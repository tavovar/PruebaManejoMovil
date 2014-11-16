/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ConexionUsuarios = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.agregarUsuario = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO usuarios SET ?", pObjeto, callback);
    };
    
    this.existeUsuario = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "SELECT * FROM usuarios WHERE id_red_social = ? AND tipo_usuario = ?";
        mConexion.getDatosSinInyection(query, [pObjeto.id_red_social, pObjeto.tipo_usuario], callback);
    };

};

exports.ConexionUsuarios = ConexionUsuarios;