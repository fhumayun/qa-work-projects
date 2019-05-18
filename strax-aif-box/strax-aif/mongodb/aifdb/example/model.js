'use strict';

const jsonToMongoose = require('json-mongoose')

const model = ({ mongoose, connection }) => {
  return jsonToMongoose({
    mongoose: mongoose,
    connection: connection,
    collection: 'example',
    schema: require('./schema.js'),
    options: {

    }
  })
}

module.exports = (mongoose) => model(mongoose)

