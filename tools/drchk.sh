#!/bin/bash

DRUSER="strax"
DRPASS="eei"
REGISTRY="dr.strax.co:5000"
REPOSITORY=$1

echo "Logging into $REGISTRY"
sudo docker login -u $DRUSER -p $DRPASS $REGISTRY
LATEST="`wget -qO- http://$REGISTRY/v1/repositories/$REPOSITORY/tags`"
LATESTVER=`echo $LATEST | sed "s/{//g" | sed "s/}//g" | sed "s/\"//g" | cut -d ' ' -f2`

RUNNING=`docker inspect "$REGISTRY/$REPOSITORY" | grep Id | sed "s/\"//g" | sed "s/,//g" |  tr -s ' ' | cut -d ' ' -f3`

if [ "$RUNNING" == "$LATESTVER" ];then
    echo "$1 local is same version as in $REGISTRY, do nothing"
else
    echo "$1 local is NOT the same version in $REGISTRY, update needed!"
    echo "$RUNNING != $LATESTVER"
fi
