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

# Version Override feature
override(){
	rule ${bold}${red}=${reset}
	filePattern="[0-9].[0-9]"
	tmpDir="tmp"
	overrideFile="/$tmpDir/$filePattern"
	if [ ! -f ${overrideFile} ]
	then
		echo "${bold}${white}Using Default Branch version: ${bold}${green}$GitNewRelease ${reset}"
	else
		newBranchVersion=$(echo /tmp/[0-9].[0-9] | cut -d'/' -f3)
		echo "${bold}${yellow}Branch Version Override Detected:${reset} ${bold}${green}$newBranchVersion${reset}"
		# Extract version digits
		# export Major=$(echo ${overrideFile}| cut -d'.' -f1)
		# export Minor=$(echo ${overrideFile}| cut -d'.' -f1)
		export GitNewRelease=$newBranchVersion
	fi
	rule ${bold}${red}=${reset}
}

# Print a horizontal rule
rule() {
	printf -v _hr "%*s" $(tput cols) && echo ${_hr// /${1--}}
}

# Print horizontal ruler with message
rulem ()  {
	# Fill line with ruler character ($2, default "-"), reset cursor, move 2 cols right, print message
	printf -v _hr "%*s" $(tput cols) && echo -en ${_hr// /${2--}} && echo -e "\r\033[2C$1"
}



# Making a Help feature

help() {
	rule ${bold}${red}=${reset}
	echo "${bold}${yellow}usage: First argument (required) $(basename $0) path of Strax Repo${reset}"
	echo "${bold}${green}Second argument to send version number to Jira${reset}"
	echo "${bold}${blue}example: $(basename $0) . 1${reset}"
    rule ${bold}${red}=${reset}
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

if [ $# -lt 1 ]; then
	help
	exit 1
fi

# Common Variables
export Prefix="STX"
export GitHead=$(git rev-list master HEAD --count $workdir)
FixName(){
	export GitRepoName=$(basename $(git remote show -n origin | grep Fetch | cut -d: -f2- | tr '[:lower:]' '[:upper:]' ))
	if [ "$GitRepoName" == "STRAXID" ]; then 
		export GitRepoName="ID"
    fi
	if [ "$GitRepoName" == "STRAXMEDIA" ]; then 
		export GitRepoName="MEDIA"
    fi
	if [ "$GitRepoName" == "SACPLAYBACK" ]; then 
		export GitRepoName="PB"
    fi
	if [ "$GitRepoName" == "EAGLEEYESAC" ]; then 
		export GitRepoName="SAC"
	fi
	if [ "$GitRepoName" == "STRAXRM" ]; then 
		export GitRepoName="SRM"
	fi
	if [ "$GitRepoName" == "SPROUTTRAX" ]; then 
		export GitRepoName="API"
	fi
	echo $GitRepoName
}
export GitNewRelease=$(basename $(git branch -a | grep "remotes" | grep "v[0-9].*" | awk -F "/" '{ print $3 }' | cut -dv -f2- | tr -d '\r' | tail -1))
override
export BuildDateTime=$(date +"%Y%m%d.%H%M")
export BuildDate=$(date +%F)
export FileToSearch="Dockerfile"
export SearchTerm="VERSION"
export GitNameFixed=$(FixName)
export FriendlyVer="ENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})"

# Inert Variables
export Major=0
export Minor=$PT_IT

# Inert feature: Get current sprint iteration from pivotal tracker
export TOKEN='996c058ea4864d8ba211a5f445285230'
export PROJECT_ID=1202356
PT_IT=$(curl -sS -X GET -H "X-TrackerToken: $TOKEN" "https://www.pivotaltracker.com/services/v5/projects/$PROJECT_ID/iterations?limit=1&scope=current" | grep '"number"' | awk -F":" {'print $2'} | cut -d, -f1-1 | sed -e 's/^[[:space:]]*//')

echo "Current Sprint Number:${blue} $PT_IT ${reset}"
echo "Git Head Counter: ${blue} $GitHead ${reset}"
echo "Git Repo Name: ${blue} $GitNameFixed ${reset}"
echo "Git Release Branch: ${blue} $GitNewRelease ${reset}"
echo "Prefix Keyword: ${blue} $Prefix ${reset}"
echo "Date/Time: ${blue} $BuildDateTime ${reset}"
echo "Date: ${blue} $BuildDate ${reset}"
echo "Build Prop Version: ${yellow} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ${reset}"
echo "Jira Version: ${yellow} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset}"
echo "$Prefix.$GitNewRelease.$GitHead.$GitNameFixed" >build.prop
rule ${bold}${green}=${reset}
echo "${dim}Loading Build Number:${reset}${bold}${yellow} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ${reset}${dim} in $FileToSearch ${reset}"
rule ${bold}${green}=${reset}
if [ -f $workdir/$FileToSearch ]; then
	#echo -e "\e[1m\e[92m\e[5mPASS!!\e[0m Dockerfile found! Patching Build Number..."
	echo "${bold}${green}PASS!!${reset} $FileToSearch found"
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
	
	if [[ -z ${jiraflag} && ${jiraflag} == "" ]]; then
		echo "${bold}${red}** Not Sending Build Number to Jira **${reset}"
	else
		if [[ ${jiraflag} -eq 1 ]]; then
			rule ${bold}${red}=${reset}
			echo "${bold}${blue} Sending Build Number:${reset}${bold}${yellow} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset} to JIRA"
			rule ${bold}${red}=${reset}

			# Test variable scope
			#echo "Prefix Keyword: ${red} $Prefix ${reset}"
			#echo "Major: ${red} $Release ${reset}"
			#echo "Release: ${red} $Release ${reset}"
			#echo "GitName: ${red} $GitRepo ${reset}"
			#echo "Date/Time: ${red} $DateTime ${reset}"

			# Get develop branch sha1 to fork new branch from
			VersionString="$Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate)"
			echo "VERSIONSTRING=${bold}${red} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset}"

			# Call Jira to create new Verion.
			CurlURL="https://eagleeyeintel.atlassian.net/rest/api/2/version"
			CurlType="Content-Type: application/json"
			CurlAuth="Authorization: Basic Zmh1bWF5dW5AZ3JvdXBjYXJldGVjaC5jb206Y2FyZXRlYW0="
			CurlCache="Cache-Control: no-cache"
			CurlDATA='{
                        	"project": "'${Prefix}'",
                        	"name" : "'${VersionString}'",
                        	"description": "",
                        	"userReleaseDate": "",
                        	"userStartDate": ""
                    	}'
			CurlResponse=$(curl -s -g -X POST -H "$CurlType" -H "$CurlAuth" -H "$CurlCache" -d "$CurlDATA" "$CurlURL")
		fi
	fi
else
	#echo -e "\e[31m\e[1m\e[5mFAIL!!\e[0m $FileToSearch not found! Unable to Patch Build Number!"
	echo "${bold}${red}FAIL!!${reset} $FileToSearch not found! Unable to Patch Build Number!"
fi