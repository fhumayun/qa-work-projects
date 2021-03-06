#!/bin/bash
#export containerCheck=`check_if_built $1 | awk '{ print $1 }' | grep "OK"`
export containerID=`docker ps -a | grep $1 | awk '{print $1}'`

function stop_container {
echo "Testing whether $1 docker container is running!"
CONTAINER=$1
RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

	if [ $? -eq 1 ]; then
	  echo "UNKNOWN - $CONTAINER does not exist."
	  exit 3
	fi

	if [ "$RUNNING" == "false" ]; then
	  echo "CRITICAL - $CONTAINER is not running."
	  exit 2
	fi

GHOST=$(docker inspect --format="{{ .State.Ghost }}" $CONTAINER)

	if [ "$GHOST" == "true" ]; then
	  echo "WARNING - $CONTAINER has been ghosted."
	  exit 1
	fi

STARTED=$(docker inspect --format="{{ .State.StartedAt }}" $CONTAINER)
NETWORK=$(docker inspect --format="{{ .NetworkSettings.IPAddress }}" $CONTAINER)

	echo "OK - $CONTAINER is running. IP: $NETWORK, StartedAt: $STARTED"

        echo "issue docker stop $1 command"
	echo "docker stop $containerID"
	docker stop $containerID
	echo "ensure $1 container is not running in exit mode"
	docker rm $(docker ps -a | grep '$1.*Exited' | cut -d " " -f 1)
}
stop_container $1
