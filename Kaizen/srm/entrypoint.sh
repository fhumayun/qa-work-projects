#!/bin/bash
echo '[INFO] entrypoint.sh started...'

# Go to tests dir and execute the runner.
cd tests

./cuke.sh
#./maven.sh
#echo '[INFO] Skipping Maven...'

echo '[INFO] entrypoint.sh finished...'
