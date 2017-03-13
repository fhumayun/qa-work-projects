echo "++++++++++++++++++++++++++++++++++++++++++++++"
echo "+        git clone latest Strax              +"
echo "++++++++++++++++++++++++++++++++++++++++++++++"
cd ~
[ -d "OpsDeck" ] && echo "OpsDeck already exists" && rm -rf OpsDeck && echo "Re-Cloning Latest Strax" && git clone https://github.com/GroupCareTech/OpsDeck.git || git clone https://github.com/GroupCareTech/OpsDeck.git
