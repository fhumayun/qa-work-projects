PROJECT_NAME ?= SproutTest
ORG_NAME ?= dr.strax.co:5000
IMAGE_NAME ?= sprouttest
VERSION ?= 0.1

DOCKERUP := $(shell docker inspect -f {{.State.Running}} tester_sprouttest_1)

deploy:
	$(info Shutting down existing docker container...)
ifeq ($(DOCKERUP), true) 
	-docker-compose -p tester -f ./docker-compose.yml down --rmi all
else
	$(info Docker container not running, skipping shut-down process...)
endif

	$(info Bringing SproutTest container up...)
	docker-compose build
	docker-compose -p tester -f ./docker-compose.yml up

