/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lugares = require('./WebServiceLugares.js');
var preguntas = require('./WebServicePreguntas.js');
var ObjetoPregunta = new preguntas.WebServicePregunta();

var manuales = require('./WebServiceManual.js');
var ObjetoManual = new manuales.WebServiceManual();

var historial = require('./WebServiceHistorial.js');

var ObjetoHistorial = new historial.WebServiceHistorial();

var paises = require('./WebServicePaises.js');
var ObjetoPais = new paises.WebServicePais();

var usuarios = require('./WebServiceUsuarios.js');

var x = new usuarios.WebServiceUsuario();

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


//----------------------------------------------------------------------------------
//          Web services para la aplicacion movil 
//----------------------------------------------------------------------------------

//GETS

router.get('/app/preguntas', ObjetoPregunta.ObtenerPreguntasTeoricas);
router.get('/app/lugares', lugares.ObtenerLugaresSucursales);
router.get('/app/historiales', ObjetoHistorial.ObtenerHistorial);
router.get('/app/manuales', ObjetoManual.ObtenerManuales);
router.get('/app/secciones', ObjetoManual.ObtenerSecciones);
router.get('/app/subsecciones', ObjetoManual.ObtenerSubsecciones);
router.get('/app/subseccion', ObjetoManual.ObtenerSubseccion);
router.get('/app/paises', ObjetoPais.getPaises);

//POST
router.post('/app/historiales', ObjetoHistorial.guardarHistorial);
router.post('/app/manuales', ObjetoManual.AgregarManual);
router.post('/app/secciones', ObjetoManual.AgregarSeccion);
router.post('/app/subsecciones', ObjetoManual.AgregarSubseccion);

//----------------------------------------------------------------------------------
//          Web services para la pagina web  
//----------------------------------------------------------------------------------

//GETS
router.get('/web/preguntas', ObjetoPregunta.ObtenerPreguntasSubSeccion);

//POTS
router.post('/web/usuariosweb', x.IdentificarseWeb);
router.post('/web/preguntas', ObjetoPregunta.AgregarPreguntaTeorica);

app.all('/', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    next();
});

app.use(router);

app.listen(80, function () {
    console.log("Node server running on http://localhost:80");
});

function responderJson(res, dataJson) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    if (dataJson === null) {
        res.send({"error": -1});
    }
    else {
        res.send(dataJson);
    }
}

exports.responderJson = responderJson;