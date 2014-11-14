/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminHistorial = require('../Logica/AdminHistorial.js');
var servidor = require('./Servidor.js');

WebServiceHistorial = function () {
    this.ObtenerHistorial = function (req, res) {
        var admin = new adminHistorial.AdminHistorial();
        admin.getTodoHistorial(function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.guardarHistorial = function (req, res) {
        console.log("HOLA umundo");
        //var admin = new adminHistorial.AdminHistorial();
        //admin.getTodoHistorial(function (data) {
        //    servidor.responderJson(res, data);
        //});
        console.log(req.body.Json);
        servidor.responderJson(res, "Hola");
    };


};

exports.WebServiceHistorial = WebServiceHistorial;
