/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var express = require("express"),
        app = express(),
        bodyParser = require("body-parser"),
        methodOverride = require("method-override");
mongoose = require('mongoose');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());
app.use(methodOverride());

var router = express.Router();

router.get('/app/preguntas', function (req, res) {
    res.send("Hello World!");
});

router.get('/app/lugares', function (req, res) {
    res.send("Hello World!");
});

app.use(router);

app.listen(3000, function () {
    console.log("Node server running on http://localhost:3000");
});

