#!/bin/bash
HOSTIP=`curl ipinfo.io/ip`
USER="user"
PASSWORD="password"
KEYWORD1="true"
PORT="8091"

function api-check(){
  declare -A array
  array[process-pull]="http://$HOSTIP:$PORT/service/lpwan/processing/pulling"
  array[process-stats]="http://$HOSTIP:$PORT/service/lpwan/processing/procstats"

  MESSAGE="Testing API for $1"
  CONTENT=$(curl -s -S --user $USER:$PASSWORD "${array[$1]}"| grep $KEYWORD1 | awk -F'"' {'print $4'} | xargs)
  #echo $MESSAGE
  #echo "Content Keyword found: $CONTENT"
  echo "Running $1 API Test URL: ${array[$1]}" 
  if [ "$CONTENT" == "$KEYWORD1" ]
    then
	echo -e "\e[1m\e[92m\e[5mPASS!!\e[0mLPWAN API for $1 works!"
    else
	echo -e "\e[31m\e[1m\e[5mFAIL!!\e[0mLPWAN API for $1 is failing!"
  fi
}
api-check process-pull
api-check process-stats
