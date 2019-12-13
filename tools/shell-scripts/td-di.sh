#!/bin/bash
export imageID=`docker images | grep "$1" | awk '{ print $3}'`

function kill-image {

        echo "Deleting docker image for $1"
        echo "docker rm $imageID"
	sudo docker rmi -f $imageID
	#echo "ensuring no untagged images are running"
	#docker rmi -f $(docker images -q --filter "dangling=true")
	#sudo docker rmi $(sudo docker images -f "dangling=true" -q)
}
kill-image $1
