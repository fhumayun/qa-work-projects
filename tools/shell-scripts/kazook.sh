#!/bin/bash
function doStraxKafka {

  BaseDir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
  KafkaDir="$BaseDir/straxkafka"
  KafkaURL="http://mirrors.advancedhosters.com/apache/kafka/0.8.2.1/kafka-0.8.2.1-src.tgz"
  KafkaGZ="kafka-0.8.2.1-src.tgz"
  KafkaPath="kafka-0.8.2.1-src"
  KafkaBin="bin"
  KafkaTopic="event"
  KafkaLocalSvc="127.0.0.1:2181"
  KafkaScript="kafka-topics.sh"

  if [[ ! -e $KafkaDir ]];
    then
      echo "Creating $KafkaDir"
      mkdir -p $KafkaDir
      cd $KafkaDir
      echo "Downloading kafka ==> "
      curl $KafkaURL | tar xvz
      echo "Launching topic from $KafkaDir/$KafkaPath/$KafkaBin"
      if [[ -e "$KafkaDir/$KafkaPath/$KafkaBin/$KafkaScript" ]];
        then
          cd $KafkaDir/$KafkaPath/$KafkaBin/
          echo "Currently in " `pwd`
          echo "Creating Kafka Event Topic ==>"
          ./$KafkaScript --create --zookeeper $KafkaLocalSvc --replication-factor 1 --partitions 1 --topic $KafkaTopic
        else
          echo "Currently in " `pwd`
          echo "Yikes! $KafkaScript file not found!"
          exit
      fi
  else
    echo "$KafkaDir already exists!  Assuming topic was created already! Clearing $KafkaDir"
    rm -rf $KafkaDir
    exit
  fi

}

doStraxKafka
