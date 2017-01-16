#!/bin/bash
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

resetdockerhost
