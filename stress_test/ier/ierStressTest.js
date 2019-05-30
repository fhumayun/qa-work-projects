'use strict';
const fs = require('fs');
const unirest = require('unirest');

class IerStressTest {
  constructor() {
    let rawdata = fs.readFileSync('config.json');  
    this.config = JSON.parse(rawdata);
    this.requests = [];
    this.xmlHeaders = 
      {'Accept': 'application/xml', 'Content-Type': 'application/xml'};
    this.jsonHeaders = 
      {'Accept': 'application/json', 'Content-Type': 'application/json'};
  }

  buildTriggers() {
    this.config.triggers.forEach((trigger) => {
      let request = {};

      if (trigger.endsWith('.xml')) {
        request.headers = this.xmlHeaders;
      }
      else if (trigger.endsWith('.json')) {
        request.headers = this.jsonHeaders;
      }
      else {
        request.headers = null;
        console.error('Invalid trigger file',trigger);
      }

      if (trigger.startsWith('saferwatch')) {
        request.url = 'https://ldev.strax.co/api/integrations/saferwatch';
      }
      else if (trigger.startsWith('shotspotter')) {
        request.url = 'https://ldev.strax.co/api/integrations/shotspotter';
      }
      else {
        request.url = null;
      }

      if (request.url && request.headers) {
        let filename = `./triggers/${trigger}`;
        request.payload = fs.readFileSync(filename, 'utf8');
        this.requests.push(request);
      }
    });
  }

  sendRequest(url,headers,payload) {
    return new Promise((resolve,reject) => {
      unirest.post(url)
        .headers(headers)
        .send(payload)
        .end(function (response) {
          if (response.status !== 200)  {
            console.error(`Error sending requests got status ${response.status} for url ${url}`);
          }
          resolve();
        });
    });
  }

  sendRequests() {
    return new Promise((resolve,reject) => {
      let promises = [];
      this.requests.forEach((request) => {
        let promise = this.sendRequest(request.url,
          request.headers,request.payload);
        promises.push(promise);
      });
      Promise.all(promises).then((data) => {
        resolve();
      }).catch((err) => {
        console.error('Error sending requests',err);
        reject();
      });
    });
  }

  async pumpRequests() {
    await new Promise(resolve => setTimeout(resolve, this.config.delay*1000));
    return new Promise((resolve,reject) => {
      this.sendRequests().then((data) => {
        resolve();
      }).catch((err) => {
        console.error('Error sending requests',err);
        reject();
      });
    });
  }

  async start() {
    this.buildTriggers();
    let total = 0;
    let count = 0;
    for (let i=0; i<this.config.loop; i++) {
      let request = await this.pumpRequests();
      count++;
      total = total + this.requests.length;
      console.log(`${total} requests sent.`)
    }
  }
}

exports.IerStressTest = IerStressTest;