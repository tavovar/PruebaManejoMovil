/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

AdminManual = function () {
    var conexionManuales = require('../Datos/ConexionManual.js');
    this.ConManuales = new conexionManuales.ConexionManuales();

    this.getManuales = function (callback) {
        this.ConManuales.getManuales(callback);
    };

    this.agregarManual = function (pObjeto, callback) {
        this.ConManuales.agregarManual(pObjeto, callback);
    };

    this.getSecciones = function (pIdManual, callback) {
        this.ConManuales.getSecciones(pIdManual, callback);

    };

    this.agregarSeccion = function (pObjeto, callback) {
        this.ConManuales.agregarSeccion(pObjeto, callback);
    };

    this.getSubSecciones = function (pIdSeccion, callback) {
        this.ConManuales.getSubSecciones(pIdSeccion, callback);
    };

    this.agregarSubseccion = function (pObjeto, callback) {
        this.ConManuales.agregarSubseccion(pObjeto, callback);
    };
};

exports.AdminManual = AdminManual;