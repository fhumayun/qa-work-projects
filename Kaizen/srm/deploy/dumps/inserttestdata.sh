#!/bin/bash
# Takes the zipped mongodump and restores test data from it

# untar
if [[ -f "./apidump.tar.gz" ]]; then
    tar xvf apidump.tar.gz
fi

# apiserverdump
if [[ ! -d "./apiserverdump" ]]; then
    echo "Tar was unsuccessful..."
    exit 1
fi

mv apiserverdump/ dump

mongorestore --host 127.0.0.1:27017

if [[ -d "./dump" ]]; then
    rm -rf dump/
fi
