/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var adminPreguntas = require('../Logica/AdminPreguntas.js');
var servidor = require('./Servidor.js');

ObtenerPreguntasTeoricas = function (req, res) {
    console.log("Realizando un request");
    var x = new adminPreguntas.AdminPreguntas();

    x.getPreguntas(1, function (data) {
        servidor.responderJson(res, data);
    });
};




exports.ObtenerPreguntasTeoricas = ObtenerPreguntasTeoricas;
