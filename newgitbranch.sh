#!/usr/bin/env bash

# Usage:
# ./newgitbranch.sh <REPONAME> <NEWBRANCHNAME>

# Get repo name
if [ -z "$1" ]; then
    echo "Error: Repository needed..."
    exit 1
fi
REPOSITORY=$1

# Get new branch (release) name
if [ -z "$2" ]; then
    echo "Error: New branch name needed..."
    exit 1
fi
NEWBRANCHNAME=$2

# Get develop branch sha1 to fork new branch from
DEVELOPSHA=$(curl -s -X GET -u qa@groupcaretech.com:gcSource1 \
     "https://api.github.com/repos/groupcaretech/$REPOSITORY/git/refs/heads/develop" \
     | jq '.object.sha')

# Send api request to github to create new branch
curl -v -g -X POST -H "Content-Type: application/json" -H "Authorization: Basic cWFAZ3JvdXBjYXJldGVjaC5jb206Z2NTb3VyY2Ux" -H "Cache-Control: no-cache" -H "Postman-Token: 29184b44-5926-a753-29a2-4fe58452b100" \
-d '{
	"ref": "refs/heads/'${NEWBRANCHNAME}'",
	"sha": '${DEVELOPSHA}'
}' "https://api.github.com/repos/groupcaretech/$REPOSITORY/git/refs"

echo "done."
