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
};

exports.AdminPaises = AdminPaises;