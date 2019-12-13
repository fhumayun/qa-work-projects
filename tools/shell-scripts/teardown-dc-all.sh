#!/bin/bash
#export containerCheck=`check_if_built $1 | awk '{ print $1 }' | grep "OK"`

function check_exit {
	echo "ensure $1 container is not running in exit mode"
        #sudo docker rm -f $(docker ps -a | grep "$1.*Exited" | cut -d " " -f 1)
	#sudo docker ps -a | grep Exit | cut -d ' ' -f 1 | xargs sudo docker rm
	sudo docker rm -f $(docker ps -q -f status=exited)
}

function stop_container {
export containerID=`docker ps -a | grep $1 | awk '{print $1}'`
echo "Testing whether $1 docker container is running!"
CONTAINER=$1
RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

	if [ $? -eq 1 ]; then
	  echo "UNKNOWN - $CONTAINER does not exist."
	  #exit 3
	fi

	if [ "$RUNNING" == "false" ]; then
	  echo "CRITICAL - $CONTAINER is not running."
	  #exit 2
	fi

GHOST=$(docker inspect --format="{{ .State.Ghost }}" $CONTAINER)

	if [ "$GHOST" == "true" ]; then
	  echo "WARNING - $CONTAINER has been ghosted."
	  #exit 1
	fi

STARTED=$(docker inspect --format="{{ .State.StartedAt }}" $CONTAINER)
NETWORK=$(docker inspect --format="{{ .NetworkSettings.IPAddress }}" $CONTAINER)

	echo "OK - $CONTAINER is running. IP: $NETWORK, StartedAt: $STARTED"

        echo "docker $1 container has ID: $containerID"
	echo "docker stop $containerID"
	sudo docker stop $containerID
	check_exit $1
}
stop_container dockerimages_ier_1
stop_container dockerimages_notification_1
stop_container dockerimages_kafkabridge_1
stop_container dockerimages_eventprocessor_1
stop_container sprouttrax_apiserver_1
stop_container dockerimages_restkafkabridge_1 
stop_container dockerimages_devicemanager_1
