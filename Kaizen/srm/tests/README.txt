Tests readme file
-----------------

$ cd /path/to/project/Kaizen/tests

run
$ cucumberjs
This should stick test output in target/allure-results
result file should look like
    tests/target/allure-results/<UUID>-testsuite.xml

then run
$ ./run-maven-script.sh

This will spin up a maven docker container and use it to generate the docs under
    tests/target/site/allure-maven-plugin.html

You can see the results of this locally by then calling
$ cd site/testResults
$ mv allure-reports/ allure-report/
$ rm allure-maven-plugin/img/tests_passed.jpg
$ cp ../../img/thumbs_up.jpg allure-report/allure-maven-plugin/img/tests_passed.jpg
$ allure report open