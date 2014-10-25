/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lugares = require('./WebServiceLugares.js');
var preguntas = require('./WebServicePreguntas.js');
var manuales = require('./WebServiceManual.js');
var historial = require('./WebServiceHistorial.js');

//var usuario = require('./WebServiceUsuarios.js');

var express = require("express"),
        app = express(),
        bodyParser = require("body-parser"),
        methodOverride = require("method-override");
mongoose = require('mongoose');

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());
app.use(methodOverride());

var router = express.Router();

router.get('/app/preguntas', preguntas.ObtenerPreguntasTeoricas);
router.get('/app/lugares', lugares.ObtenerLugaresSucursales);
router.get('/app/manuales', manuales.ObtenerManuales);
router.get('/app/historial', historial.ObtenerHistorial);


app.use(router);

app.listen(3000, function () {
    console.log("Node server running on http://localhost:3000");
});

