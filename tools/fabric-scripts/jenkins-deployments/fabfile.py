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

def make_deploy_srm():
    # Deploy straxRM
    run('cd /usr/local/bin/OpsDeck/straxRM && docker login -u strax -p eei dr.strax.co:5000 | tee && make deploy')

def make_deploy_sac():
    # Deploy SAC
    run('cd /usr/local/bin/OpsDeck/eesac && docker login -u strax -p eei dr.strax.co:5000 | tee && make deploy')

def make_deploy_api():
    # Deploy apiserver
    run('cd /usr/local/bin/OpsDeck/apiserver && docker login -u strax -p eei dr.strax.co:5000 | tee && make deploy')

def make_deploy_id():
    # Deploy idserver
    run('cd /usr/local/bin/OpsDeck/straxID && docker login -u strax -p eei dr.strax.co:5000 |tee && make deploy')

def make_deploy_media():
    # Deploy mediaserver
    run('cd /usr/local/bin/OpsDeck/media/strax_media && docker login -u strax -p eei dr.strax.co:5000 | tee && make deploy')

def make_deploy_playback():
    # Deploy playback
    run('cd /usr/local/bin/OpsDeck/playback && docker login -u strax -p eei dr.strax.co:5000 | tee && make deploy')
