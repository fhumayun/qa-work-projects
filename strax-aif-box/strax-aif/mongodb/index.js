'use strict'

const fs        = require('fs')
const mongoose  = require('mongoose')
mongoose.Promise = global.Promise;

const getConnectOptions = (settings) => {
  return {
    db  : settings.dbParameters,
    user: settings.user,
    pass: settings.pass,
    server  : settings.serverParameters,
    replset : settings.replsetParameters,
    promiseLibrary : global.Promise
  }
}

const getMongoURLs = (settings) => {
  const _url = settings.servers.reduce((p, c) => p + c + ',', 'mongodb://')
  const url  = _url.substr(0, _url.length - 1)

  var DBs, dbURL = {}

  if(typeof settings.db == "string") {
    DBs = [settings.db]
  } else {
    DBs = settings.db
  }

  DBs.forEach(db => {
    // build connection string for every database
    dbURL[db] = url + '/' + db + "?authSource=admin"
  })

  return dbURL
}

function mongodb ({integrator, container}) {
  console.log('get ready for init')

  return new Promise((resolve, reject) => {
    console.log('working on Promise')

    integrator.on('mongo.init', () => {

      if (!container.resolve('mongoSettings')) {
        return reject(new Error('mongodb settings not supplied'))

      } else {

        const options   = getConnectOptions(container.cradle.mongoSettings)
        const dbServers = getMongoURLs(container.cradle.mongoSettings)

        var database = {}

        // console.log(dbServers);
        const dbKeys = Object.keys(dbServers);

        // create connection objects for each database and wire up with container
        // for (var [db, dbURL] of Object.entries(dbServers)) {

        for (var i = 0, length = dbKeys.length; i < length; i++) {

          const db = dbKeys[i];
          const dbURL = dbServers[db];
          const connection = mongoose.connect(dbURL, options).connection

          connection.on('error', err => {
            // clean up
            mongoose.disconnect();
            integrator.emit('mongo.error', err)
          })

          connection.on('disconnected', () => {
            // clean up
            mongoose.disconnect();
            integrator.emit('mongo.disconnected', db)
          })

          connection.on('connected', () => {
            // wire up all the models.
            database[db] = {}

            // retrieve DB models from <<project-root>>/mongodb/<<dbName>>/<<models>>
            const modelPath = process.env.MODELS_PATH;
            var dbPath = modelPath ? `${modelPath}/${db}` : `${__dirname}/${db}`;
            console.log("Reading database ", dbPath);
            fs.readdir(dbPath, (err, folders) => {
              if(!err && folders) {
                // folders = ['accounts']      // TODO uncomment only for testing purposes
                folders.forEach(folder => {

                  // ignore the JOI schemas from _common folder
                  if("_" != folder.charAt(0)) {

                    fs.stat(dbPath + '/' + folder, (err, stats) => {
                      if(!err && stats) {
                        if (stats.isDirectory()) {
                          database[db][folder] = require(dbPath + '/' + folder + '/model.js')(mongoose)
                        }
                      } else {
                        console.log("Fatal Error: File")
                      }
                    })
                  }
                })
              } else {
                console.log("Fatal Error: Directory", err)
              }
            })

            database[db]._connection = connection;
            integrator.emit('mongo.ready', dbServers);
          })
        }

        // disconnect connections with all the database
        const disconnect = () => {
          console.log("about to disconnect");
          for (var [key, db] of Object.entries(container.cradle.database)) {
            if (key != 'disconnect')
              db._connection.close()
          }
        }

        database.disconnect = disconnect

        return resolve(database)
      }
    })
  })
}

module.exports = mongodb
