#!/bin/bash

start=`date +%s`
export SRL="/var/log/strax-release-`date +"%Y-%m-%d--%I:%M-%P"`"
USER=$(whiptail --title "(S)trax (D)eployment (T)ool 0.1" --menu "Choose who is doing the release" 15 60 4 \
"Faisal" "QA" \
"Tim" "Dev" \
"Sundar" "Dev" \
"Other" "Authorized User" 3>&1 1>&2 2>&3)
exitstatus=$?
if [ $exitstatus = 0 ]; then
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "      Deployment Initiated by $USER           " | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    pinky					      	  | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+      Backing up MongoDB for Release        +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~
    mongodump                                             | sudo tee -a $SRL
    echo "Logging Strax Release $SRL"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo -n "+   Strax Released @ `date +"%Y-%m-%d--%I:%M-%P"`"
    echo "    +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd ~
    ./get_strax.sh
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Building Strax Jar files           +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd ~/strax/strax
    mvn clean install -DskipTest
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Tear Down Docker Strax             +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    teardown-dc-all.sh
    teardown-di-all.sh
    set +x
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+ Loading Required Collections into Mongo +"
    echo "+++++++++++++++++++++++++++++++++++++++++++++"
    cd ~/strax/strax/strax-test/mongo-test-scripts/
    mongo MicroServiceNoEvents.js
    mongo CreateParticipant.js
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Verifying GCC Images               +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    gcclouddeploy.sh	
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Building SproutTrax Image          +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd ~/strax/docker_images/node/SproutTrax
    docker build -t=strax/sprout --no-cache .
    docker-compose up -d
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Docker Compose Strax Images        +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    cd ~/strax/docker_images/
    docker-compose up -d
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         Strax Docker Containers            +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    docker ps -a | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         Strax Docker Images                +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    docker images | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+      Restoring MongoDB after Relase        +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~
    mongorestore ./dump                                   | sudo tee -a $SRL
    end=`date +%s`
    runtime=$( echo "$end - $start" | bc -l )
    echo "$start start time, $end end time, $runtime runtime"
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "Release took $runtime seconds to execute"       | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
            if whiptail --yesno "Send Email Notification?" 10 60; then
                    sudo cat $SRL | mail -s "[`date +"%Y-%m-%d--%I:%M-%P"`] Strax Released to the Build Server Instance" fhumayun@groupcaretech.com,tmcclure@groupcaretech.com,sselvaraj@groupcaretech.com,hglass@groupcaretech.com
            else
                    echo "End of Release. No email sent.  Goodbye!"
            fi
else
  echo "Aborting Strax Release.  Goodbye!"
fi
