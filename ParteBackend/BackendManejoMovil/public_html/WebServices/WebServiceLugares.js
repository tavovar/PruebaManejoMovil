/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var AdminLugares = require('../Logica/AdminLugares.js');
var servidor = require('./Servidor.js');

WebServiceLugar = function () {
    var admin = new AdminLugares.AdminLugares();

    this.ObtenerLugaresSucursales = function (req, res) {
        admin.getLugares(1, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarSucursal = function (req, res) {
        admin.agregarLugar(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarLugar = function (req, res) {
        var pk_sucursal = req.query.pk_sucursal;
        if (pk_sucursal === undefined || pk_sucursal === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarLugar(pk_sucursal, function (data) {
            servidor.responderJson(res, data);
        });
    };
};

exports.WebServiceLugar = WebServiceLugar;