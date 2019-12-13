#!/bin/bash
cd ~/strax/strax/strax-test/strax-simulation/strax-device-sim-serenity
mvn -q clean post-integration-test -DskipTests=false &
