#!/bin/bash

export Home="/var/lib/jenkins"
export OpsDeckPath="$Home/OpsDeck"

function check_if_image_exists(){
  declare -A array
  array[gccloud/ubuntu]="sudo docker build -t=gccloud/ubuntu $OpsDeckPath/gccloud-ubuntu/."
  array[gccloud/java]="sudo docker build -t=gccloud/java $OpsDeckPath/gccloud-java/."
  array[gccloud/nodejs]="sudo docker build -t=gccloud/nodejs $OpsDeckPath/gccloud-nodejs/."
  array[gccloud/mosquitto]="sudo docker build -t=gccloud/mosquitto $OpsDeckPath/gccloud-mosquitto/."
  array[gccloud/mongodb]="sudo docker build -t=gccloud/mongodb --no-cache $OpsDeckPath/gccloud-mongodb/."
  array[gccloud/kafka]="sudo docker build -t=gccloud/kafka --no-cache $OpsDeckPath/kafka/."
  
  echo -e "Testing whether $1 image has been built? \n"
  if docker images | grep -w $1
  then
    echo -e "$1 exists. Safe to proceed. \n"
  else
    echo -e "$1 does not exists. \n"
    echo "Running $1 build exec: ${array[$1]}" 
    ${array[$1]} 
  fi
}
check_if_image_exists gccloud/ubuntu
check_if_image_exists gccloud/java
check_if_image_exists gccloud/mongodb
check_if_image_exists gccloud/kafka
check_if_image_exists gccloud/mosquitto
check_if_image_exists gccloud/nodejs
