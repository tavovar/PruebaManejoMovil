/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ConexionHistorial = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getTodoHistorial = function (pIdUsuario, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select * from historiales where fk_usuario="+pIdUsuario, callback);
    };

    this.agregarHistorial = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        console.log(pObjeto);
        mConexion.saveDato("INSERT INTO historiales SET ?", pObjeto, callback);

    };
    
};

exports.ConexionHistorial = ConexionHistorial;