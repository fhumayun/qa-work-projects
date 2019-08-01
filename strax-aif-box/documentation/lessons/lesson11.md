# Lesson 11

## Blueprint routes 
Source: https://sailsjs.com/documentation/concepts/blueprints/blueprint-actions

Lets go over some code: 

`userController.js`

        module.exports = {

        /**
        * A custom action that overrides the built-in "findOne" blueprint action.
        * As a dummy example of customization, imagine we were working on something in our app
        * that demanded we tweak the format of the response data, and that we only populate two
        * associations: "company" and "friends".
        */
        findOne: function (req, res) {

            sails.log.debug('Running custom `findOne` action.  (Will look up user #'+req.param(\'id\')...');

            User.findOne({ id: req.param('id') }).omit(['password'])
            .populate('company', { select: ['profileImageUrl'] }) //profile image, so we can see it
            .populate('top8', { omit: ['password'] }) //don't show password
            .exec(function(err, userRecord) {
            if (err) {
                switch (err.name) {
                case 'UsageError': return res.badRequest(err);
                default: return res.serverError(err);
                }
            }

            if (!userRecord) { return res.notFound(); } //return a response of not found

            if (req.isSocket) {
                User.subscribe(req, [user.id]);
            }

            return res.ok({
                model: 'user',
                luckyCoolNumber: Math.ceil(10*Math.random()),
                record: userRecord
            });
            });
        }

        }


Here we are overriding the findOne.


Plenty of example of overriding. 
- Changing the function to route you other than returning a json object.
- Creating a unqiue id as well as also creating object.
- Delete and create another object
