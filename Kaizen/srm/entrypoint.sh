#!/bin/bash
echo '[INFO] _testrunner.sh started...'

# Go to tests dir and execute the runner.
cd tests

./cuke.sh
#./maven.sh
echo '[INFO] -- Skipping Maven...'

echo '[INFO] _testrunner.sh finished...'