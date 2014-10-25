/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ConexionUsuarios = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.agregarUsuario = function (pObjeto, callback) {
        var mObjeto = {pk_id: pObjeto.pk_id, tipo_usuario: pObjeto.tipo_usuario};
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO usuarios SET ?", mObjeto, callback);
        mConexion.close();
    };
    
    this.existeUsuario = function (pObjeto, callback) {
        var mObjeto = {pk_id: pObjeto.pk_id, tipo_usuario: pObjeto.tipo_usuario};
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "SELECT * FROM usuarios WHERE pk_id="+mObjeto.pk_id;
        mConexion.getDatos(query, callback);
        mConexion.close();
    };

};

exports.ConexionUsuarios = ConexionUsuarios;