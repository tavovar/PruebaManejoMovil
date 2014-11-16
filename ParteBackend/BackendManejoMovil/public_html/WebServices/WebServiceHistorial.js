/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminHistorial = require('../Logica/AdminHistorial.js');
var servidor = require('./Servidor.js');

WebServiceHistorial = function () {
    var admin = new adminHistorial.AdminHistorial();
    
    this.ObtenerHistorial = function (req, res) {
        admin.getTodoHistorial(function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.guardarHistorial = function (req, res) {
        admin.agregarHistorial(req.body.Json, function (data) {
            servidor.responderJson(res, data);
        });
    };
};

exports.WebServiceHistorial = WebServiceHistorial;
