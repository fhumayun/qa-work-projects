# Kaizen

Kaizen is a Nodejs project that utilizes Cucumber and Allure to test the EagleEye SRM/SAC API and generate reports.
When run Kaizen will bring itself up inside a Docker container running Ubuntu 16.04 and begin running its tests.
First it will run Cucumber, a tool which runs BDD tests against our API and then generates Junit XML output. After the 
XML is generated it will be parsed by Allure which is a Maven plugin. Allure will take the results and generate a report in HTML.


- Running Kaizen:

./Kaizen/rundockertests.sh
