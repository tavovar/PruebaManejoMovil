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
        this.ConLugares.agregarLugar(pLugar, pFuncion);
    };

};

exports.AdminLugares = AdminLugares;
