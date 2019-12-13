#!/bin/bash
set +x
export HomeDir=${HOME}
export CSPath="$HomeDir/CentralStations"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "          Time to Git CentralStations         "
echo "++++++++++++++++++++++++++++++++++++++++++++++"

function git_get(){
  declare -A array
  array[EagleEyeCSUI]="git clone https://github.com/GroupCareTech/EagleEyeCSUI.git"
  array[EagleEyeSAC]="git clone https://github.com/GroupCareTech/EagleEyeSAC.git"
  array[SafeTraxCSUI]="git clone https://github.com/GroupCareTech/SafeTraxCSUI.git"

  echo "++++++++++++++++++++++++++++++++++++++++++++++"
  echo "Testing whether $1 has been cloned"
  echo "++++++++++++++++++++++++++++++++++++++++++++++"
  if [ -d "$1" ]; then
  # Control will enter here if $DIRECTORY exists.
	echo "$1 already exists.  Blowing it away..."
	rm -rf $CSPath/$1
	echo "Re-cloning $1"
	${array[$1]}
  else
	echo "$1 doesn't exist."
	echo "Cloning $1"
	${array[$1]}
  fi
}

main(){
  echo "Checking for $CSPath"
  echo "++++++++++++++++++++++++++++++++++++++++++++++"
  if [ -d "CentralStations" ]; then
	echo "CentralStations already exists"
	cd "$CSPath"
	git_get EagleEyeCSUI
	git_get EagleEyeSAC
	git_get SafeTraxCSUI
  else
	echo "CentralStations does not exist"
	mkdir $CSPath
	cd "$CSPath"
	git_get EagleEyeCSUI
	git_get EagleEyeSAC
	git_get SafeTraxCSUI
  fi
}

main
