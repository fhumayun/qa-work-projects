module.exports = function () {
    this.Before(function (scenario) {
        this.actionwords = Object.create(require('./actionwords.js').Actionwords);
    });

    this.After(function (scenario) {
        this.actionwords = null;
    });
















    this.Given(/^a user successfully logs into the SRM$/, function (callback) {
        this.actionwords.aUserSuccessfullyLogsIntoTheSRM();
        callback();
    });

    this.Given(/^the SRM UI correctly loads$/, function (callback) {
        this.actionwords.theSRMUICorrectlyLoads();
        callback();
    });

    this.When(/^the Pre-Mission tab is selected$/, function (callback) {
        this.actionwords.thePreMissionTabIsSelected();
        callback();
    });

    this.When(/^the Create Mission button is pressed$/, function (callback) {
        this.actionwords.theCreateMissionButtonIsPressed();
        callback();
    });


    this.Then(/^the user should be able to enter the Mission Name$/, function (callback) {
        this.actionwords.theUserShouldBeAbleToEnterTheMissionName();
        callback();
    });

    this.Then(/^add a Mission Address$/, function (callback) {
        this.actionwords.addAMissionAddress();
        callback();
    });

    this.Then(/^provide a Mission Plan Description$/, function (callback) {
        this.actionwords.provideAMissionPlanDescription();
        callback();
    });

    this.Then(/^press Create Mission to save the result for later use$/, function (callback) {
        this.actionwords.pressCreateMissionToSaveTheResultForLaterUse();
        callback();
    });

    this.Then(/^pick a Mission Type$/, function (callback) {
        this.actionwords.pickAMissionType();
        callback();
    });
}
