/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


AdminPaises = function () {
    var conexionPaises = require('../Datos/ConexionPaises.js');
    this.ConPaises = new conexionPaises.ConexionPaises();

    this.getPaises = function (pFuncion) {
        return this.ConPaises.getPaises(pFuncion);
    };
    
    this.agregarPais = function (pObjeto, pFuncion) {
        var mObjeto = {nombre:pObjeto.nombre};
        return this.ConPaises.agregarPais(mObjeto, pFuncion);
    };

    this.borrarPais = function (pIdPais, pFuncion) {
        return this.ConPaises.borrarPais(pIdPais, pFuncion);
    };
};

exports.AdminPaises = AdminPaises;