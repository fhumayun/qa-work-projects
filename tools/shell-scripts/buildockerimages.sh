ls -d */  | grep -v "SproutTrax" | grep -v "/kafka" | grep -v "mosquitto" | grep -v "mongo" | sort -n| awk '{print $NF}' | xargs -n1 sh -c 'cd $0 && pwd && ./*build.sh && ./*run.sh'
