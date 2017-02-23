Cucumer automation tests
---

### Cucumber automation scripts for CI/CD and regression/integration testing

#### Summary
These cuke scripts are built to be automatically run against the StraxRM api endpoints for user, event, and device management.
This will simulate a user interface call to the Strax engine and intelligently determine the latest status of our code based on the response received.
The results are then stored, and depending on the outcome our automated build process will either continue to the next step or stop the assembly line and alert us of a potential risk.

#### Running the regression/integration tests
To run: `$ ./entrypoint.sh` _\*This will run the test locally. Use OpsDeck to run via the latest docker container._

#### Automated test result path
XML results: `/path/to/application/tests/target/allure-results/<UUID>-testsuite.xml`

At this point `maven.sh` will generate an html report with the results.

#### Maven/Allure HTML report

You can see the results of this locally by then calling:

_\*This will be deprecated via the_ `deploymavenreport.sh` _script in the future._

```
$ cd site/testResults
$ mv allure-reports/ allure-report/
$ rm allure-maven-plugin/img/tests_passed.jpg
$ cp ../../img/thumbs_up.jpg allure-report/allure-maven-plugin/img/tests_passed.jpg
$ allure report open
```

_To be continued..._