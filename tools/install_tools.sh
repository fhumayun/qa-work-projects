#!/bin/bash

echo "[START]"
echo ''

# Install buildnum.sh
echo '[0] [INSTALL] buildnum.sh...'
sudo ln -s shell-scripts/buildnum.sh /usr/local/bin/
sudo chmod +x /usr/local/bin/buildnum.sh
if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
echo ''

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
