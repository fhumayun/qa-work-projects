#!/bin/bash
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

setdockerhostname
