#!/bin/bash

echo '================================================'
echo "$1"
echo '================================================'

if [[ -n /tmp/mavenreport ]]; then
    rm -rf /tmp/mavenreport
    echo "Removing stale maven files..."
fi

echo "Creating new maven temp dir..."
mkdir /tmp/mavenreport
cd /tmp/mavenreport

# ----------------------------------------------------------------------------------------

# Make sure dropbox-uploader is installed and in the path
echo "Checking node installed..."
if [[ $(which node | grep 'not found' | wc -l | xargs) > 0 ]]; then
    echo "Please install 'node' and try again..."
    exit 1
fi

# Grab latest maven report from dropbox
dropbox_uploader.sh etc etc...

if [[ $(docker ps -a | grep mavenreport | wc -l | xargs) > 0 ]]; then
    docker rm -f mavenreport
    echo "Removing stale mavenreport container..."
fi

echo "Starting docker container..."
docker run --name mavenreport --restart always -v /tmp/api/mavenreport:/usr/share/nginx/html:ro -p 3280:80 -P -d nginx

echo "Done..."
