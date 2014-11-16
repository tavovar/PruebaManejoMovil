/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Constantes = require('../Constantes/Constantes.js');
var ObjetoConstantes = new Constantes.Constantes();

var consultas = require('./WebServiceConsultas.js');
var ObjetoConsulta = new consultas.WebServiceConsulta();

var lugares = require('./WebServiceLugares.js');
var ObjetoLugares = new lugares.WebServiceLugar();

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

app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Headers", "Content-Type");
    res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
    next();
});

var router = express.Router();


//----------------------------------------------------------------------------------
//          Web services para la aplicacion movil 
//----------------------------------------------------------------------------------

//GETS

router.get('/app/preguntas', ObjetoPregunta.ObtenerPreguntaTeorica);
router.get('/app/preguntas_dinamicas', ObjetoPregunta.ObtenerPreguntaDinamica);
router.get('/app/lugares', ObjetoLugares.ObtenerLugaresSucursales);
router.get('/app/historiales', ObjetoHistorial.ObtenerHistorial);
router.get('/app/historiales_semanas', ObjetoHistorial.ObtenerHistorialSemanas);
router.get('/app/historiales_meses', ObjetoHistorial.ObtenerHistorialMeses);
router.get('/app/manuales', ObjetoManual.ObtenerManuales);
router.get('/app/secciones', ObjetoManual.ObtenerSecciones);
router.get('/app/subsecciones', ObjetoManual.ObtenerSubsecciones);
router.get('/app/subseccion', ObjetoManual.ObtenerSubseccion);
router.get('/app/paises', ObjetoPais.getPaises);

//POST

router.post('/app/lugares', ObjetoLugares.AgregarSucursal);
router.post('/app/historiales', ObjetoHistorial.guardarHistorial);
router.post('/app/secciones', ObjetoManual.AgregarSeccion);
router.post('/app/subsecciones', ObjetoManual.AgregarSubseccion);
router.post('/app/paises', ObjetoPais.agregarPais);


//DELETE

router.delete('/app/lugares', ObjetoLugares.EliminarLugar);
router.delete('/app/secciones', ObjetoManual.EliminarSeccion);
router.delete('/app/subsecciones', ObjetoManual.EliminarSubseccion);
router.delete('/app/paises', ObjetoPais.borrarPais);

//----------------------------------------------------------------------------------
//          Web services para la pagina web  
//----------------------------------------------------------------------------------

//GETS
router.get('/web/preguntas', ObjetoPregunta.ObtenerPreguntasSubSeccion);
router.get('/web/preguntas_dinamicas', ObjetoPregunta.ObtenerTodasPreguntasDinamicas);
router.get('/web/consultas', ObjetoConsulta.ObtenerConsultas);
router.get('/web/consulta', ObjetoConsulta.ObtenerConsulta);
router.get('/web/manuales', ObjetoManual.ObtenerTodosManuales);

//POTS
router.post('/web/usuariosweb', x.IdentificarseWeb);
router.post('/web/preguntas', ObjetoPregunta.AgregarPreguntaTeorica);
router.post('/web/preguntas_dinamicas', ObjetoPregunta.AgregarPreguntaDinamica);
router.post('/web/consulta', ObjetoConsulta.ResolverConsulta);
router.post('/web/manuales', ObjetoManual.AgregarManual);

//DELETE
router.delete('/web/preguntas', ObjetoPregunta.EliminarPregunta);
router.delete('/web/preguntas_dinamicas', ObjetoPregunta.EliminarPreguntaDinamica);
router.delete('/web/consulta', ObjetoConsulta.EliminarConsulta);
router.delete('/web/manuales', ObjetoManual.EliminarManual);

app.all('/', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Headers", "Content-Type");
    res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
    next();
});

app.use(router);

app.listen(8080, function () {
    console.log("Node server running on http://localhost:8080");
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

var http = require('http');
var path = require('path');
var fs = require('fs');
http.createServer(function (request, response) {
    console.log('request starting...');

    var filePath = ObjetoConstantes.UbicacionServidorWeb;
    var ArchivosSolicitado = filePath + request.url;
    if (request.url === '/')
        ArchivosSolicitado = filePath + '/index.html';

    console.log(ArchivosSolicitado);

    fs.exists(ArchivosSolicitado, function (exists) {
        if (exists) {
            fs.readFile(ArchivosSolicitado, function (error, content) {
                if (error) {
                    response.writeHead(500);
                    response.end();
                }
                else {
                    response.writeHead(200);
                    response.end(content, 'utf-8');
                }
            });
        }
        else {
            response.writeHead(404);
            response.end();
        }
    });

}).listen(80);


console.log('Server running at http://127.0.0.1:8125/');

//var connect = require('connect');
//var serveStatic = require('serve-static');
//connect().use(serveStatic(UbicacionServer)).listen(8080);

