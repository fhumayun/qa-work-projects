#!/bin/bash

echo '[INFO] _testrunner.sh started...'

# Install dependancies from package.json
npm install
if [[ $? -ne 0 ]]; then echo '[ERROR] "npm install" finished with errors...'; fi

# Install cuke globally
npm install -g cucumber
if [[ $? -ne 0 ]]; then echo '[ERROR] "npm install -g cucumber" finished with errors...'; fi

# link nodejs executable to node
if [ ! -f /usr/bin/node ]; then
    ln -s /usr/bin/nodejs /usr/bin/node > /dev/null
fi

# Go to tests dir and execute the runner.
# This will call cuke and maven
cd tests

./cuke.sh

#./maven.sh
echo '[INFO] -- Skipping Maven...';

echo '[INFO] _testrunner.sh finished...'
