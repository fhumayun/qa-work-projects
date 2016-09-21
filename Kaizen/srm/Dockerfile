FROM ubuntu:16.04

## Meta
MAINTAINER GroupCare Technologies LLC. <support@groupcaretech.com>

## Setup
EXPOSE 8080
WORKDIR /opt/workdir

ENV DEBIAN_FRONTEND noninteractive  # Make the container realize its running headless
ENV TESTURL "https://eedemo.strax.co"      # URL the tests will point to for the api
ENV TESTPORT "8080"                  # Port where the tests will point
ENV MONGOHOST "localhost"           # Mongo host name
ENV MONGOPORT "27017"               # Mongo port

## Execute
COPY tests/pom.xml /opt/workdir/tests
RUN apt-get -qq update && apt-get install -y \
        git nodejs npm maven=3.3.9-3

CMD [ "./_testrunner.sh" ]
