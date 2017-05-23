#!/bin/bash

echo "[START]"
echo ''

# Install buildnum.sh
echo '[0] [INSTALL] buildnum.sh...'
# Remove previous file copies and ensure symlink is establish instead
shelldir="shell-scripts"
ulbDir="/usr/local/bin"
ulbDirFile="buildnum.sh"
ulbDirPath="${ulbDir}/${ulbDirFile}"
if [ -L ${ulbDirPath} ] ; then
   if [ -e ${ulbDirPath} ] ; then
      echo "✅ Good link"
   else
      echo " ❌ Broken link"
      sudo ln -s ${shelldir}/${ulbDirFile} ${ulbDir}
      sudo chmod +x ${ulbDirPath}
        if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
        echo ''
   fi
elif [ -e ${ulbDirPath} ] ; then
   echo " ❌ Not a link"
   echo "Replacing with symlink"
   sudo rm -f ${ulbDirPath}
   sudo ln -s ${shelldir}/${ulbDirFile} ${ulbDir}
   sudo chmod +x ${ulbDirPath}
     if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
     echo ''
else
   echo " ❌ Missing"
   echo "Adding buildnum symlink"
   sudo ln -s ${shelldir}/${ulbDirFile} ${ulbDir}
   sudo chmod +x ${ulbDirPath}
     if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
     echo ''
fi

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
sudo cp dotfiles/.zshrc ~/
sudo cp dotfiles/.zprofile ~/
if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
echo ''

# add timestamps to history
echo '[2] [WARNING] *!* This will NOT be automatically run *!*'
echo "     \`---- Please manually run \`sudo $(pwd)/setup/historytimestamp.sh\`"
echo ''

echo '[INFO] If any other installations have to be added to this please email jshanahan@eagleeyeintelligence.com'
echo ''

echo '[DONE]'
