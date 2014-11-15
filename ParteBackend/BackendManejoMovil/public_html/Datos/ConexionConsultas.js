/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ConexionConsulta = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getTodasConsultas = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select pk_consultas, asunto, descripcion, resuelto from consultas", callback);
    };

    this.getInfoConsulta = function (pIdConsulta, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("select pk_consultas, asunto, descripcion, resuelto, correo from consultas where pk_consultas = ?", pIdConsulta, callback);
    };

    this.resolverConsulta = function (pIdConsulta, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("update consultas resuelto = 1 where pk_consultas = ?", pIdConsulta, callback);
    };

    this.borrarConsulta = function (pIdConsulta, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("DELETE FROM consultas where pk_consultas = ?", pIdConsulta, callback);
    };
};

exports.ConexionConsulta = ConexionConsulta;