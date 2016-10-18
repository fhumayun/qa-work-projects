#!/bin/bash

echo '[INFO] Running cuke.sh...'
echo '[INFO] Running with tags: EVENT, CREATE only...'

cucumberjs
CUKE_EXIT_CODE=$?

if [ $CUKE_EXIT_CODE -ne 0 ]  ; then echo '[ERROR] Finished with errors...' && exit 1 ; fi

echo '[INFO] cuke.sh Finished...'
