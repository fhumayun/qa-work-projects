#!/bin/bash

#Uncomment set -x command to display verbose debugging
#set -x

# Terminal colors
red=$(tput setaf 1)
green=$(tput setaf 2)
yellow=$(tput setaf 3)
blue=$(tput setaf 6)
reset=$(tput sgr0)
dim=$(tput dim)
bold=$(tput bold)

#Capture directory passed as argument.
workdir=$(dirname "$1")

# Print a horizontal rule
rule() {
	printf -v _hr "%*s" $(tput cols) && echo ${_hr// /${1--}}
}

# Print horizontal ruler with message
rulem ()  {
	# Fill line with ruler character ($2, default "-"), reset cursor, move 2 cols right, print message
	printf -v _hr "%*s" $(tput cols) && echo -en ${_hr// /${2--}} && echo -e "\r\033[2C$1"
}

function escape_slashes {
    sed 's/\//\\\//g' 
}

function change_line {
	if [ "$(uname)" == "Darwin" ]; then
    	# Do something under Mac OS X platform
		local OLD_LINE_PATTERN=${SearchTerm}
		local NEW_LINE=${FriendlyVer}
		local FILE=${FileToSearch}
		local NEW=$(echo "${NEW_LINE}" | escape_slashes)
		sed -i .bak '/'"${OLD_LINE_PATTERN}"'/s/.*/'"${NEW}"'/' "${FILE}"
		mv "${FILE}.bak" /tmp/
	elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
		# Do something under GNU/Linux platform
		sed -i "/VERSION/cENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})" $FileToSearch
	elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
		# Do something under Windows NT platform
		echo "${bold}${red}FAIL!!${reset} $(basename) is not ready for Windows"
	fi
}

# Making a Help feature

help() {
	rule ${bold}${red}=${reset}
	echo "${bold}${yellow}usage: First argument (required) instance name: e.g. QA, UAT, demo, PBSO${reset}"
    rule ${bold}${red}=${reset}
}

if [ $# -lt 1 ]; then
	help
	exit 1
fi

if [ $1 = "QA" ]; then
	echo "${bold}${green}PATCHING ${reset} $FileToSearch found"
	if grep -q ${SearchTerm} ${FileToSearch}; then
		export DockerfileOldVersion=$( cat "$FileToSearch" | grep "$SearchTerm" )
   		echo "${bold}${green}PASS!!${reset} Version Info found in $FileToSearch"
	    echo "Before: ${bold}${yellow} ${DockerfileOldVersion} ${reset}"
		change_line
		export DockerfileNewVersion=$( cat "$FileToSearch" | grep "$SearchTerm" )
		echo "Now: ${bold}${green} ${DockerfileNewVersion} ${reset}"
	else
   		echo "${bold}${red}FAIL!!${reset} $SearchTerm not found in $FileToSearch"
		exit
	fi
    