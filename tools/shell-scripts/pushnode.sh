#!/bin/bash
set +x
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down the Node Container"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-dc.sh sprouttrax_apiserver_1
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down Storm Image"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-di.sh strax/sprout
#echo "++++++++++++++++++++++++++++++++++++++++++++++"
#echo "        Refreshing OpsDeck Repository"
#echo "++++++++++++++++++++++++++++++++++++++++++++++"
#get_ops.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Refreshing Node Repository"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~
[ -d "strax" ] && echo "Strax already exists" 
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+       git clone SproutTrax for node.js     +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/docker_images/node
[ -d "SproutTrax" ] && echo "SproutTrax already exists" && rm -rf SproutTrax && echo "Re-Cloning SproutTrax" && git clone https://github.com/GroupCareTech/SproutTrax.git || git clone https://github.com/GroupCareTech/SproutTrax.git
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Bringing Up Node"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/docker_images/node/SproutTrax
docker build -t=strax/sprout --no-cache .
docker-compose -f docker-compose-beta.yml up -d
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Node Deployed"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "Type testnode.sh to see if node is up and passing"
