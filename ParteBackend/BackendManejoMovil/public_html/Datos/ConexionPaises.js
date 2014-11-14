/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ConexionPaises = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getPaises = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select pk_pais, nombre from paises", callback);
    };
    
};

exports.ConexionPaises = ConexionPaises;