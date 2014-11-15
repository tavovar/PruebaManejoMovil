/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminPaises = require('../Logica/AdminPaises.js');
var servidor = require('./Servidor.js');


WebServicePais = function () {
    var admin = new adminPaises.AdminPaises();

    this.getPaises = function (req, res) {
        admin.getPaises(function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.agregarPais = function (req, res) {
        admin.agregarPais(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.borrarPais = function (req, res) {
        var pk_pais = req.query.pk_pais;
        if (pk_pais === undefined || pk_pais === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarPais(pk_pais, function (data) {
            servidor.responderJson(res, data);
        });
    };
};


exports.WebServicePais = WebServicePais;