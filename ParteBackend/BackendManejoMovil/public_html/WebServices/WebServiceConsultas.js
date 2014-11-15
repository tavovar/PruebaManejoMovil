/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminConsulta = require('../Logica/AdminConsultas.js');
var servidor = require('./Servidor.js');

WebServiceConsulta = function () {
    var admin = new adminConsulta.AdminConsulta();

    this.ObtenerConsultas = function (req, res) {
        admin.getTodasConsultas(function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerConsulta = function (req, res) {
        var pk_consultas = req.query.pk_consultas;

        if (pk_consultas === undefined || pk_consultas === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }

        admin.getInfoConsulta(pk_consultas, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ResolverConsulta = function (req, res) {
        var pk_consultas = req.body.pk_consultas;

        if (pk_consultas === undefined || pk_consultas === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.resolverConsulta(pk_consultas, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarConsulta = function (req, res) {
        var pk_consultas = req.query.pk_consultas;
        if (pk_consultas === undefined || pk_consultas === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarConsulta(pk_consultas, function (data) {
            servidor.responderJson(res, data);
        });
    };
};

exports.WebServiceConsulta = WebServiceConsulta;
