/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var adminPreguntas = require('../Logica/AdminPreguntas.js');
var servidor = require('./Servidor.js');

WebServicePregunta = function () {
    var admin = new adminPreguntas.AdminPreguntas();

    this.ObtenerPreguntasTeoricas = function (req, res) {
        console.log("Realizando un request");

        admin.getPreguntas(1, function (data) {
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

};

exports.WebServicePregunta = WebServicePregunta;
