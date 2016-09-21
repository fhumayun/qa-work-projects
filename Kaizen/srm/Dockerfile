FROM ubuntu:16.04

## Meta
MAINTAINER GroupCare Technologies LLC. <support@groupcaretech.com>

## Setup
EXPOSE 8080
WORKDIR /opt/workdir
ENV DEBIAN_FRONTEND noninteractive

## Execute
COPY tests/pom.xml /opt/workdir/tests
RUN apt-get -qq update && apt-get install -y \
        git nodejs npm maven=3.3.9-3

CMD [ "./_testrunner.sh" ]
