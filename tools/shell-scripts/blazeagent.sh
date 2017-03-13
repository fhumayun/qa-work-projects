#!/bin/bash

export VERSION=stable
export API_KEY=247f2a58bd266d9351ee
export HARBOR_ID=57b71e67f168ec69423b60da
export A_ENVIRONMENT=https://a.blazemeter.com
export AGENT_FILES_URL=https://s3.amazonaws.com/blazemeter/install/ship-agent/stable
export AEGIR_ENABLED=1




sudo curl "${AGENT_FILES_URL}"/HarborInstall.sh | bash 2>&1 | tee -a /tmp/ship_install.log
