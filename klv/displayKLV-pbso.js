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
PBSO 20             'wss://pbso20.strax.co/mqtt'
*/
// const WebSocket_URL = 'wss://cd01.strax.co/mqtt';
// const WebSocket_URL = 'wss://staging.strax.co/mqtt';
const WebSocket_URL = 'wss://pbso20.strax.co/mqtt';

// TCP/TLS connect url
const TCP_URL = 'mqtt://localhost:1883'
const TCP_TLS_URL = 'mqtts://localhost:8883'

let count = 0;

const options = {
  connectTimeout: 4000,

  // Authentication
  clientId: 'strax-ier-service-12211',
  username: 'admin',
  password: 'public',
  keepalive: 60,
  clean: true,
};

const client = mqtt.connect(WebSocket_URL, options);

// after connect
client.on('connect', () => {
  console.log('Connected to', WebSocket_URL);

  client.subscribe('0/PBSO/media_service/klv/#', (err) => {
    console.log(err || 'Subscribe Success')
  });
});

// handle message event
client.on('message', (topic, message) => {
  count++;
  message = JSON.parse(message.toString('utf-8'));
  console.log(message)
  console.log(count)
  
});
