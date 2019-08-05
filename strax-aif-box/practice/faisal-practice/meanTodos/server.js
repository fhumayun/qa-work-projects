/*

   ____                                 _   _
  |  _ \ _ __ ___ _ __   __ _ _ __ __ _| |_(_) ___  _ __
  | |_) | '__/ _ \ '_ \ / _` | '__/ _` | __| |/ _ \| '_ \
  |  __/| | |  __/ |_) | (_| | | | (_| | |_| | (_) | | | |
  |_|   |_|  \___| .__/ \__,_|_|  \__,_|\__|_|\___/|_| |_|
                 |_|

*/

var express = require("express");
var path = require("path");
var bodyParser = require("body-Parser");

var index = require("./routes/index");
var todos = require("./routes/todos");

/*

  __     ___                 _____             _
  \ \   / (_) _____      __ | ____|_ __   __ _(_)_ __   ___
   \ \ / /| |/ _ \ \ /\ / / |  _| | '_ \ / _` | | '_ \ / _ \
    \ V / | |  __/\ V  V /  | |___| | | | (_| | | | | |  __/
     \_/  |_|\___| \_/\_/   |_____|_| |_|\__, |_|_| |_|\___|
                                         |___/

*/

app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use("/", index);
app.use("/api/v1/", todos);

app.listen(1337, function() {
  console.log("Server started on port 1337");
});
