#!/bin/bash

echo "[START]"
echo ''

# Install oh-my-zsh 
echo '[0] [INSTALL] zsh...'
sudo apt-get install -y zsh toilet cowsay
sudo sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
sudo sed -i "s@/bin/bash@/usr/bin/zsh@" /etc/passwd
if [[ $? -eq 0 ]]; then echo -e "[1] ✅"; else echo -e "[1] ❌"; fi
echo ''

# Install dotfiles
echo '[1] [INSTALL] zsh resource files...'
cp .zshrc ~/
cp .zprofile ~/
if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
echo ''

# Install buildnum.sh
echo '[2] [INSTALL] buildnum.sh...'
cp shell-scripts/buildnum.sh /usr/local/bin
chmod +x /usr/local/bin/buildnum.sh
if [[ $? -eq 0 ]]; then echo -e "[0] ✅"; else echo -e "[0] ❌"; fi
echo ''

# Install dropbox_uploader.sh
echo '[3] [INSTALL] dropbox_uploader.sh...'
curl -s -S "https://raw.githubusercontent.com/andreafabrizi/Dropbox-Uploader/master/dropbox_uploader.sh" -o /usr/local/bin/dropbox_uploader.sh
chmod +x /usr/local/bin/dropbox_uploader.sh
if [[ $? -eq 0 ]]; then echo -e "[1] ✅"; else echo -e "[1] ❌"; fi
echo ''

# add timestamps to history
echo '[4] [WARNING] *!* This will NOT be automatically run *!*'
echo "     \`---- Please manually run \`sudo $(pwd)/setup/historytimestamp.sh\`"
echo ''

echo '[INFO] If any other installations have to be added to this please email jshanahan@eagleeyeintelligence.com'
echo ''

echo '[DONE]'
