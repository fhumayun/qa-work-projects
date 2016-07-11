/**
 *
 */
'use strict';

var userdata =
    [
        {
            "_id" : ObjectId("000000000000000000000001"),
            "userName" : "tim@gct.com",
            "password" : "cf919b461386ba3127cbd6bba36be7f3895f2ed0",
            "firstName" : "Timothy",
            "lastName" : "McClure",
            "salt" : "Dd7u+sI1s1B/48ZX1iILdVGOy445sC8/fA5QZONBCwJR6oLMUhi2H/P5hWvBJ9eIEeo+jNb7eqdtUdxm2oKfOwxi9ZincyygfwtN7dVD6MtB0rDrXRULdt5k5z2ITl23k70wMAnmYNSWMrAxGlyeoWCOnXxmtdZOR8pt7EyCVM0=",
            "role" : "admin",
            "__v" : 0.0
        }
    ];


var conn = new Mongo();

var db = conn.getDB("test_straxmedia");

db.dropDatabase();

db.accounts.insert( userdata );
