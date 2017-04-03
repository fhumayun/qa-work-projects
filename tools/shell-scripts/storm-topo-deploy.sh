#!/usr/bin/env bash

echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "          Storm Topology Checker"
echo "++++++++++++++++++++++++++++++++++++++++++++++"

StormTopGetName=$(storm list | grep ACTIVE | awk {'print $1'})
StormTopStatus=$(storm list | grep ACTIVE | awk {'print $2'})


# Topology builder
StormTopBuilder(){
    Stormjar="strax-storm-safetrax-1.0.jar"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "    Checking if Storm Jars are Built          "
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd $StormShPath

    if [ -f $Stormjar ]; then
       echo "File '$Stormjar' Exists"
    else
       echo "The File '$Stormjar' Does Not Exist"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "        Fixing Storm POM scope issues"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        cd /home/ubuntu/strax/strax/strax-storm/strax-topologies/strax-storm-safetrax
        sed -i 's/<!--<scope>provided<\/scope>-->/<scope>provided<\/scope>/g' pom.xml
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "        Building Storm Maven JARs"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        cd ~/strax/strax
        mvn clean install -DskipTest
    fi
}

# Call storm script
StormScript(){
    cd $StormShPath
    StormSh=$(ls | grep $(hostname).sh)
}


# Main

StormShPath="/home/ubuntu/strax/storm-topologies/"

cd $StormShPath

GetStormTopName="$(StormTopGetName)"
GetStormTopStatus="$(StormTopStatus)"
StormExec=$(StormSh)
ActiveState="ACTIVE"

if [[ "$(GetStormTopStatus)" == "$ActiveState" ]]
    then
    echo "$GetStormTopName is currently ACTIVE on $(hostname).  Ready For Shutdown"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "     Bringing Down Storm $GetStormTopName     "
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    storm kill $GetStormTopName
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "Bringing Up Storm $GetStormTopName on $(hostname)"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd $StormShPath
    ./$StormExec
else
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "Bringing Up Storm $GetStormTopName on $(hostname)"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd $StormShPath
    ./$StormExec
fi
