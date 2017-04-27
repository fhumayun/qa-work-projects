    SlackChannel="strax-deployments"
    SlackIcon=":vertical_traffic_light:"
    SlackName="DeployBot"
    StormShPath="/home/ubuntu/strax/storm-topologies/"
    cd $StormShPath
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    echo "          Storm Topology Checker"
    echo "++++++++++++++++++++++++++++++++++++++++++++++"
    StormActiveState="ACTIVE"
    StormTopGetName=$(storm list | grep $StormActiveState | awk {'print $1'})
    StormTopStatus=$(storm list | grep $StormActiveState | awk {'print $2'})
    echo "Topology Name: ${StormTopGetName}"
    echo "Toplogy State: ${StormTopStatus}"
    StormTopDropSlack(){
        SlackTopDrop="*[ALERT!]* Storm Toplogy: *$StormTopGetName* on *$(hostname)* is Preparing for *SHUTDOWN*.  Emails, SMS, and PUSH Notifications will be now be *OFFLINE*"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "      Sent Storm Top Drop Slack Message"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackTopDrop"
    }
    StormTopUpSlack(){
        SlackTopUp="*[RESTORED]* Storm Toplogy: *${StormTopGetName}* on *$(hostname)* state is: *${StormActiveState}*.  Emails, SMS, and PUSH Notifications are now *ONLINE*"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "      Sent Storm Top Up Slack Message"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        slackcli -h $SlackChannel -u $SlackName -e $SlackIcon -m "$SlackTopUp"
    }
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
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "Pre-requisites are satisfied"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
    }
    StormExec=$(ls | grep $(hostname).sh)
    echo "Toplogy Runner: $StormExec"
    if [ "${StormTopStatus}" = "${StormActiveState}" ];
        then
        StormTopDropSlack
        echo "$StormTopGetName is currently ACTIVE on $(hostname).  Preparing for Shutdown"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "     Bringing Down Storm $StormTopGetName     "
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        storm kill $StormTopGetName
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "Bringing Up Storm $StormTopGetName on $(hostname)"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
    	StormTopBuilder
    	sleep 10
        cd $StormShPath
        ./$StormExec
        StormTopUpSlack
    else
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "No Storm Topologies were found running"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        echo "Bringing Up Storm $StormTopGetName on $(hostname)"
        echo "++++++++++++++++++++++++++++++++++++++++++++++"
        StormTopBuilder
    	sleep 10
    	cd $StormShPath
        ./$StormExec
        StormTopUpSlack
    fi
