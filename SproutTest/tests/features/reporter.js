var reporter = require('cucumberjs-allure-reporter');

reporter.config(
    {
        labels: {
            feature: [ 'create', 'read', 'update', 'delete' ],
            story: [ 'event', 'account', 'fidget', 'user', 'participant' ],
            severity: [ 'happy', 'angry', 'greedy' ]
        },
        targetDir: "target/allure-results"
    }
);

module.exports = reporter;
