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
        mConexion.getDatosSinInyection("select preguntas_correctas, fecha, tipo from historiales WHERE fk_usuario = ? GROUP BY fecha DESC, pk_historial DESC;", pIdUsuario, callback);
    };

    this.getHistorialSemanas = function (pIdUsuario, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("select WEEK(fecha), preguntas_correctas, fecha, tipo from historiales WHERE fk_usuario = ? GROUP BY YEAR(fecha) DESC, WEEK(fecha) DESC, pk_historial DESC;", pIdUsuario, callback);
    };
    
    this.getHistorialMeses = function (pIdUsuario, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("select MONTH(fecha), preguntas_correctas, fecha, tipo from historiales WHERE fk_usuario = ? GROUP BY YEAR(fecha) DESC, MONTH(fecha) DESC, pk_historial DESC;", pIdUsuario, callback);
    };

    this.agregarHistorial = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        console.log(pObjeto);
        mConexion.saveDato("INSERT INTO historiales SET ?", pObjeto, callback);

    };
    
};

exports.ConexionHistorial = ConexionHistorial;