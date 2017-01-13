#!/bin/bash
echo "[INFO] Starting up $0"

## Vars
###############################################
URL="https://eagleeyeintel.atlassian.net/rest/api/2/search"
FILENAME=jirareleasename.txt
WORKDIR="/tmp"
ACTION=""
ENVIRONMENTS=( "sprouttrax" "straxrm" "eagleeyesac" "sacplayback" "strax" "straxmedia" "straxid" )
#ENVIRONMENTS=( "sacplayback" )

## Setup
###############################################
cd ${WORKDIR}

## Figure out what action to take
###############################################
case $1 in
    "--delete")
        # delete (delete)
        echo '[INFO] Deleting...'
        ACTION="delete"
        ;;
    "--safe-delete")
        # safe delete (merge/delete)
        echo '[INFO] Safe-deleting...'
        ACTION="safedelete"
        ;;
    "--rename")
        # rename branch (copy/delete)
        echo '[INFO] Renaming...'
        if [[ -z "$2" ]]; then
            echo 'The rename action requires a new branch name be specified...'
            exit
        fi
        NEWNAME="$2"
        ACTION="rename"
        ;;
    "--create")
        # create new branch
        echo '[INFO] Creating...'
        ACTION="create"
        ;;
    *)
        # no arg
        echo '[ERROR] Argument needed...'
        echo '--delete, --safe-delete, --rename, --create'
        exit
esac

## Call Jira to get newest Release name
###############################################
curl -vs -g -o ${FILENAME} -X POST -H "Content-Type: application/json" -H "Authorization: Basic Zmh1bWF5dW5AZ3JvdXBjYXJldGVjaC5jb206Y2FyZXRlYW0=" -H "Cache-Control: no-cache" \
 -d '{
"jql": "project = STX AND Type = Release",
"startAt": 0,
"maxResults": 1,
"fields": [
        "customfield_10020"
    ]
}' ${URL} 2> /dev/null

# This could probably be better...
cat ${FILENAME} | jq -M '.issues|.[0]|.fields|.customfield_10020' | tr -d '\"' | tee ${FILENAME}
#cat ${FILENAME} | tr , '\n' |grep 'customfield_10020' | cut -d'"' -f 6 | tee ${FILENAME}
BRANCHNAME=$(cat ${FILENAME})

## Verify the correct name
###############################################
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

## Get git repos
###############################################

for env in "${ENVIRONMENTS[@]}"
do
    cd /tmp
    rm -rf ${env}
    git clone https://github.com/groupcaretech/${env}
    cd ${env}

    case $ACTION in
    delete)
        git push origin --delete ${BRANCHNAME}
        ;;
    safedelete)
        # merge first
        echo 'Safe-delete not yet implemented...'
        #git push origin --delete ${BRANCHNAME}
        ;;
    rename)
        # copy / delete
        echo 'Rename not yet implemented...'
        #git push origin --delete ${BRANCHNAME}
        ;;
    create)
        git checkout origin/develop
        git checkout -b ${BRANCHNAME}
        git push origin ${BRANCHNAME}
        ;;
    esac

    cd /tmp
    rm -rf ${env}
done

## Clean
###############################################
rm ${FILENAME}

## Notify via Slack that the new branches are ready
###############################################
if [[ $ACTION == "create" ]]; then
    echo "🌳 \`New git branch [${BRANCHNAME}] created for strax, srm, api, sac, pb, id, media.\`" | slackcat -c engineering --stream --plain > /dev/null &
fi
