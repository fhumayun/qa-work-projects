#!/bin/bash

# Common Variables
export Major=0
export Minor=$PT_IT

#Capture directory passed as argument.
workdir=$(dirname "$1")

help () {
        echo "usage: $(basename $0) path of Strax Repo"
}

if [ $# -lt 1 ] ;
then
        help

        exit 1
fi

for i in $@
do
        git rev-list master HEAD --count $workdir/
        echo "Pivotal Tracker Iteration No: $PT_IT"
		echo "Git Head Counter: $GitVer"
		echo "Build Number: $Major.$Minor.$Release"
done
