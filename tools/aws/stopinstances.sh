#!/bin/bash

echo '================================================'
echo "$0"
echo '================================================'

if [[ -z "$1" ]]; then
    echo 'Need to specify an environment...'
    exit 10
fi
ENVIRONMENT="$1"

# msg (mongo/zookeeper/kafka)  demo:i-ec2eafdc  qa:i-8567c993  uat:i-6925ccf1
# storm                        demo:i-3edb590e  qa:i-75b63363  uat:i-b6c88fb8
# kurento                      demo:            qa:i-c46dc3d2  uat:
# ms                           demo:            qa:i-0308ac15  uat:
# media                        demo:i-c8164851  qa:i-0766c811  uat:i-5cd63fc4
# id                           demo:i-e526a7d5  qa:i-f502a6e3  uat:i-c9d53c51
# api                          demo:i-e328a9d3  qa:i-5fdf2049  uat:i-58db32c0
# srm                          demo:i-8f20a1bf  qa:i-770baf61  uat:i-503fcfc8
# sac                          demo:i-4926a779  qa:i-e808acfe  uat:i-6224cdfa
# pb                           demo:i-ae25a49e  qa:i-af08acb9  uat:i-4427cedc

case $ENVIRONMENT in
    demo)
        INSTANCES="i-c8164851 i-e526a7d5 i-e328a9d3 i-8f20a1bf i-4926a779 i-ae25a49e i-ec2eafdc i-3edb590e"
        ;;
    qa)
        INSTANCES="i-0766c811 i-f502a6e3 i-5fdf2049 i-770baf61 i-e808acfe i-af08acb9 i-75b63363 i-8567c993 i-c46dc3d2 i-0308ac15"
        ;;
    uat)
        INSTANCES="i-5cd63fc4 i-c9d53c51 i-58db32c0 i-503fcfc8 i-6224cdfa i-4427cedc i-6925ccf1 i-b6c88fb8"
        ;;
    *)
        echo 'Not a supported environment...'
        echo 'Choices: demo, qa, uat'
        exit 10
        ;;
esac

# Stop all instances
aws ec2 stop-instances --region us-east-1 --instance-ids ${INSTANCES}