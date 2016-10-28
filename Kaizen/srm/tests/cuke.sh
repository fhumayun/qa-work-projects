#!/bin/bash

echo '[INFO] Running cuke.sh...'

#cucumberjs --tags @participant
#cucumberjs --tags @event
#cucumberjs --tags @user
#cucumberjs --tags @fidget
cucumberjs

CUKE_EXIT_CODE=$?

if [ $CUKE_EXIT_CODE -ne 0 ]  ; then echo '[ERROR] Finished with errors...' && exit 1 ; fi

echo '[INFO] cuke.sh Finished...'
