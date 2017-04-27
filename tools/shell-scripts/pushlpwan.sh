#!/bin/bash
set +x
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down the LPWAN Container"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-dc.sh dockerimages_lpwan_1
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down LPWAN Image"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-di.sh dockerimages_lpwan
#echo "++++++++++++++++++++++++++++++++++++++++++++++"
#echo "        Refreshing OpsDeck Repository"
#echo "++++++++++++++++++++++++++++++++++++++++++++++"
#get_ops.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Refreshing Strax Repository"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+        git clone latest Strax              +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~
[ -d "strax" ] && echo "Strax already exists" && rm -rf strax && echo "Re-Cloning Latest Strax" && git clone https://github.com/GroupCareTech/strax.git || git clone https://github.com/GroupCareTech/strax.git
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+     Preparing ProtoBuff for $hostname      +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax/strax-traxpack/src/main/java
rm -rf *
cd /home/ubuntu/strax/strax/strax-traxpack
./proto.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Building Storm Maven JARs"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax
mvn clean install -DskipTest
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Bringing Up LPWAN MicroService"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
#cd ~/strax/docker_images/lpwan-kafka-bridge
#sudo docker build -t="strax/lpwankafkabridge" .
cd ~/strax/docker_images/
docker-compose up -d lpwan
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Testing Deployment"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
#termdown 30
echo "Run the command testlpwan.sh to test deployment"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      LPWAN Deployed"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
