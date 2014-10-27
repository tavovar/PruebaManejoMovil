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
        mConexion.close();
    };

    this.agregarHistorial = function (pIdUsuario, pObjeto, callback) {

        var mObjeto = {preguntas_correctas: pObjeto.preguntas_correctas, fecha: pObjeto.fecha, fk_usuario: pObjeto.fk_usuario, tipo: pObjeto.tipo};
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO historiales SET ?", mObjeto, callback);
        mConexion.close();

    };
    
};

exports.ConexionHistorial = ConexionHistorial;