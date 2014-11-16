/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var AdminHistorial = function () {

    var conexionHistorial = require('../Datos/ConexionHistorial.js');
    this.ConHistorial = new conexionHistorial.ConexionHistorial();

    this.getTodoHistorial = function (pIdUsuario, callback) {
        this.ConHistorial.getTodoHistorial(pIdUsuario, callback);
    };

    this.agregarHistorial = function (pObjeto, callback) {
        var mObjeto = {tipo: pObjeto.tipo, preguntas_correctas: pObjeto.preguntas_correctas, fk_usuario: pObjeto.fk_usuario, fecha:pObjeto.fecha};
        this.ConHistorial.agregarHistorial(mObjeto, callback);
    };

};

exports.AdminHistorial = AdminHistorial;