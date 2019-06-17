// import MQTT.js
const mqtt = require('mqtt');
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
const WebSocket_URL = 'wss://staging.strax.co/mqtt';

// TCP/TLS connect url
const TCP_URL = 'mqtt://localhost:1883'
const TCP_TLS_URL = 'mqtts://localhost:8883'


const options = {
  connectTimeout: 4000,

  // Authentication
  clientId: 'strax-ier-service-display',
  username: 'admin',
  password: 'public',
  keepalive: 60,
  clean: true,
};

const client = mqtt.connect(WebSocket_URL, options);

// after connect
client.on('connect', () => {
  console.log('Connected to', WebSocket_URL);

  client.subscribe('0/PBSO/ier-service/trigger/capture/#', (err) => {
    console.log(err || 'Subscribe Success')
  });
});

// handle message event
client.on('message', (topic, message) => {
  message = JSON.parse(message.toString('utf-8'));
  for (let i=0; i<message.length; i++) {
    console.log(`${message[i].triggerType} ${JSON.stringify(message[i].keywords)}  ${new Date(message[i].time*1000)}`);
  }
});
