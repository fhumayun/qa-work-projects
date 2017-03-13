#!/bin/bash
export imageID=`docker images | grep "/$1" | awk '{ print $3}'`

function kill-image {

        echo "issue docker rm $1 command"
        echo "docker rm $imageID"
	docker rmi -f $imageID
}
kill-image $1
