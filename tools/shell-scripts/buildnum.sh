#!/bin/bash

# --- Lib

# Version Override feature
override(){
    rule "${bold}${red}=${reset}"
    filePattern="[0-9].[0-9]"
    tmpDir="tmp"
    overrideFile="/$tmpDir/$filePattern"
    if [ ! -f "${overrideFile}" ]; then
        echo "${bold}Using Default Branch version: ${bold}${green}$GitNewRelease ${reset}"
    else
        newBranchVersion=$(echo /tmp/[0-9].[0-9] | cut -d'/' -f3)
        echo "${bold}${yellow}Branch Version Override Detected:${reset} ${bold}${green}$newBranchVersion${reset}"
        export GitNewRelease=${newBranchVersion}
    fi
    rule "${bold}${red}=${reset}"
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


# Buildnum help message
buildnum_help() {
    rule "${bold}${red}=${reset}"
    # Print usage
    echo "Usage:  ./buildnum.sh <workdir> <jira>"
    echo ""
    echo "Strax version number generator"
    echo ""
    echo "Options:"
    echo "  help - Print out help message"
    echo "  <dir> - Specify directory other than self (default 'pwd')"
    echo "  <jira> - Specify whether to send new version number to jira or not (default yes)"
    echo "${bold}${yellow}usage: First argument (required) $(basename $0) path of Strax Repo${reset}"
    echo "${bold}${green}Second argument to send version number to Jira${reset}"
    echo "${bold}${blue}example: $(basename $0) . 1${reset}"
    rule "${bold}${red}=${reset}"
}


# escape slashes
function escape_slashes {
    sed 's/\//\\\//g'
}


# change line
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
        sed -i "/VERSION/cENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})" ${FileToSearch}
    elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
        # Do something under Windows NT platform
        echo "${bold}${red}FAIL!!${reset} $(basename) is not ready for Windows"
    fi
}


# Fix name
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
    if [ "$GitRepoName" == "STRAX-APP" ]; then
        # Todo Fix this?
        export GitRepoName="APP"
    fi
    echo ${GitRepoName}
}


# --- Setup

#set -x

# Terminal colors
red=$(tput setaf 1)
green=$(tput setaf 2)
yellow=$(tput setaf 3)
blue=$(tput setaf 6)
reset=$(tput sgr0)
dim=$(tput dim)
bold=$(tput bold)

# Grab command line args

# If help
if [ "$1" == "help" ]; then
    buildnum_help
    exit 0
fi

# Capture work directory or use current dir if not specified
if [[ -z "$1" ]]; then
    workdir=$(pwd)
else
    workdir=$(dirname "$1")
fi

# Capture the "send version to jira" flag or default to "yes"
if [[ -z "$2" ]]; then
    jiraflag=1
else
    jiraflag=$2
fi

# Version generation vars
export Prefix="STX"
export GitHead=$(git rev-list master HEAD --count)
export GitNewRelease=$(basename $(git branch -a | grep "remotes" | grep "v[0-9].*" | awk -F "/" '{ print $3 }' | cut -dv -f2- | tr -d '\r' | tail -1))
override
export BuildDateTime=$(date +"%Y%m%d.%H%M")
export BuildDate=$(date +%F)
export FileToSearch="Dockerfile"
export SearchTerm="VERSION"
export GitNameFixed=$(FixName)
export FriendlyVer="ENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})"


# --- Main

# Check Dockerfile exists
if [ ! -f $(pwd)/${FileToSearch} ]; then
    echo "${bold}${red}FAIL!!${reset} $FileToSearch not found! Unable to Patch Build Number!"
    exit 1
fi

# Check "VERSION" string is in Dockerfile
if ! grep -q ${SearchTerm} ${FileToSearch}; then
    echo "${bold}${red}FAIL!!${reset} $SearchTerm not found in $FileToSearch"
    exit 1
fi

# Print debug info
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

# Patch the VERSION variable in Dockerfile
export DockerfileOldVersion=$( cat "$FileToSearch" | grep "$SearchTerm" )
echo "${bold}${green}PASS!!${reset} Version Info found in $FileToSearch"
echo "Before: ${bold}${yellow} ${DockerfileOldVersion} ${reset}"
change_line
export DockerfileNewVersion=$( cat "$FileToSearch" | grep "$SearchTerm" )
echo "Now: ${bold}${green} ${DockerfileNewVersion} ${reset}"


# Do/dont sent new version to Jira releases page
# Todo Should the "-z jiraflag && ..." be a "&&" or "||" ?
if [[ -z ${jiraflag} && ${jiraflag} == "" ]]; then
    echo "${bold}${red}** Not Sending Build Number to Jira **${reset}"
else
    if [[ ${jiraflag} -eq 1 ]]; then
        rule ${bold}${red}=${reset}
        echo "${bold}${blue} Sending Build Number:${reset}${bold}${yellow} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset} to JIRA"
        rule ${bold}${red}=${reset}

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
