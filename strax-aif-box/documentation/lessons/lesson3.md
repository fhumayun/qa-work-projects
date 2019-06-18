# Lesson 3

## Javascript

### Promises

#### What is a promise?
A promise is an object that may produce a single value some time in the future. 
[Source](https://medium.com/javascript-scene/master-the-javascript-interview-what-is-a-promise-27fc71e77261)

3 states: 
- Fulfilled
- Rejected
- Pending

#### What does await do?
- In order to use await, it must be in an async function
- Async and Await are extensions of promises
- The await expression causes the async function execution to pause until a Promise is fulfilled, that is resolved or rejected, and to resume execution of the async function after fulfillment. When resumed, the value of the await expression is that of the fulfilled Promise. If the Promise is rejected, the await expression throws the rejected value.
[Source](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/await)

#### Reading Material
- [Google Developers - Promises Article](https://developers.google.com/web/fundamentals/primers/promises)
- [Medium - Promises Article](https://medium.com/javascript-scene/master-the-javascript-interview-what-is-a-promise-27fc71e77261)
- [Mozilla - Await Article](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/await)