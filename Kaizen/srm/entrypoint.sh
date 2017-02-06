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

echo "Checking cucumber installed..."
if [[ $(npm list -g 2>/dev/null | grep cucumber | wc -l | xargs) < 1 ]]; then
    echo "Please install cucumber (npm install -g cucumber)..."
    exit 1
fi

# Go to tests dir and execute the runner.
cd tests

./cuke.sh
./maven.sh
#echo '[INFO] Skipping Maven...'

echo '[INFO] entrypoint.sh finished...'
