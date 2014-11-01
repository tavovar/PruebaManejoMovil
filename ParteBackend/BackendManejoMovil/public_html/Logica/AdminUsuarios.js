/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Constantes = require('../Constantes/Constantes.js');


AdminUsuarios = function () {
    var conexionPreguntas = require('../Datos/ConexionPreguntas.js');
    this.ConPreguntas = new conexionPreguntas.ConexionPreguntas();

    var ObjetoConstantes = new Constantes.Constantes();

    this.identificacionWeb = function(pUsuario, pContrasena){
        console.log(pUsuario + ObjetoConstantes.UsuarioWeb);
        if (pUsuario === ObjetoConstantes.UsuarioWeb && pContrasena===ObjetoConstantes.Password){
            return true;
        }
        else{
            return false;
        }
    };
};

exports.AdminUsuarios = AdminUsuarios;