/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

AdminPreguntas = function () {
    var conexionPreguntas = require('../Datos/ConexionPreguntas.js');
    this.ConPreguntas = new conexionPreguntas.ConexionPreguntas();

    this.getPreguntas = function (pPais, pFuncion) {
        return this.ConPreguntas.getPreguntas(pPais, pFuncion);
    };

    this.agregarPreguntas = function (pObjeto, pFuncion) {
        this.ConPreguntas.agregarPreguntas(pObjeto, pFuncion);
    };
};

exports.AdminPreguntas = AdminPreguntas;

