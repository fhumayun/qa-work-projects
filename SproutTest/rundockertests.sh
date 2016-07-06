#!/bin/bash

PWD=`pwd`

#if [ -d "SproutTrax" ]; then
#    cd SproutTrax
#    git reset --hard
#    git pull
#    cd ..
#else
#    git clone https://github.com/gct-john/SproutTrax.git
#fi

make deploy
