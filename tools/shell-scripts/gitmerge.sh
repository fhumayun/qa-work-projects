#!/bin/bash
echo "[INFO] gitmerge.sh starting..."

if [[ -z "$1" ]]; then
    echo "[ERROR] Requires a repo name..."
    exit 1
fi

if [[ -z "$2" ]]; then
    echo "[ERROR] Requires a branch name..."
    exit 1
fi

REPO=$1
BRANCH=$2

cd /tmp
rm -rf $REPO
git clone https://github.com/groupcaretech/$REPO
cd $REPO
git merge origin/$BRANCH -m "This is an automatic merge bot. I am merging into Master."
git push

echo "[INFO] gitmerge.sh done..."
