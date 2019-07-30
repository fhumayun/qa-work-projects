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
// const WebSocket_URL = 'wss://sandbox.strax.co/mqtt';
const WebSocket_URL = "ws://localhost:8083/mqtt"; //local

// TCP/TLS connect url
const TCP_URL = 'mqtt://localhost:1883'
const TCP_TLS_URL = 'mqtts://localhost:8883'

const CAPTURE_TOPIC = '0/PBSO/ier-service/trigger/capture'
const ALERT_TOPIC = '0/PBSO/ier-service/trigger/alert/#'//hits

const options = {
  connectTimeout: 4000,

  // Authentication
  clientId: 'strax-ier-service-lbs111',
  username: 'admin',
  password: 'public',
  keepalive: 60,
  clean: true,
};

const client = mqtt.connect(WebSocket_URL, options);

//Copy from NoSQLBooster for MongoDB free edition. This message does not appear if you are using a registered version.

let hit = [ { lpScore: 0.6981093287467957,
    time: 1561648358,
    keys: [ 'ierhit/5ce5685332da7f002bc14a64/1561648357544.jpeg' ],
    x: 1029,
    keywords: [{"lpr":"ABC123"}],
    triggerType: 'lpr',
    displayData: { lprScore: 0.6981093287467957 },
    y: 171,
    source: '5d0cd9468ce014002543eb16' } //logan - local videofeed - uav - eric-rtmp
    // source: '5cc0d1eeec485700294b3b6a' } //logan - local videofeed - ip camera
    // source: '5d2a41dbc5aae400299970a5' } //sandbox feed
  ]

// after connect
client.on('connect', () => {

  hit.timeStamp = new Date();

  client.publish(CAPTURE_TOPIC, JSON.stringify(hit))
  console.log('published')

  setInterval(()=>{
    client.publish(CAPTURE_TOPIC, JSON.stringify(hit))
    console.log('published')
  },6000)
  
});