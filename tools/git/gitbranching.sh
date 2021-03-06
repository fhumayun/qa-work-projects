#!/bin/bash
echo "[INFO] Starting up $0"

# --- Vars
url="https://eagleeyeintel.atlassian.net/rest/api/2/search"
FILENAME=jirareleasename.txt
WORKDIR="/tmp"
ACTION=""
branchname=""
GITACCOUNT="https://github.com/groupcaretech"
REPOSITORIES=( "sprouttrax" "strax-app" "strax" "straxmedia" "straxid" )
#REPOSITORIES=( "qa" )

# --- Setup
cd "${WORKDIR}" || exit

# --- Library
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
        }' ${url} 2> /dev/null

    jq -M '.issues|.[0]|.fields|.customfield_10020' "${FILENAME}" | tr -d '\"' | tee ${FILENAME}
    #cat ${FILENAME} | tr , '\n' |grep 'customfield_10020' | cut -d'"' -f 6 | tee ${FILENAME}
    branchname=$(cat ${FILENAME})
    rm ${FILENAME}

    ## Verify the correct name
    read -p -r "[$] Is ${branchname} the correct name? (ynq) " yn

    case $yn in
        [Yy]* )
            ;;
        [Qq]* )
            echo 'Quitting...'
            exit
            ;;
        [Nn]* )
            read -p -r "[$] Enter the new name: " branchname
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

# --- Figure out what action to take
case $1 in
    "--delete"|"delete")
        ACTION="delete"
        echo '[INFO] Deleting...'
        if [[ -n "$2" ]]; then
            branchname="$2"
        else
            getLatestRelease
        fi
        ;;
    "--rename"|"rename")
        ACTION="rename"
        echo '[INFO] Renaming...'
        if [[ -z "$2" || -z "$3" ]]; then
            echo 'The rename action requires you specify the old branch name and a new branch name...'
            exit 1
        fi
        OLDNAME="$2"
        NEWNAME="$3"
        ;;
    "--create"|"create")
        # create new branch
        echo '[INFO] Creating...'
        ACTION="create"
        if [[ -z "$2" || -z "$3" ]]; then
            echo 'The Create action requires you specify the Source branch name and a Destination branch name...'
            exit 1
        fi
        oldname="$2"
        newname="$3"
        ;;
    "--merge"|"merge")
        # merge branch into master
        ACTION="merge"
        echo '[INFO] Merging...'
        if [[ -z "$2" || -z "$3" || -z "$4" ]]; then
            echo 'The merge action requires you specify the source branch, destination branch, and repo name...'
            exit
        fi
        sourcebranch="$2"
        destingationrepo="$3"
        branchname="$4"
        ;;
    *)
        echo '[ERROR] Argument needed...'
        usage
esac

# --- Main
for repo in "${REPOSITORIES[@]}"
do
    cd /tmp || exit
    rm -rf "${repo}"
    git clone "${GITACCOUNT}"/"${repo}"
    cd "${repo}" || exit
    git fetch

    case $ACTION in
    delete)
        git push origin --delete "${branchname}"
        ;;
    rename)
        git checkout --track origin/"${OLDNAME}"
        git checkout -b "${NEWNAME}"
        git push origin "${NEWNAME}"
        git push origin --delete "${OLDNAME}"
        ;;
    create)
        git checkout --track remotes/origin/"${oldname}"
        git checkout -b "${newname}"
        git push origin "${newname}"
        ;;
    merge)
        # Only merges into master for now
        git merge origin/"${branchname}" -m "This is an automatic merge bot"
        git push
    esac

    #cd /tmp
    #rm -rf ${repo}
done

# --- Notify via Slack that the new branches are ready
if [[ $ACTION == "create" ]]; then
    echo "Creation done"
    #echo "🌳 \`New git branch [${branchname}] created for strax, srm, api, sac, pb, id, media.\`" | slackcat -c engineering --stream --plain > /dev/null &
fi
