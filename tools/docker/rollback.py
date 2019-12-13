#!/usr/bin/env python3

"""
rollback.py

Use to find docker image versions for rolling back certain services
"""


# --- Setup

import requests
import json
import sys
import os

# Set these to authenticate with your private registry
# docker_registry_un = 'shanahanjrs'
# docker_registry_pw = 'tH3L3genD27'
docker_registry_un = 'strax'
docker_registry_pw = 'eei'

# Turn on debug statements
debug = False

# --- Vars

# Build auth tuple
docker_registry_auth = (docker_registry_un, docker_registry_pw)

# Docker Registry API endpoints
docker_registry_api_catalog = '/v2/_catalog'
docker_registry_api_image_tags = '/v2/{name}/tags/list'


# --- Lib

def list_repos(registry_url):
    """ List all repositories on the registry """

    if debug:
        print('[DEBUG] list_repos: registry_url: ' + registry_url)

    if registry_url == '':
        sys.exit('list_repos: registry_url empty')

    repo_list_url = registry_url + docker_registry_api_catalog
    if debug:
        print('[DEBUG] list_repos: repo_list_url: ' + repo_list_url)

    repo_list = requests.get(repo_list_url, auth=docker_registry_auth)

    if repo_list.status_code != 200:
        print('Status code: {status}'.format(status=repo_list.status_code))

    print(json.dumps(json.loads(repo_list.text), sort_keys=True, indent=4))


def list_repo_tags(repository_url, repository_name):
    """ List all docker image tags for a specific docker repo """

    if debug:
        print('[DEBUG] list_repo_tags: registry_url: ' + repository_url)
        print('[DEBUG] list_repo_tags: repository_name: ' + repository_name)

    if repository_url == '':
        sys.exit('list_repo_tags: repository_url empty')

    if repository_name == '':
        sys.exit('list_repo_tags: repository_name empty')

    repo_catalog_url = repository_url + docker_registry_api_image_tags.format(name=repository_name)
    if debug:
        print('[DEBUG] list_repo_tags: repo_catalog_url: ' + repo_catalog_url)

    repo_tags = requests.get(repo_catalog_url, auth=docker_registry_auth)

    if repo_tags.status_code != 200:
        print('Status code: {status}'.format(status=repo_tags.status_code))

    print(json.dumps(json.loads(repo_tags.text), sort_keys=True, indent=4))


def add_http_https(registry_url):
    """ Takes a url and adds HTTP or HTTPS """

    if debug:
        print('[DEBUG] add_http_https: registry_url: ' + registry_url)

    if registry_url == '':
        sys.exit('add_http_https: registry_url empty')

    http_or_https = input('http or https> ')

    if 'https' in http_or_https:
        return 'https://' + registry_url
    else:
        return 'http://' + registry_url


def guess_url_port(url):
    """ Takes a url and guesses the port based on http vs https
    Will only map (https->443) and (http->80)
    """

    if debug:
        print('[DEBUG] guess_url_port: url: ' + url)

    if url == '':
        sys.exit('guess_url_port: url empty')

    if url.startswith('https'):
        return '443'
    else:
        return '80'


def _check_for_dotfile():
    """ Checks for the existance of a .rollback file with URL and PORT inside """

    if debug:
        print('[DEBUG] check_for_dotfile called')

    cwd = os.getcwd()
    dotfile_filename = '.rollback'
    dotfile_abs_path = '{path}/{filename}'.format(path=cwd, filename=dotfile_filename)

    if os.path.exists(dotfile_abs_path):

        with open(dotfile_abs_path, 'r') as dotfile_obj:
            dotfile_contents = json.loads(dotfile_obj.read())
            dotfile_obj.close()

        return (dotfile_contents["url"], dotfile_contents["port"])

    else:
        return (None, None)


def main():
    """ Main """

    print('Docker registry info')

    if debug:
        print('[DEBUG] Debugging enabled')

    docker_registry_url, docker_registry_port = _check_for_dotfile()

    if docker_registry_url == None:
        docker_registry_url = input('url> ')

        if not docker_registry_url.startswith('http'):
            docker_registry_url = add_http_https(docker_registry_url)

    if docker_registry_port == None:
        docker_registry_port = input('port> ')

        if docker_registry_port == '':
            docker_registry_port = guess_url_port(docker_registry_url)

    # Build URL
    docker_registry_url = '{url}:{port}'.format(url=docker_registry_url, port=docker_registry_port)

    if debug:
        print('[DEBUG] main: docker_registry_url: ' + docker_registry_url)

    # List docker repos
    list_repos(docker_registry_url)

    # Select repository
    selected_repo = input('repo> ')
    list_repo_tags(docker_registry_url, selected_repo)


# --- Start

if __name__ == "__main__":
    main()