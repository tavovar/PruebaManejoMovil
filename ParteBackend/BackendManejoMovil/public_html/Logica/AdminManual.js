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
        var mObjeto = {nombre: pObjeto.nombre, fk_pais: parseInt(pObjeto.fk_pais)};
        this.ConManuales.agregarManual(mObjeto, callback);
    };

    this.getSecciones = function (pIdManual, callback) {
        this.ConManuales.getSecciones(pIdManual, callback);
    };

    this.agregarSeccion = function (pObjeto, callback) {
        var mObjeto = {nombre: pObjeto.nombre, fk_manual: pObjeto.fk_manual};
        this.ConManuales.agregarSeccion(mObjeto, callback);
    };

    this.getSubsecciones = function (pIdSeccion, callback) {
        this.ConManuales.getSubSecciones(pIdSeccion, callback);
    };

    this.agregarSubseccion = function (pObjeto, callback) {
        var mObjeto = {nombre: pObjeto.nombre, descripcion: pObjeto.descripcion, fk_seccion: parseInt(pObjeto.fk_seccion)};
        this.ConManuales.agregarSubseccion(mObjeto, callback);
    };
    
    this.getSubseccion = function (pIdSubseccion, callback) {
        this.ConManuales.getSubSeccion(pIdSubseccion, callback);
    };
};

exports.AdminManual = AdminManual;