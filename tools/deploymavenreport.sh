#!/bin/bash

echo '================================================'
echo "$1"
echo '================================================'

if [[ -d /tmp/mavenreport ]]; then
    rm -rf /tmp/mavenreport
    echo "Removing stale maven files..."
fi

# Make sure dropbox-uploader is installed and in the path
echo "Checking node installed..."
if [[ $(which node | grep 'not found' | wc -l | xargs) > 0 ]]; then
    echo "Please install 'node' and try again..."
    exit 1
fi

# Create tmp dir
echo "Creating new maven temp dir..."
mkdir /tmp/mavenreport
cd /tmp/mavenreport

# Get name of latest report
newestreportname=$(dropbox_uploader.sh -q list "AllureReports/" | cut -d' ' -f4 | sort -r | head -1)

# Check filename length. Should never be less than 20 characters
if [[ ${#newestreportname} -lt 20 ]]; then
    echo "Filename: ${newestreportname} is too short..."
    exit
fi

# Download latest report
dropbox_uploader.sh download "AllureReports/${newestreportname}"

# Untar the report
tar -xzvf ${newestreportname}

# Remove current Allure Report container if its running
if [[ $(docker ps -a | grep mavenreport | wc -l | xargs) > 0 ]]; then
    docker rm -f mavenreport
    echo "Removing stale mavenreport container..."
fi

# Start report container
echo "Starting docker container..."
docker run --name mavenreport --restart always -v $(pwd)/allure-maven-plugin:/usr/share/nginx/html:ro -p 3280:80 -P -d nginx

echo "Done..."
