const expect = require("chai").expect;
const fibAPI = require("./api-config")(1337); //port 1337 for fibonacci app

const fibonacciRoute = "/fibonacci";

//Test Run 1
describe("Fibonacci Test Run 1", function() {
  it("Start GET", function(done) {
    fibAPI
      .get(fibonacciRoute)
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 1 }
        expect(res.body.fibonacci).to.equal(1);

        done();
      });
  });

  it("First PUT - Advance the Fibonacci Number", function(done) {
    fibAPI
      .put(fibonacciRoute)
      // .set("Content-Type", "application/json")
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 1 }
        expect(res.body.fibonacci).to.equal(1);

        done();
      });
  });

  it("Second PUT - Advance the Fibonacci Number", function(done) {

    fibAPI
      .put(fibonacciRoute)
      // .set("Content-Type", "application/json")
      // .send(newFibonacci)
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 2 }
        expect(res.body.fibonacci).to.equal(2); //3rd fibonacci number

        done();
      });
  });

  it("Third PUT - Advance the Fibonacci Number", function(done) {
    fibAPI
      .put(fibonacciRoute)
      // .set("Content-Type", "application/json")
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 3 }
        expect(res.body.fibonacci).to.equal(3); //4th fibonacci number

        done();
      });
  });

  it("First POST - Set the Fibonacci Number to 21", function(done) {
    fibAPI
      .post(fibonacciRoute + "/21")
      // .set("Content-Type", "application/json")
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 21 }
        expect(res.body.fibonacci).to.equal(21);

        done();
      });
  });

  it("PUT - Advance the Fibonacci Number", function(done) {
    fibAPI
      .put(fibonacciRoute)
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 34 }
        expect(res.body.fibonacci).to.equal(34); //3rd fibonacci number

        done();
      });
  });

  it("DELETE the Fibonacci Number - Set back to 1", function(done) {
    fibAPI
      .delete(fibonacciRoute)
      .expect(200)
      .expect("Content-Type", /json/)
      .end((err, res) => {
        console.log(res.body);
        expect(err).to.equal(null); //we do not want errors

        //we expect to have the property fibonacci
        expect(res.body).to.have.property("fibonacci");
        //we expect to get { fibonacci: 1 }
        expect(res.body.fibonacci).to.equal(1); //1st

        done();
      });
  });
});

// //Test Run 2
describe('Fibonacci Test Run 2', function() {

    it("First POST - Set the Fibonacci Number to 13", function(done) {
  fibAPI
    .post(fibonacciRoute + "/13")
    // .set("Content-Type", "application/json")
    .expect(200)
    .expect("Content-Type", /json/)
    .end((err, res) => {
      console.log(res.body);
      expect(err).to.equal(null); //we do not want errors

      //we expect to have the property fibonacci
      expect(res.body).to.have.property("fibonacci");
      //we expect to get { fibonacci: 13 }
      expect(res.body.fibonacci).to.equal(13);

      done();
    });
});

it("PUT - Advance the Fibonacci Number", function(done) {
  fibAPI
    .put(fibonacciRoute)
    .expect(200)
    .expect("Content-Type", /json/)
    .end((err, res) => {
      console.log(res.body);
      expect(err).to.equal(null); //we do not want errors

      //we expect to have the property fibonacci
      expect(res.body).to.have.property("fibonacci");
      //we expect to get { fibonacci: 21 }
      expect(res.body.fibonacci).to.equal(21); 
      done();
    });
});

})
