#!/bin/bash
echo "[INFO] Starting up $0"

## Vars
###############################################
URL="https://eagleeyeintel.atlassian.net/rest/api/2/search"
FILENAME=jirareleasename.txt
WORKDIR="/tmp"
ACTION=""
BRANCHNAME=""
GITACCOUNT="https://github.com/groupcaretech"
REPOSITORIES=( "strax-api" "strax-app" "strax" "strax-media" "strax-id" "strax-mobile" )
#REPOSITORIES=( "qa" )

## Setup
###############################################
cd ${WORKDIR}

## Library
###############################################
getLatestRelease () {

    # Try the latest Jira release as the branch name
    curl -vs -g -o ${FILENAME} -X POST \
        -H "Content-Type: application/json" \
        -H "Authorization: Basic Zmh1bWF5dW5AZ3JvdXBjYXJldGVjaC5jb206Y2FyZXRlYW0=" \
        -H "Cache-Control: no-cache" \
        -d '{
        "jql": "project = STX AND Type = Release",
        "startAt": 0,
        "maxResults": 1,
        "fields": [
                "customfield_10020"
            ]
        }' ${URL} 2> /dev/null

    cat ${FILENAME} | jq -M '.issues|.[0]|.fields|.customfield_10020' | tr -d '\"' | tee ${FILENAME}
    #cat ${FILENAME} | tr , '\n' |grep 'customfield_10020' | cut -d'"' -f 6 | tee ${FILENAME}
    BRANCHNAME=$(cat ${FILENAME})
    rm ${FILENAME}

    ## Verify the correct name
    read -p "[$] Is ${BRANCHNAME} the correct name? (ynq) " yn

    case $yn in
        [Yy]* )
            ;;
        [Qq]* )
            echo 'Quitting...'
            exit
            ;;
        [Nn]* )
            read -p "[$] Enter the new name: " BRANCHNAME
            ;;
        *)
            ;;
    esac
}

usage () {
    echo 'Usage ./gitbranching.sh [command] [args...]'
    echo '    --delete [optional branch name]'
    echo '    --rename [old branch name] [new branch name]'
    echo '    --create [optional branch name]'
    echo '    --merge [repository name] [branch name]'
    exit
}

## Figure out what action to take
###############################################
case $1 in
    "--delete")
        ACTION="delete"
        echo '[INFO] Deleting...'
        if [[ -n "$2" ]]; then
            BRANCHNAME="$2"
        else
            getLatestRelease
        fi
        ;;
    "--rename")
        ACTION="rename"
        echo '[INFO] Renaming...'
        if [[ -z "$2" || -z "$3" ]]; then
            echo 'The rename action requires you specify the old branch name and a new branch name...'
            exit
        fi
        OLDNAME="$2"
        NEWNAME="$3"
        ;;
    "--create")
        # create new branch
        ACTION="create"
        echo '[INFO] Creating...'
        if [[ -n "$2" ]]; then
            BRANCHNAME="$2"
        else
            getLatestRelease
        fi
        ;;
    "--merge")
        # merge branch into master
        ACTION="merge"
        echo '[INFO] Merging...'
        if [[ -z "$2" || -z "$3" ]]; then
            echo 'The merge action requires you specify the repo name and branch name...'
            exit
        fi
        REPOSITORIES=( "$2" )
        BRANCHNAME="$3"
        ;;
    *)
        echo '[ERROR] Argument needed...'
        usage
esac

## Get git repos
###############################################

for repo in "${REPOSITORIES[@]}"
do
    cd /tmp
    rm -rf ${repo}
    git clone ${GITACCOUNT}/${repo}
    cd ${repo}
    git fetch

    case $ACTION in
    delete)
        git push origin --delete ${BRANCHNAME}
        ;;
    rename)
        git checkout --track origin/${OLDNAME}
        git checkout -b ${NEWNAME}
        git push origin ${NEWNAME}
        git push origin --delete ${OLDNAME}
        ;;
    create)
        git checkout -b ${BRANCHNAME}
        git push origin ${BRANCHNAME}
        ;;
    merge)
        # Only merges into master for now
        git merge origin/${BRANCHNAME} -m "This is an automatic merge bot"
        git push
    esac

    #cd /tmp
    #rm -rf ${repo}
done

## Notify via Slack that the new branches are ready
###############################################
if [[ $ACTION == "create" ]]; then
    echo "Creation done"
    #echo "🌳 \`New git branch [${BRANCHNAME}] created for strax, srm, api, sac, pb, id, media.\`" | slackcat -c engineering --stream --plain > /dev/null &
fi
