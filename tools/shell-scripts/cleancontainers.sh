#!/bin/bash
# Shamelessly stolen from https://gist.github.com/eduardocardoso/82a629882ddb02ab3677

set -o errexit

echo "Removing exited docker containers..."
docker ps -a -f status=exited -q | xargs -r docker rm -f -v

echo "Removing untagged images..."
docker images --no-trunc | grep "<none>" | awk '{print $3}' | xargs -r docker rmi -f

