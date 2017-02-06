#!/bin/bash
echo '[INFO] entrypoint.sh started...'

# Make sure req's are installed
echo "Checking node installed..."
if [[ $(which node | wc -l | xargs) < 1 ]]; then
    echo "Please install 'node' and try again..."
    exit 1
fi

# Deps
npm i

# Cuke
npm i -g cucumber

# Go to tests dir and execute the runner.
cd tests

./cuke.sh
./maven.sh
#echo '[INFO] Skipping Maven...'

echo '[INFO] entrypoint.sh finished...'
