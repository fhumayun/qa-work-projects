module.exports = {


  friendlyName: 'Welcome Message',


  description: '',


  inputs: {

    name: {
      type: 'string',
      example: 'Ami',
      description: 'The name of the person to greet.',
      required: true
    }

  },


  // exits: {

  //   success: {
  //     description: 'All done.',
  //   },

  // },


  fn: async function (inputs,exits) {
    var result = `Hello, ${inputs.name}!`;
    return exits.success(result);
  }

};

