PROJECT_NAME ?= Kaizen
ORG_NAME ?= dr.strax.co:5000
IMAGE_NAME ?= kaizen
VERSION ?= 0.1

DOCKERUP := $(shell docker inspect -f {{.State.Running}} tester_kaizen_1)

PHONY: deploy

deploy:
	$(info Shutting down existing docker container...)
ifeq ($(DOCKERUP), true) 
	-docker-compose -p tester -f ./docker-compose.yml down --rmi all
else
	$(info Docker container not running, skipping shut-down process...)
endif

	$(info Bringing Kaizen container up...)
	docker-compose build
	docker-compose -p tester -f ./docker-compose.yml up

