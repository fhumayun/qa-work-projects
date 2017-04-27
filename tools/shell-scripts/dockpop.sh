#!/bin/bash

# Tests if an image has already been built. Images like "mysql", "rabbitmq", "solr", etc. don't have to be re-build often.


function check_if_built {

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
}

  function setdockerhostname {
    HostNameFile="hostname.original"
    WhatIsHost=`hostname`
    if [ $HOSTNAME != "Tims-MacBook-Pro.local" ]
      then echo "Time to fix hostname ==> " $WhatIsHost echo "to Tims-MacBook-Pro.local"
           echo "Backing up hostname first"
           if [ -f $HostNameFile ]
             then
                echo "Clean up hostfile backup"
                rm -f $HostNameFile
                echo "Backing up hostname"
                hostname > hostname.original
                echo "================================"
                echo "File $HostNameFile contains"
                cat $HostNameFile
              else
                echo "Backing up hostname"
                hostname > hostname.original
                echo "================================"
                echo "File $HostNameFile contains"
                cat $HostNameFile
              fi
      sudo hostname Tims-MacBook-Pro.local
    else
      echo "$WhatIsHost is correct"
    fi
  }

  function resetdockerhost {
    HostNameFile="hostname.original"
    WhatIsHost=`hostname`
    if [ $HOSTNAME == "Tims-MacBook-Pro.local" ]
      then
      echo "hostname is currently set to ==> " $WhatIsHost
        if [ -f $HostNameFile ]
          then
            while read line
            do
                echo "Time to restore hostname: $WhatisHost back to $line"
                echo "Restoring hostname to $line"
                sudo hostname $line
            done < $HostNameFile
        else
            echo "YIKES! The backup files $HostNameFile is missing!  Just set your hostname manually with the command:\nsudo hostname <name you choose>"
            exit
        fi
        else
          echo "$WhatIsHost is already in its original state"
    fi
  }

  function getStraxPath {

    #DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
    #!/bin/bash

    SOURCE="${BASH_SOURCE[0]}"
    while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
      TARGET="$(readlink "$SOURCE")"
      if [[ $SOURCE == /* ]]; then
        echo "SOURCE '$SOURCE' is an absolute symlink to '$TARGET'"
        SOURCE="$TARGET"
      else
        DIR="$( dirname "$SOURCE" )"
        echo "SOURCE '$SOURCE' is a relative symlink to '$TARGET' (relative to '$DIR')"
        SOURCE="$DIR/$TARGET" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
      fi
    done
    echo "SOURCE is '$SOURCE'"
    RDIR="$( dirname "$SOURCE" )"
    DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
    if [ "$DIR" != "$RDIR" ]; then
      echo "DIR '$RDIR' resolves to '$DIR'"
    fi
    echo "Home base is set to ==> '$DIR'"

    echo "The script you are running has basename `basename $0`, dirname `dirname $0`"
    echo "The present working directory is `pwd`"

  }

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

getStraxPath
setdockerhostname



check_if_exists_and_build mongo
check_if_exists_and_build mosquitto
check_if_exists_and_build kafka
check_if_exists_and_build node
check_if_exists_and_build notification
check_if_exists_and_build eventprocessor
check_if_exists_and_build mqttkafkabridge
check_if_exists_and_build rabbitmq

resetdockerhostname
