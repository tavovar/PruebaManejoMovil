/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminManual = require('../Logica/AdminManual.js');
var servidor = require('./Servidor.js');


WebServiceManual = function () {
    var admin = new adminManual.AdminManual();

    this.ObtenerManuales = function (req, res) {
        var fk_pais = req.query.fk_pais;
        if (fk_pais === undefined || fk_pais === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getManuales(fk_pais, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarManual = function (req, res) {
        admin.agregarManual(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerSecciones = function (req, res) {
        var fk_manual = req.query.fk_manual;
        if (fk_manual === undefined || fk_manual === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getSecciones(fk_manual, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarSeccion = function (req, res) {
        console.log("Agregando una sección!!");
        admin.agregarSeccion(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerSubsecciones = function (req, res) {
        var fk_seccion = req.query.fk_seccion;
        if (fk_seccion === undefined || fk_seccion === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.getSubsecciones(fk_seccion, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.AgregarSubseccion = function (req, res) {
        admin.agregarSubseccion(req.body, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.ObtenerSubseccion = function (req, res) {
        var pk_subseccion = req.query.pk_subseccion;
        if (pk_subseccion === undefined || pk_subseccion === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }

        admin.getSubseccion(pk_subseccion, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarManual = function (req, res) {
        var pk_manual = req.query.pk_manual;
        if (pk_manual === undefined || pk_manual === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarManual(pk_manual, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarSeccion = function (req, res) {
        var pk_seccion = req.query.pk_seccion;
        if (pk_seccion === undefined || pk_seccion === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarSeccion(pk_seccion, function (data) {
            servidor.responderJson(res, data);
        });
    };

    this.EliminarSubseccion = function (req, res) {
        var pk_subseccion = req.query.pk_subseccion;
        if (pk_subseccion === undefined || pk_subseccion === '') {
            servidor.responderJson(res, {"error": -1});
            return;
        }
        admin.borrarSeccion(pk_subseccion, function (data) {
            servidor.responderJson(res, data);
        });
    };

};
exports.WebServiceManual = WebServiceManual;
