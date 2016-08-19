#!/bin/bash
# Start registrator
# John Shanahan

docker rm -f registrator0

docker run -d \
  --name registrator0 --net host \
  -v /var/run/docker.sock:/tmp/docker.sock \
  gliderlabs/registrator:v7 \
  consul://ci.strax.co:8500
