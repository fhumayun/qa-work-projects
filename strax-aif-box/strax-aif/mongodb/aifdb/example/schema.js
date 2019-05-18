'use strict';

const Joi = require('joi')

module.exports = Joi.object({
  draftType             : Joi.string().required().valid(
    'PARTICIPANTS', 'CLUSTERS', 'SUBUNITS', 'IERS', 'TRIGGERS', 'PRE_PLANS', 
    'CALLSIGNS', 'VIDEO_FEEDS', 'MAP_LAYERS'),
  data             : Joi.object().required(),
  isDraft          : Joi.boolean().default(true),

});
