StormTopology="strax-safetrax"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+        Building latest Strax               +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "Stopping Storm Topology: $StormTopology"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
storm kill $StormTopology
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+Preparing ProtoBuff for $hostname"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax/strax-traxpack/src/main/java
rm -rf *
cd /home/ubuntu/strax/strax/strax-traxpack
./proto.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+Ensuring storm scope is set to provided"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax/strax-storm/strax-topologies/strax-storm-safetrax
sed -i 's/<!--<scope>provided<\/scope>-->/<scope>provided<\/scope>/g' pom.xml
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "Running the Maven Build Process"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/strax
mvn clean install -DskipTests
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+     Bringing up the Storm Topology"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/storm-topologies
./deploy-safetrax.sh
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "Build Complete"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
