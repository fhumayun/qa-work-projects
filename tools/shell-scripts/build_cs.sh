CS_HOME="/home/ubuntu"
CS_DIR="CentralStation/safetrax/stgui"
echo "Locating Dockerfile at $CS_HOME/$CS_DIR"
echo "Building IER CentralStation Docker Image"
docker build -t=strax/safecs $CS_HOME/$CS_DIR/.
echo "Launching IER Central Station Docker Container"
cd $CS_HOME/$CS_DIR
docker-compose up -d
