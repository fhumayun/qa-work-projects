echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+        git clone latest Strax              +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~
[ -d "strax" ] && echo "Strax already exists" && rm -rf strax && echo "Re-Cloning Latest Strax" && git clone https://github.com/GroupCareTech/strax.git || git clone https://github.com/GroupCareTech/strax.git
echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+       git clone SproutTrax for node.js     +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~/strax/docker_images/node
[ -d "SproutTrax" ] && echo "SproutTrax already exists" && rm -rf SproutTrax && echo "Re-Cloning SproutTrax" && git clone https://github.com/GroupCareTech/SproutTrax.git || git clone https://github.com/GroupCareTech/SproutTrax.git

