#!/bin/bash

printf "Checking UID...\n"
if (( EUID != 0 )); then
	echo "You must be root to do this..."
	exit 10
else
	printf "User is root ...\n"
fi

var=HISTTIMEFORMAT="%d/%m/%y %T "

echo "" >> /etc/profile
echo "${var}" >> /etc/profile

echo "Done..."
