'use strict';

const Joi = require('joi');

module.exports = Joi.object({
    input: Joi.string().allow('', null),
    formattedAddress: Joi.string().allow('', null),
    dms: Joi.string().allow('', null),
    lat: Joi.string().allow('', null),
    lng: Joi.string().allow('', null),
    geo: Joi.object({
      type: Joi.string().regex(/Point/),
      coordinates: Joi.array().items(Joi.number().precision(6), Joi.number().precision(6))
    }),
    additionalInfo: Joi.object()
});
