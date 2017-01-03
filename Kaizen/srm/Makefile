USER = strax
PROJECTNAME = kaizen
ORGNAME = dr.strax.co:5000
IMAGENAME = cucumber_srm
VERSION = latest

DOCKERUP := $(shell docker inspect -f {{.State.Running}} tester_kaizen_1)

.PHONY: build latest deploy

build:
	$(info make build)
	$(info Building Kaizen docker image...)
	docker build .
	$(info Removing image...)

latest:
	$(info make latest)
	$(info Building Kaizen docker image...)
	docker build --rm -q -t $(ORGNAME)/$(IMAGENAME):$(VERSION) -f Dockerfile .
	$(info Pushing image...)
	docker push $(ORGNAME)/$(IMAGENAME)
	$(info Removing image...)
	docker rmi -f `docker images -q $(ORGNAME)/$(IMAGENAME):latest`

deploy:
	$(info make deploy)
	$(info MOVE THIS RECIPIE TO OPSDECK (╯°□°)╯ ︵┻━┻)
	$(info Shutting down existing docker container...)
ifeq ($(DOCKERUP), true)
	-docker-compose -p tester -f docker-compose.yml down --rmi all
else
	$(info Docker container not running, skipping shut-down process...)
endif

  # when dockerized/OpsDeck:
	# $(info Updating and running Kaizen...)
	# docker pull $(ORGNAME)/$(IMAGENAME):$(VERSION)
	# docker-compose -p $(PROJECTNAME) -f docker-compose.yml up

	# when local:
	$(info Bringing Kaizen container up...)
	docker-compose build
	docker-compose -f ./docker-compose.yml up
