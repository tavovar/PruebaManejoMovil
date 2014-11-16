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
//        var mObjeto = {tipo: 1, preguntas_correctas: 6, fk_usuario: 1, fecha:"2014-12-02"};
//        admin.agregarHistorial(mObjeto, function (data) {
//            servidor.responderJson(res, data);
//        });
        var fk_usuario = req.query.fk_usuario;
        if (fk_usuario === undefined || fk_usuario === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }

        admin.getTodoHistorial(fk_usuario, function (data) {
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
