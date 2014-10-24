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
        mConexion.close();
    };

    this.agregarLugar = function (pLugar, callback) {

        var mObjeto = {nombre: pLugar.nombre, latitud: pLugar.latitud, longitud: pLugar.longitud, telefono: pLugar.telefono, fk_pais: pLugar.fk_pais};
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO sucursales SET ?", mObjeto, callback);
        mConexion.close();

    };
};

exports.ConexionLugares = ConexionLugares;