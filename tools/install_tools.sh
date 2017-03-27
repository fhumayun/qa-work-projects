#!/bin/bash

echo '================================================'
echo "$0"
echo '================================================'

# buildnum.sh
echo 'Copying buildnum.sh...'
cp shell-scripts/buildnum.sh /usr/local/bin
chmod +x /usr/local/bin/buildnum.sh

# dropbox_uploader.sh
echo 'Installing dropbox_uploader.sh...'
curl "https://raw.githubusercontent.com/andreafabrizi/Dropbox-Uploader/master/dropbox_uploader.sh" -o /usr/local/bin/dropbox_uploader.sh
chmod +x /usr/local/bin/dropbox_uploader.sh

# add timestamps to history
echo 'Run `sudo ./setup/historytimestamp.sh`'

echo 'Done...'
