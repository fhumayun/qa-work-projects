#!/bin/bash

echo "Creating apidocs..."

if [[ -n /tmp/api ]]; then rm -rf /tmp/api; echo "Removing stale api files..."; fi
git clone https://github.com/groupcaretech/sprouttrax /tmp/api
cd /tmp/api
apidoc -i app/controllers/ -o apidoc/
if [[ $(docker ps -a | grep apidocs | wc -l | xargs) > 0 ]]; then docker rm -f apidocs; echo "Removing stale apidocs container..."; fi
docker run --name apidocs -v /tmp/api/apidoc:/usr/share/nginx/html:ro -p 3270:80 -P -d nginx

echo "Done..."