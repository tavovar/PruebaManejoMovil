/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

AdminLugares = function () {
    var conexionLugares = require('../Datos/ConexionLugares.js');
    this.ConLugares = new conexionLugares.ConexionLugares();

    this.getLugares = function (pPais, pFuncion) {
        return this.ConLugares.getLugares(pPais, pFuncion);
    };

    this.agregarLugar = function (pLugar, pFuncion) {
        var mObjeto = {nombre: pLugar.nombre, lugar: pLugar.lugar, latitud: pLugar.latitud, longitud: pLugar.longitud, telefono: pLugar.telefono, fk_pais: pLugar.fk_pais};
        this.ConLugares.agregarLugar(mObjeto, pFuncion);
    };

    this.borrarLugar = function (pIdLugar, callback) {
        this.ConLugares.borrarLugar(parseInt(pIdLugar), callback);
    };

};

exports.AdminLugares = AdminLugares;
