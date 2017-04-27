#!/bin/bash
set +x
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down the Storm Container"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-dc.sh opsdeck_storm_1
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Tearing Down Storm Image"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
td-di.sh gccloud/storm
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Refreshing OpsDeck Repository"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
get_ops.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Refreshing Strax Repository"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
get_strax.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "        Building Storm Maven JARs"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax
mvn clean install -DskipTest
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Bringing Up Storm Docker Pieces"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/OpsDeck
sudo docker build -t=gccloud/storm --no-cache gccloud-storm/.
docker-compose up -d storm
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+      Bringing Up Kafka Topics"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/OpsDeck/gccloud-kafka/tools
./kt.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+     Bringing up the Storm Topology"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/storm-topologies
./deploy-safetrax.sh
