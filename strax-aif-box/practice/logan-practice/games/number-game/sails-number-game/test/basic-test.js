const expect = require("chai").expect;
const supertest = require("supertest");
const apiHost = "localhost";
const apiPort = 1337;
let apiUri = `http://${apiHost}:${apiPort}`;
const api = supertest(apiUri);
const welcomeRoute = "/welcome";

//Test Run 1
describe("Number Game Tests 1", function() {
  it("Welcome Route", function(done) {
    api
      .get(welcomeRoute)
      .expect(200)
      .expect("Content-Type","application/json; charset=utf-8")
      .end((err,res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        // expect(res.body).to.have.property("fibonacci");
        // //we expect to get { fibonacci: 1 }
        // expect(res.body.fibonacci).to.equal(1);

        done();
      });
  });
});
