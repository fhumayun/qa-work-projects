//display hits

// import MQTT.js
const mqtt = require("mqtt");
// <script src="https://unpkg.com/mqtt/dist/mqtt.min.js"></script>
// const mqtt = require('mqtt')

// WebSocket connect url
//Change for env switching
/*
ENV 
Staging             'wss://staging.strax.co/mqtt'
CD                  'wss://cd.strax.co/mqtt'
Sandbox             'wss://sandbox.strax.co/mqtt'
*/
// const WebSocket_URL = "wss://sandbox.strax.co/mqtt";
const WebSocket_URL = "ws://localhost:8083/mqtt"; //local
// const WebSocket_URL = "wss://staging.strax.co/mqtt"; //staging

// TCP/TLS connect url
const TCP_URL = "mqtt://localhost:1883";
const TCP_TLS_URL = "mqtts://localhost:8883";

const CAPTURE_TOPIC = "0/PBSO/ier-service/trigger/capture/#";
const ALERT_TOPIC = "0/PBSO/ier-service/trigger/alert/#"; //hits

const options = {
  connectTimeout: 4000,

  // Authentication
  clientId: "strax-ier-service-lbdh111",
  username: "admin",
  password: "public",
  keepalive: 60,
  clean: true
};

const client = mqtt.connect(WebSocket_URL, options);

// after connect
client.on("connect", () => {
  console.log("Connected to", WebSocket_URL);

  // client.subscribe(CAPTURE_TOPIC, err => {
  //   console.log(err || "Subscribe Capture Success");
  // });

  client.subscribe(ALERT_TOPIC, (err) => {
    console.log(err || 'Subscribe Alert Success')
  });
});

// handle message event
client.on("message", (topic, message) => {
  message = JSON.parse(message.toString("utf-8"));

  //for alerts - ier hits
  console.log("message", message);
  console.log("message.id", message.id);
  console.log("message.ierId", message.ierId);


  //for captures
  // for (let i=0; i<message.length; i++) {
  //   // console.log(`${message[i].triggerType} ${JSON.stringify(message[i].keywords)}  ${new Date(message[i].time*1000)}`);
  //   console.log(`${message[i].triggerType} ${JSON.stringify(message[i].keywords)} ${JSON.stringify(message[i].displayData)}` )
  // }
});
