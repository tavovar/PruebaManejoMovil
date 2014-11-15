/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var AdminConsulta = function () {

    var conexionConsulta = require('../Datos/ConexionConsultas.js');
    this.ConConsulta = new conexionConsulta.ConexionConsulta();

    this.getTodasConsultas = function (callback) {
        this.ConConsulta.getTodasConsultas(callback);
    };

    this.getInfoConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.getInfoConsulta(pIdConsulta, callback);
    };
    this.resolverConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.resolverConsulta(pIdConsulta, callback);
    };
    
    this.borrarConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.borrarConsulta(parseInt(pIdConsulta), callback);
    };
};

exports.AdminConsulta = AdminConsulta;