FROM ubuntu:zesty

## Meta
MAINTAINER John Shanahan <jshanahan@eagleeyeintelligence.com>

## Setup
WORKDIR /opt/workdir

# Make the container realize its running headless
ENV DEBIAN_FRONTEND noninteractive

# URL the tests will point to for the api
# Include port if needed Ex eedev.strax.co:8080
# ENV TESTURL "https://api-dev.strax.co"
# ENV TESTURL "https://eedemo.strax.co:8080"
ENV TESTURL "https://qa-api.strax.co"

## Execute
COPY tests/ /opt/workdir/tests/
COPY package.json /opt/workdir
COPY entrypoint.sh /opt/workdir

# Install dependancies
RUN apt-get update && apt-get install -y nodejs npm 
#maven=3.3.9-3

# Link nodejs to node
RUN ln -s /usr/bin/nodejs /usr/bin/node > /dev/null && ln -s /usr/bin/nodejs /usr/local/bin/node > /dev/null

# Update npm and install packages
RUN npm install
RUN npm install -g cucumber@1.3.1

# Main
CMD [ "./entrypoint.sh" ]