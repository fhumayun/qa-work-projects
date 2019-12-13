straxver(){
cd ~/strax/strax
StraxVer=$(git rev-list master HEAD --count)
echo $StraxVer
}
nodever(){
cd ~/strax/docker_images/node/SproutTrax
NodeVer=$(git rev-list master HEAD --count)
echo $NodeVer
}
GetStraxVer="$(straxver)"
GetNodeVer="$(nodever)"
GitSyncNo="Synced-Already"
GitSyncYes="Sync-Needed"
StormTopology="strax-safetrax-demo"
SlackChannel="strax-deployments"
SlackIcon=":vertical_traffic_light:"
SlackName="DeployBot"
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
    echo -n "+   Strax Release @ `date +"%Y-%m-%d--%I:%M-%P"`" | sudo tee -a $SRL
    echo "    +"| sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"  | sudo tee -a $SRL 
    echo "Github Synchronization Check"      		   | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"  | sudo tee -a $SRL
    GitSyncCheck(){
    cd ~/strax/strax
    GitSyncCheck=$( [ "`git log --pretty=%H ...refs/heads/master^ | head -n 1`" = "`git ls-remote origin -h refs/heads/master |cut -f1`" ] && echo "Synced-Already" || echo "Sync-Needed")
echo $GitSyncCheck
    }
    GetSyncStatus="$(GitSyncCheck)"
    if [[ "${GetSyncStatus}" == "${GitSyncYes}" ]]
        then
        echo "[ALERT!] Local and Remote Repos Out of Sync on $(hostname).  Ready For Deployment"  | sudo tee -a $SRL
        SlackReady2Deploy="*[NOTICE!]* Local and Remote Repos are *Out of Sync* on *$(hostname)*.  Ready For Deployment!"
        slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackReady2Deploy"
    else
        echo "Local and Remote Repos Already Synced. Deployment Interrupted"  | sudo tee -a $SRL
        SlackNotReady2Deploy="*[Warning!]* Local and Remote Repos already *in Sync* on *$(hostname)*.  Checking Pull Request then Continuing..."
        slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackNotReady2Deploy"
        read -p "Press any key to continue OR ctrl-c to exit..."
    fi
    echo "++++++++++++++++++++++++++++++++++++++++++++++" 
    echo "Send Deployment Warning Email"      
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    if whiptail --yesno "Send Email Notification?" 10 60; then
    SlackEntryMessage="WARNING! Deployment Initiated by $USER - Current Strax Version: $GetStraxVer, Node Version: $GetNodeVer - $(hostname) will be unavailable for a short time"
    echo "[`date +"%Y-%m-%d--%I:%M-%P"`] - Until next confirmation email - $(hostname) will be unavailable for a short while - mobile app will be unresponsive" | mail -s "WARNING! Strax is being Deployed to $(hostname)" fhumayun@groupcaretech.com,tmcclure@groupcaretech.com,sselvaraj@groupcaretech.com,hglass@groupcaretech.com,jzambrano@groupcaretech.com,tmargetta@groupcaretech.com,sadams@groupcaretech.com,jchin@groupcaretech.com
    slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackEntryMessage"
    else
         echo "No Warning Email sent.  Continuing with Deployment!"
    fi
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "      Deployment Initiated by $USER           " | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    pinky					      	  | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+      Backing up MongoDB for Release        +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~
    mongodump                                             | sudo tee -a $SRL
    echo "Forcing strax repo sync"
    cd ~
    get_strax.sh
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+     Bringing Down Storm $StormTopology     +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~/tools
    fab -f stormfab-demo.py main
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+     Preparing ProtoBuff for $hostname      +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~/strax/strax/strax-traxpack/src/main/java
    rm -rf *
    cd /home/ubuntu/strax/strax/strax-traxpack
    ./proto.sh
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "        Fixing Storm POM scope issues"	  | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd /home/ubuntu/strax/strax/strax-storm/strax-topologies/strax-storm-safetrax
    sed -i 's/<!--<scope>provided<\/scope>-->/<scope>provided<\/scope>/g' pom.xml
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         Building Strax Jar files           +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~/strax/strax
    mvn clean install -DskipTest
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "+         Tear Down Docker Strax             +"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    teardown-dc-all.sh
    teardown-di-all.sh
    set +x
    #echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    #echo "+ Loading Required Collections into Mongo +"   | sudo tee -a $SRL
    #echo "+++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    #cd ~/strax/strax/strax-test/mongo-test-scripts/
    #mongo sprout_demo.js
    #mongo MicroServiceNoEvents.js
    #cd ~/strax/strax/strax-test/mongo-test-scripts/topics
    #mongo topics_demo.js
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    echo "+         Building SproutTrax Image          +"| sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    cd ~/strax/docker_images/node/SproutTrax
    docker build -t=strax/sprout --no-cache .
    docker-compose -f docker-compose.yml up -d
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    echo "+         Docker Compose Strax Images        +"| sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    cd ~/strax/docker_images/
    docker-compose up -d
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    echo "        Patching Storm Related Files"          | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    stormpatcher.sh
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    echo "+     Bringing up the Storm Topology"		 | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++"| sudo tee -a $SRL
    cd ~/strax/storm-topologies
    ./deploy-safetrax-demo.sh
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         Strax Docker Containers            +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    docker ps -a | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         Strax Docker Images                +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    docker images | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+         CentralStation Deployment          +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    get_cs.sh
    cd ~/CentralStations/EagleEyeCSUI
    chmod +x build_cs.sh
    ./build_cs.sh
    docker-compose up -d
    cd ~/CentralStations/EagleEyeSAC
    chmod +x build_cs.sh
    ./build_cs.sh
    docker-compose up -d
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+      Restoring MongoDB after Relase        +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~
    mongorestore ./dump                                   | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "+          Test REST services	               +" | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    cd ~
    sleep 10
    testnode.sh	                                   	  | sudo tee -a $SRL
    end=`date +%s`
    runtime=$( echo "$end - $start" | bc -l )
    echo "$start start time, $end end time, $runtime runtime"
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
    echo "Release took $runtime seconds to execute"       | sudo tee -a $SRL
    echo "++++++++++++++++++++++++++++++++++++++++++++++" | sudo tee -a $SRL
            if whiptail --yesno "Send Email Notification?" 10 60; then
		    SlackExitMessage="*[ALERT!]* Deployment to $(hostname) completed in $runtime seconds. New *Strax* Version: *$(GetStraxVer)*, New *Node* Version: *($GetNodeVer)*"
                    sudo cat $SRL | mail -s "[`date +"%Y-%m-%d--%I:%M-%P"`] Strax Released to $(hostname)" fhumayun@groupcaretech.com,tmcclure@groupcaretech.com,sselvaraj@groupcaretech.com,hglass@groupcaretech.com,jzambrano@groupcaretech.com,tmargetta@groupcaretech.com,sadams@groupcaretech.com,jchin@groupcaretech.com
		    slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackExitMessage"
            else
                    echo "End of Release. No email sent.  Goodbye!"
            fi
else
  echo "Aborting Strax Release.  Goodbye!"
fi
