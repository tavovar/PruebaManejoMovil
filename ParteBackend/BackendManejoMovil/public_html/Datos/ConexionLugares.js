/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ConexionLugares = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getLugares = function (pPais, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select * from sucursales", callback);
    };

    this.agregarLugar = function (pLugar, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO sucursales SET ?", pLugar, callback);
    };
    
    this.borrarLugar = function (pLugar, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("DELETE FROM sucursales WHERE pk_sucursal = ?", pLugar, callback);
    };
};

exports.ConexionLugares = ConexionLugares;