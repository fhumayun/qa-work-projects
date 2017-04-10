#!/bin/bash

echo '================================================'
echo "$0"
echo '================================================'
echo ''

# Install buildnum.sh
echo '[0] [INSTALL] buildnum.sh...'
cp shell-scripts/buildnum.sh /usr/local/bin
chmod +x /usr/local/bin/buildnum.sh
if [[ $? -eq 0 ]]; then echo -e "✅"; else echo -e "❌"; fi
echo ''

# Install dropbox_uploader.sh
echo '[1] [INSTALL] dropbox_uploader.sh...'
curl -s -S "https://raw.githubusercontent.com/andreafabrizi/Dropbox-Uploader/master/dropbox_uploader.sh" -o /usr/local/bin/dropbox_uploader.sh
chmod +x /usr/local/bin/dropbox_uploader.sh
if [[ $? -eq 0 ]]; then echo -e "✅"; else echo -e "❌"; fi
echo ''

# add timestamps to history
echo '[2] [WARNING] *!* This will NOT be automatically run *!*'
echo "     \`---- Please manually run \`sudo $(pwd)/setup/historytimestamp.sh\`"
echo ''

echo '[DONE]'
