#!/bin/bash
HOSTIP=`curl ipinfo.io/ip`
KEYWORD="USA"
URL="http://$HOSTIP/api/notification/closestaddress/26.4009987/-80.1091288"
MESSAGE="Testing REST GET call: $URL"
echo $MESSAGE
CONTENT=$(curl -s -S "$URL"| grep $KEYWORD | awk -F'"' {'print $4'} | awk -F',' {'print $4'} | xargs)
echo "Content Keyword found: $CONTENT"
if [ "$CONTENT" == "$KEYWORD" ]
  then
	echo -e "\e[1m\e[92m\e[5mPASS!!\e[0m NODE API works!"
    else
	echo -e "\e[31m\e[1m\e[5mFAIL!!\e[0mLPWAN API is failing!"
fi
