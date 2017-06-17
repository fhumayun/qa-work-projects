#!/bin/bash
set -x
scriptName="$(basename $0)"
lastModified=`date -r $scriptName`
echo "This file was last modified on: $lastModified" 
echo "[START]"
echo ''

# Install buildnum.sh
echo '[0] [INSTALL] buildnum.sh...'
# Remove previous file copies and ensure symlink is establish instead
export homeDir=$HOME
export shellDir="$homeDir/qa/tools/shell-scripts"
export dotDir="$homeDir/qa/tools/dotfiles"
export ulbDir="/usr/local/bin"
export ulbDirFile="buildnum.sh"
export ulbDirPath="${ulbDir}/${ulbDirFile}"
export matchType="*"
sudo chown -R ubuntu:ubuntu ${ulbDir}
sudo chmod g+rwx ${ulbDir}
cd $shellDir
function SymLinker {
        
        for files in $(find . -name "*" | cut -d'/' -f2);
        do 
            ln -s $shellDir/$files /usr/local/bin;
        done
}
if [ -L ${ulbDirPath} ] ; then
   if [ -e ${ulbDirPath} ] ; then
      echo "✅ Good link"
   else
      echo " ❌ : Broken link"
      sudo rm -f ${ulbDirPath}
      SymLinker
      if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
      echo ''
   fi
elif [ -e ${ulbDirPath} ] ; then
   echo " ❌ : Not a link"
   echo "Replacing with symlink"
   sudo rm -f ${ulbDirPath}
   SymLinker
     if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
     echo ''
else
   echo " ❌ : Missing"
   echo "Adding buildnum symlink"
   cd ${shelldir}
   SymLinker
     if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
     echo ''
fi
exit
# Install dropbox_uploader.sh
echo '[1] [INSTALL] dropbox_uploader.sh...'
sudo curl -s -S "https://raw.githubusercontent.com/andreafabrizi/Dropbox-Uploader/master/dropbox_uploader.sh" -o /usr/local/bin/dropbox_uploader.sh
sudo chmod +x /usr/local/bin/dropbox_uploader.sh
if [[ $? -eq 0 ]]; then echo -e "[1] ✅"; else echo -e "[1] ❌"; fi
echo ''

# Install oh-my-zsh 
echo '[3] [INSTALL] zsh...'
sudo apt-get install -y zsh toilet cowsay
sudo sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
sudo sed -i "s@/bin/bash@/usr/bin/zsh@" /etc/passwd
if [[ $? -eq 0 ]]; then echo -e "[1] ✅"; else echo -e "[1] ❌"; fi
echo ''

# Install dotfiles
echo '[4] [INSTALL] zsh resource files...'
cd $dotDir
cp zshrc ~/.zshrc
cp zprofile ~/.zprofile
if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
echo ''

# add timestamps to history
echo '[2] [WARNING] *!* This will NOT be automatically run *!*'
echo "     \`---- Please manually run \`sudo $(pwd)/setup/historytimestamp.sh\`"
echo ''

echo '[INFO] If any other installations have to be added to this please email jshanahan@eagleeyeintelligence.com'
echo ''

echo '[DONE]'
