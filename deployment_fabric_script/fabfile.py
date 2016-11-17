# Continuous Delivery Fabric File
# John Shanahan

import imp
try:
    imp.find_module('fabric')
    from fabric.api import run
except ImportError:
    print('\033[91m')
    print('# Fabric is not installed. Please `pip install fabric`')
    print('# Exiting ...')
    print('\033[0m')
    exit()

def uname():
    run('uname -a')

def make_deploy_srm():
    # Deploy straxRM
    run('cd /usr/local/bin/OpsDeck/straxRM && git diff | tee && make deploy')

def make_deploy_sac():
    # Deploy SAC
    run('cd /usr/local/bin/OpsDeck/eesac && git diff | tee && make deploy')

def make_deploy_api():
    # Deploy apiserver
    run('cd /usr/local/bin/OpsDeck/apiserver && git diff | tee && make deploy')

def make_deploy_id():
    # Deploy idserver
    run('cd /usr/local/bin/OpsDeck/straxID && git diff |tee && make deploy')

def make_deploy_media():
    # Deploy mediaserver
    run('cd /usr/local/bin/OpsDeck/media/strax_media && git diff | tee && make deploy')

def make_deploy_playback():
    # Deploy playback
    run('cd /usr/local/bin/OpsDeck/playback && git diff | tee && make deploy')
