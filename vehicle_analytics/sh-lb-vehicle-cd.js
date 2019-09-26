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
// const WebSocket_URL = 'wss://sandbox.strax.co/mqtt'; //sandbox
const WebSocket_URL = 'wss://cd.strax.co/mqtt'; //cd
// const WebSocket_URL = "ws://localhost:8083/mqtt"; //local


// TCP/TLS connect url
const TCP_URL = 'mqtt://localhost:1883'
const TCP_TLS_URL = 'mqtts://localhost:8883'

const CAPTURE_TOPIC = '0/PBSO/ier-service/trigger/capture'
const ALERT_TOPIC = '0/PBSO/ier-service/trigger/alert/#'//hits

const options = {
  connectTimeout: 4000,

  // Authentication
  clientId: 'strax-ier-service-lbs112',
  username: 'admin',
  password: 'public',
  keepalive: 60,
  clean: true,
};

const client = mqtt.connect(WebSocket_URL, options);

//Copy from NoSQLBooster for MongoDB free edition. This message does not appear if you are using a registered version.

let hit = [{ 
  keywords: [
    {
      color: "silver/grey",
      makemodel: "ram pickup truck"
    }
  ],
  keys: [
    "ierhit/5d0286df0f406f00299d3412/1560976007604.jpeg"
  ],
  feeds: [],
  triggerType: "vehicle",
  // source: "5d0286df0f406f00299d3412", //uav
  source: "5cc0d1eeec485700294b3b6a", //rear office camera - local
  // source: "5cdd9ef4c7ef0f002b6aac9f", //rear office camera - cd
  // source: "5ce5685332da7f002bc14a64", //sandbox - Rear Office Direct
  
  displayData: {
    modelScore: 0.9941408038139343
  },
  location: null
}]

// after connect
client.on('connect', () => {

  // hit.timeStamp = new Date();

  client.publish(CAPTURE_TOPIC, JSON.stringify(hit))
  console.log('published')

  setInterval(()=>{
    client.publish(CAPTURE_TOPIC, JSON.stringify(hit))
    console.log('published')
  },6000)
  
});