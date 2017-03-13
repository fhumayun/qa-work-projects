sudo docker run \
    -d \
    -e ENV_DOCKER_REGISTRY_HOST=https://dr.strax.co \
    -e ENV_DOCKER_REGISTRY_PORT=5000 \
    -p 8080:80 \
    konradkleine/docker-registry-frontend:v2
