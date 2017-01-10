IPADDR=$(echo $SSH_CLIENT | awk '{print $1}')

echo ":u7981: *SSH* on \`$(hostname)\` at \`$(date)\` from ip addr <http://api.geoiplookup.net/?query=$IPADDR)|$IPADDR>" | slackcat -c strax-qa --stream --plain
