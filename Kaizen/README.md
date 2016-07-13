# Kaizen

![Kaizen Logo](http://i.imgur.com/9Z1BFWs.png)

###### Kaizen - [kahy-zen] A business philosophy or system that is based on making positive changes on a regular basis, as to improve productivity.

---

Kaizen is a Nodejs project that utilizes Cucumber and Allure to test the EagleEye SRM/SAC API and generate reports. Over time The Kaizen Project will evolve to encompass many other aspects of testing, quality assurance, and continuous improvement.
When run Kaizen will bring itself up inside a Docker container running Ubuntu 16.04 and begin running its tests.
First it will run Cucumber, a tool which runs BDD tests against our API and then generates Junit XML output. After the 
XML is generated it will be parsed by Allure which is a Maven plugin. Allure will take the results and generate a report in HTML.


## Running Kaizen:
- https://slimwiki.com/gct/running-kaizen-tests
./rundockertests.sh

## Kaizen, under the hood
- https://slimwiki.com/gct/srm-test-code
