#!/bin/bash
#Uncomment set -x command to display verbose debugging
#set -x
# ---
# Last Updated: 3/10/17 - fh
# - Fixed support for STX prefix in git branches
# - Fixed problem with .git extension breaking repo naming feature
# ---
# Last Updated: 3/30/17 - fh
# - Fixed problem with buildnum no longer picking up override
# ---
# Last update 11/Apr/17 - jrs
# - Changed the way buildnum generates the Major/Minor. Based it off of the current git branch now.
# - Ex STX-1.2 -> 1.2; STX-1.3 -> 1.3; STX-34.907 -> 34.907
# - If you "touch /tmp/X.Y" your build will get overridden by "X.Y"
# - if the current branch is "master" or "qa" it will get tagged with the NEWEST remote STX-X.Y branch.
# - if the branch is "master" or "qa" and there is NO existing remote STX-X.Y branch it will get a 0.0 tag.
# ---
# Last update 23/May/17 - fh
#-  Shifted prefix to global var declaration at top of the script
# - Corrected Repo renaming convention
# - Corrected Override prefix to match new versioning convention
# - Corrected Override logic to better find override file
# - Added more error handling
# - Won't run on unsupported Repos

# --- Global Vars here
export Prefix="STX"
export CurrentDir="$(pwd)"
export Match="ENV"

# Version Override feature
function override {
    rule ${bold}${red}=${reset}
    #- filePattern="STX-?(?:.[0-9]{1}){1,3}"
    filePattern="${Prefix}-*"
    tmpDir="tmp"
    overrideFileFind=$(ls /$tmpDir | grep ${filePattern})
    overrideFileVersion=$(ls /$tmpDir | grep ${filePattern} | cut -d'-' -f2-)
    overrideFile=$(echo ${overrideFileFind})
    if [ ! -f /$tmpDir/${overrideFile} ]; then
        echo "${bold}Using Default Branch version: ${bold}${green}$GitNewRelease ${reset}"
    else
        newBranchVersion=${overrideFileVersion}
        echo "${bold}${red}Branch Version Override Detected:${reset}${bold}${green}$newBranchVersion${reset}"
        export GitNewRelease=${newBranchVersion}
    fi
    rule ${bold}${red}=${reset}
}


# Print a horizontal rule
function rule {
    printf -v _hr "%*s" $(tput cols) && echo ${_hr// /${1--}}
}


# Print horizontal ruler with message
function rulem {
    # Fill line with ruler character ($2, default "-"), reset cursor, move 2 cols right, print message
    printf -v _hr "%*s" $(tput cols) && echo -en ${_hr// /${2--}} && echo -e "\r\033[2C$1"
}


# Buildnum help message
function buildnum_help {
    rule ${bold}${red}=${reset}
    # Print usage
    echo "${bold}${blue}Usage:  ./buildnum.sh <set jira flag 1>${reset}"
    echo ""
    echo "${bold}${blue}Strax version number generator${reset}"
    echo ""
    echo "${bold}${yellow}Options:"
    echo "  help - Print out help message"
    echo "  <dir> - Specify directory. -- Only remains for legacy reasons."
    echo "  <jira> - Specify whether to send new version number to jira or not (default yes)${reset}"
    echo ""
    echo "${bold}${green}usage: First argument (required) $(basename $0) path of Strax Repo${reset}"
    echo "${bold}${green}Second argument to send version number to Jira${reset}"
    echo "${bold}${blue}example: $(basename $0) 1${reset}"
    rule ${bold}${red}=${reset}
}


# escape slashes
function escape_slashes {
    sed 's/\//\\\//g'
}


# Get the name of the current git branch
function GetBranchName {
    git branch | grep \* | cut -d' ' -f 2
}


# Get the current iteration version from the current git branch
function GetIterationFromBranch {
    git branch | grep \* | cut -d' ' -f 2 | cut -d'-' -f 2
}


# change line
function ChangeLine {
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

# Add line
function AddLine {
    if [ "$(uname)" == "Darwin" ]; then
        # Do something under Mac OS X platform
        echo "❌ ${bold}${red}FAIL!!${reset} BuildNum cannot append on Mac OS X"
        echo "Please add ENV VERSION ${bold}${blue}${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})${reset} manually in the ${FileToSearch}"
    elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
        # Do something under GNU/Linux platform
        sed -i .bak "/${MATCH_PATTERN}/aENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})" ${FileToSearch}
    elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
        # Do something under Windows NT platform
        echo "❌ ${bold}${red}FAIL!!${reset} $(basename) is not ready for Windows"
    fi
}


# Fix name
function FixName {
    export GitRepoName=$(basename $(git remote show -n origin | grep Fetch | cut -d: -f2- | tr '[:lower:]' '[:upper:]' ))
    if [ "$GitRepoName" == "STRAX-ID" ]; then
        export GitRepoName="ID"
    fi
    if [ "$GitRepoName" == "STRAX-MEDIA" ]; then
        export GitRepoName="MEDIA"
    fi
    if [ "$GitRepoName" == "STRAX-API" ]; then
        export GitRepoName="API"
    fi
    if [ "$GitRepoName" == "STRAX-APP" ]; then
        # Todo Fix this?
        export GitRepoName="WAPP"
    fi
    echo ${GitRepoName}
}


# --- Setup

# Terminal colors
red=$(tput setaf 1)
green=$(tput setaf 2)
yellow=$(tput setaf 3)
blue=$(tput setaf 6)
reset=$(tput sgr0)
dim=$(tput dim)
bold=$(tput bold)


# --- Grab command line args

# If help
if [ "$1" == "help" ]; then
    buildnum_help
    exit 0
fi

# leave this. Seriously.
if [[ -z "$1" ]]; then
    workdir=0
else
    workdir=$1
fi

# Capture the "send version to jira" flag or default to "yes"
if [[ -z "$2" ]]; then
    jiraflag=0
else
    jiraflag=$2
fi


# --- Generate the version number

# Git Version generation vars
export GitHead=$(git rev-list master HEAD --count)

# Figure out Major/Minor version
export GitCurrentBranch=$(GetBranchName)

if [[ $GitCurrentBranch == "master" || $GitCurrentBranch == "qa" ]]; then
    # If master or qa branch then use the newest remote "STX" branch on github
    export GitNewRelease=$(git branch -a | grep ${Prefix} | cut -d/ -f 3 | sort | tail -1 | cut -d\- -f 2)
    if [ -z "$GitNewRelease" ]; then
        # If no STX branch exists on github then tag 0.0 because i have no idea what to do here.
        # TODO Figure out what to do here
        export GitNewRelease="0.0"
    fi
else
    # If not master or qa branch then use the current branch for major/minor:
    # STX-1.2 -> 1.2
    export GitNewRelease=$(GetIterationFromBranch)
fi

# Override the version number if we need to
override

# Build the rest of the version number
export BuildDateTime=$(date +"%Y%m%d.%H%M")
export BuildDate=$(date +%F)
export FileToSearch="Dockerfile"
export SearchTerm="VERSION"
export GitNameFixed=$(FixName)
export FriendlyVer="ENV VERSION ${Prefix}.${GitNewRelease}.${GitHead}.${GitNameFixed} (${BuildDate})"

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
echo "${dim}Loading Build Number:${reset} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ${reset}${dim} in $FileToSearch ${reset}"
rule ${bold}${green}=${reset}

 function PatchVersion {
    # Patch the VERSION variable in Dockerfile
    DockerFileLoc="${CurrentDir}/${FileToSearch}"
    export DockerfileOldVersion=$( cat "${DockerFileLoc}" | grep "${SearchTerm}" )
    echo "✅ ${bold}${green}PATCHING!!${reset}"
    echo "Before:  ${DockerfileOldVersion} ${reset}"
    ChangeLine
    export DockerfileNewVersion=$( cat "${DockerFileLoc}" | grep "${SearchTerm}" )
    echo "Now: ${bold}${green} ${DockerfileNewVersion} ${reset}"
 }

 function addVerString {
    # Patch the VERSION variable in Dockerfile
    DockerFileLoc="${CurrentDir}/${FileToSearch}"
    Match="ENV"
    echo "✅ ${bold}${green}ADDING!!${reset}"
    AddLine
    export DockerfileNewVersion=$( cat "${DockerFileLoc}" | grep "${SearchTerm}" )
    echo "Now: ${bold}${green} ${DockerfileNewVersion} ${reset}"
 }

# --- Main
if ! [[ "${GitNameFixed}" =~ ^(ID|MEDIA|API|WAPP)$ ]]; then
    rule ${bold}${red}=${reset}
    echo "❌ "  "${bold}${red}Not a valid Strax deployment build repo. No Versioning Done.${reset}"
    rule ${bold}${red}=${reset}
    exit
else
    # Check Dockerfile exists so we can patch it
    if [[ ! -f $(pwd)/${FileToSearch} ]]; then
        echo "❌ ${bold}${red}FAIL!!${reset} ${FileToSearch} not found! Unable to Patch Build Number!${reset}"
        exit 1
    else
        echo "✅ ${bold}${green}PASS!!${reset} Located ${FileToSearch}"
        # Check "VERSION" string is in Dockerfile -- This is what gets set during the build
         if ! grep -q ${SearchTerm} ${FileToSearch}; then
            echo "❌ ${bold}${red}FAIL!!${reset} ${SearchTerm} not found in ${FileToSearch}."
            addVerString
         else
            echo "✅ ${bold}${green}PASS!!${reset} Updating VERSION string in ${FileToSearch}"
            PatchVersion
        fi
    fi
fi


# Send new version to Jira releases page
if [[ -z ${jiraflag} && ${jiraflag} == "" ]]; then
    echo "${bold}${red}❌ ** Not Sending Build Number to Jira **${reset}"
else
    if [[ ${jiraflag} -eq 1 ]]; then
        rule ${bold}${red}=${reset}
        echo "✅ ${bold}${blue} Sending Build Number:${reset} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset} to JIRA"
        rule ${bold}${red}=${reset}

        # Get develop branch sha1 to fork new branch from
        VersionString="$Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate)"
        echo "VERSIONSTRING=${bold}${red} $Prefix.$GitNewRelease.$GitHead.$GitNameFixed ($BuildDate) ${reset}"

        # Call Jira to create new Version.
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

