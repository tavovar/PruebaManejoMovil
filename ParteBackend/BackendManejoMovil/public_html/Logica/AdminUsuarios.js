/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Constantes = require('../Constantes/Constantes.js');


AdminUsuarios = function () {
    var conexionUsuarios = require('../Datos/ConexionUsuarios.js');
    this.ConUsuarios = new conexionUsuarios.ConexionUsuarios();

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
    
    this.registraUsuario = function(pObjeto, pFuncion){
        if (pObjeto.tipo_usuario===undefined){
            pObjeto = JSON.parse(pObjeto);
        }
        var AutoReferencia = this;
        var mObjeto = {tipo_usuario:pObjeto.tipo_usuario, id_usuario: pObjeto.id_red_social};
        this.IdentificarUsuarioApp(mObjeto,function(pFilas){
             if (pFilas.length === 0) {
                 AutoReferencia.agregarUsuario(mObjeto, pFuncion);
            }
            else {
                pFuncion(pFilas);
            }
        });
       
    };
    
    this.IdentificarUsuarioApp = function(pObjeto, pFuncion){
        if (pObjeto.tipo_usuario===undefined){
            pObjeto = JSON.parse(pObjeto);
        }
        var mObjeto = {tipo_usuario:pObjeto.tipo_usuario, id_usuario: pObjeto.id_red_social};
        this.ConUsuarios.existeUsuario(mObjeto, pFuncion);
    };
};

exports.AdminUsuarios = AdminUsuarios;