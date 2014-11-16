/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var multiparty = require('multiparty')
        , http = require('http')
        , util = require('util')
        , fs = require('fs')
        , uuid = require('node-uuid');

var adminPreguntas = require('../Logica/AdminPreguntas.js');
var servidor = require('./Servidor.js');

WebServicePregunta = function () {
    var admin = new adminPreguntas.AdminPreguntas();
    var Constantes = require('../Constantes/Constantes.js');
    var ObjetoConstantes = new Constantes.Constantes();

    this.ObtenerPreguntaTeorica = function (req, res) {
        console.log("Realizando un request");
        var pk_usuario = req.query.pk_usuario;
        if (pk_usuario === undefined || pk_usuario === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        var numero_pregunta = req.query.numero_pregunta;
        if (numero_pregunta === undefined || numero_pregunta === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        var fk_manual = req.query.fk_manual;
        if (fk_manual === undefined || fk_manual === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getPreguntas(pk_usuario, numero_pregunta, fk_manual, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarPreguntaTeorica = function (req, res) {
        console.log("Realizando un request");
        admin.agregarPregunta(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerPreguntasSubSeccion = function (req, res) {
        var fk_subseccion = req.query.fk_subseccion;
        if (fk_subseccion === undefined || fk_subseccion === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getPreguntasSubSeccion(fk_subseccion, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarPreguntaDinamica = function (req, res) {
        var form = new multiparty.Form();

        form.parse(req, function (err, fields, files) {
            console.log(files.displayImage);
            console.log(fields.AccionCorrecta[0]);

            var mAccionCorrecta = fields.AccionCorrecta[0];
            var mVelocidad = fields.velocidad[0];
            var file = files.displayImage[0];

            var temporal_path = file.path;
            var destination_path = ObjetoConstantes.UbicacionImagenes + file.originalFilename;
            var input_stream = fs.createReadStream(temporal_path);
            var output_stream = fs.createWriteStream(destination_path);
            input_stream.pipe(output_stream);


            mObjeto = {accion: mAccionCorrecta, ruta: file.originalFilename, velocidad: mVelocidad};
            input_stream.on('end', function () {
                console.log('Uploaded : ' + destination_path);
                admin.agregarPreguntaDinamica(mObjeto, function (data) {
                    servidor.responderJson(res, "#");
                });
            });
        });

        form.on('error', function (err) {
            console.log('Error parsing form: ' + err.stack);
        });


    };

    this.ObtenerTodasPreguntasDinamicas = function (req, res) {
        admin.getPreguntasDinamicas(function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerPreguntaDinamica = function (req, res) {
        var pk_usuario = req.query.pk_usuario;
        var numero_pregunta = req.query.numero_pregunta;
        if (pk_usuario === undefined || pk_usuario === '' ||
                numero_pregunta === undefined || numero_pregunta === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getPreguntaDinamica(pk_usuario, numero_pregunta, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarPregunta = function (req, res) {
        var pk_pregunta = req.query.pk_pregunta;
        if (pk_pregunta === undefined || pk_pregunta === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarPreguntas(pk_pregunta, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarPreguntaDinamica = function (req, res) {
        var pk_pregunta_dinamica = req.query.pk_pregunta_dinamica;
        if (pk_pregunta_dinamica === undefined || pk_pregunta_dinamica === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarPreguntasDinamicas(pk_pregunta_dinamica, function (data) {
            servidor.responderJson(res, data);
        });
    };

};

exports.WebServicePregunta = WebServicePregunta;