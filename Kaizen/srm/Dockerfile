FROM ubuntu:16.04

## Meta
MAINTAINER GroupCare Technologies LLC. <support@groupcaretech.com>

## Setup
EXPOSE 8080
WORKDIR /opt/workdir

# Make the container realize its running headless
ENV DEBIAN_FRONTEND noninteractive

# URL the tests will point to for the api
# Include port if needed Ex eedev.strax.co:8080
ENV TESTURL "https://api-dev.strax.co"

# Mongo host name
ENV MONGOHOST "localhost"

# Mongo port
ENV MONGOPORT "27017"

## Execute
COPY tests/pom.xml /opt/workdir/tests
RUN apt-get -qq update && apt-get install -y \
        git nodejs npm maven=3.3.9-3

CMD [ "./_testrunner.sh" ]
