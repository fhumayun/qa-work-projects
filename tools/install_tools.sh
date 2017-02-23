#!/bin/bash

echo '================================================'
echo "$0"
echo '================================================'

echo 'Copying buildnum.sh...'
cp buildnum.sh /usr/local/bin
chmod +x /usr/local/bin/buildnum.sh

echo 'Installing dropbox_uploader.sh...'
curl "https://raw.githubusercontent.com/andreafabrizi/Dropbox-Uploader/master/dropbox_uploader.sh" -o /usr/local/bin/dropbox_uploader.sh
chmod +x /usr/local/bin/dropbox_uploader.sh

echo 'Done...'
