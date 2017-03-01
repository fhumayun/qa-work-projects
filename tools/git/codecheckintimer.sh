#!/bin/bash

## Vars
ENVIRONMENTS=( "sprouttrax" "straxrm" "eagleeyesac" "sacplayback" "strax" "straxmedia" "straxid" )
WORKDIR="/tmp"

# Working dir
cd ${WORKDIR}

# Main
echo "#############################"
echo "##   Last code check ins   ##"
echo "#############################"

for env in "${ENVIRONMENTS[@]}"
do

    rm -rf /tmp/${env}
    git clone https://github.com/GroupCareTech/${env} /tmp/${env} > /dev/null
    cd /tmp/${env}
    echo "${env}..."
    echo "Master: $(git log origin/master | grep Date | head -1)"
    echo "Develop: $(git log origin/develop | grep Date | head -1)"
    rm -rf /tmp/${env}

done