#!/bin/bash

echo '================================================'
echo "$0"
echo '================================================'

if [[ -n /tmp/api ]]; then
    rm -rf /tmp/api
    echo "Removing stale api files..."
fi

echo "Grabbing latest code..."
git clone https://github.com/groupcaretech/sprouttrax /tmp/api
cd /tmp/api

echo "Checking node installed..."
if [[ $(which node | wc -l | xargs) < 1 ]]; then
    echo "Please install 'node' and try again..."
    exit 1
fi

echo "Checking npm installed..."
if [[ $(which npm | wc -l | xargs) < 1 ]]; then
    echo "Please install 'npm' and try again..."
    exit 1
fi

echo "Checking apidoc installed..."
if [[ $(npm list -g 2>/dev/null | grep apidoc | wc -l | xargs) < 1 ]]; then
    echo "Please install apidoc (npm install -g apidoc)..."
    exit 1
fi

echo "Creating apidocs..."
apidoc -i app/controllers/ -o apidoc/

if [[ $(docker ps -a | grep apidocs | wc -l | xargs) > 0 ]]; then
    docker rm -f apidocs
    echo "Removing stale apidocs container..."
fi

echo "Starting docker container..."
docker run --name apidocs --restart always -v /tmp/api/apidoc:/usr/share/nginx/html:ro -p 3270:80 -P -d nginx

echo "Done..."
