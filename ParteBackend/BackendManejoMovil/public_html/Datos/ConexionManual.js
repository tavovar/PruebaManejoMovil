/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ConexionManuales = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getManuales = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select * from manuales", callback);
    };

    this.agregarManual = function (pObjeto, callback) {
        var mObjeto = {nombre: pObjeto.nombre, fk_pais: pObjeto.fk_pais };
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO manuales (nombre, fk_pais) SET ?", mObjeto, callback);
    };
    
    this.getSecciones = function (pIdManual, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "select * from secciones WHERE pk_seccion=" + pIdManual;
        mConexion.getDatos(query, callback);
    };
    
    this.agregarSeccion = function (pObjeto, callback) {
        var mObjeto = {nombre: pObjeto.nombre, fk_manual: pObjeto.fk_manual };
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO secciones (nombre, fk_manual) SET ?", mObjeto, callback);
    };
    
    this.getSubSecciones = function (pIdSeccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "select * from subsecciones WHERE pk_subseccion=" + pIdSeccion;
        mConexion.getDatos(query, callback);
    };
    
    this.agregarSubseccion = function (pObjeto, callback) {
        var mObjeto = {nombre: pObjeto.nombre, descripcion: pObjeto.descripcion, fk_seccion: pObjeto.fk_seccion };
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO subsecciones (nombre, descripcion, fk_pais) SET ?", mObjeto, callback);
    };
    
};

exports.ConexionManuales = ConexionManuales;