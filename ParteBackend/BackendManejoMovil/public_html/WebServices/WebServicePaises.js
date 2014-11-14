/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminPaises = require('../Logica/AdminPaises.js');
var servidor = require('./Servidor.js');


WebServicePais = function () {
    var admin = new adminPaises.AdminPaises();

    this.getPaises = function (req, res) {
        admin.getPaises(function (data) {
			servidor.responderJson(res, data);
        });
    };
};


exports.WebServicePais = WebServicePais;