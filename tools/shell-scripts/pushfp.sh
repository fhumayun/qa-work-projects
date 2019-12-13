#!/bin/bash
set +x
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down the FILE PUSHER Container"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-dc.sh dockerimages_lpwanfp_1
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down FILE PUSHER Image"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-di.sh dockerimages_lpwanfp
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
echo "        Building Storm Maven JARs"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax
mvn clean install -DskipTest
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Bringing Up LPWAN-FP MicroService"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/docker_images/lpwan-file-pusher
sudo docker build -t="strax/lpwanfilepusher" .
cd ~/strax/docker_images/
docker-compose up -d lpwanfp
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Testing Deployment"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
#termdown 30
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      FIlE-PUSHER Deployed"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
