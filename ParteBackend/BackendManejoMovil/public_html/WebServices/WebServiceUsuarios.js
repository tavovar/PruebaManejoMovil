/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var adminUsuarios = require('../Logica/AdminUsuarios.js');
var servidor = require('./Servidor.js');

WebServiceUsuario = function () {
    var admin = new adminUsuarios.AdminUsuarios();
    
    this.IdentificarseWeb = function (req, res) {
        console.log(req.body.nombre);
        console.log(req.body.contrasena);
        if (admin.identificacionWeb(req.body.nombre, req.body.contrasena)===true){
            servidor.responderJson(res, true);
        }
        else{
            servidor.responderJson(res, false);
        }
        
    };
};

exports.WebServiceUsuario = WebServiceUsuario;