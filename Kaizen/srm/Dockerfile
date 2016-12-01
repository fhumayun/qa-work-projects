FROM ubuntu:16.04

## Meta
MAINTAINER John Shanahan <jshanahan@eagleeyeintelligence.com>

## Setup
WORKDIR /opt/workdir

# Make the container realize its running headless
ENV DEBIAN_FRONTEND noninteractive

# URL the tests will point to for the api
# Include port if needed Ex eedev.strax.co:8080
ENV TESTURL "https://api-dev.strax.co"
#ENV TESTURL "https://eedemo.strax.co:8080"
#ENV TESTURL "https://qa-api.strax.co"

## Execute
COPY tests/pom.xml /opt/workdir/tests
RUN apt-get -qq update && apt-get install -y \
        git nodejs npm
#        git nodejs npm maven=3.3.9-3

CMD [ "./_testrunner.sh" ]
