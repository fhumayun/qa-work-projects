SRM cucumberjs scripts
---

These cuke scripts are built to be automatically run against the StraxRM api endpoints for user, event, and device management.
This will simulate a user interface call to the Strax engine and intelligently determine the latest status of our code based on the response received.
The results are then stored, and depending on the outcome our automated build process will either continue to the next step or stop the assembly line and alert us of a potential risk.

To run: `./entrypoint.sh`

XML results: `/path/to/application/tests/target/allure-results/<UUID>-testsuite.xml`

Then maven.sh will generate an html report with the results.

You can see the results of this locally by then calling:
```
$ cd site/testResults
$ mv allure-reports/ allure-report/
$ rm allure-maven-plugin/img/tests_passed.jpg
$ cp ../../img/thumbs_up.jpg allure-report/allure-maven-plugin/img/tests_passed.jpg
$ allure report open
```
