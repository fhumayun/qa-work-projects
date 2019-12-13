#!/bin/bash
echo "Testing whether $1 image has been built!"
for images in `docker images | awk '{print $1}' | awk -F "/" '{print $2}' | grep $1`
do
        if [ "$images"=="$1" ];
        then
                echo "Docker Image: "$images
                echo $1" matches the docker image "$images
        else
                echo $1" does not match any docker images"
                dpa
        fi
done

