/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ConexionManuales = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getManuales = function (fk_pais, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();

        mConexion.getDatosSinInyection("select pk_manual, nombre from manuales WHERE fk_pais = ?",fk_pais, callback);
    };
    
    this.getTodosManuales = function ( callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("select pk_manual, nombre from manuales", callback);
    };

    this.agregarManual = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();

        mConexion.saveDato("INSERT INTO manuales SET ?", pObjeto, callback);
    };
    
    this.getSecciones = function (pIdManual, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "select pk_seccion, nombre, indice from secciones WHERE fk_manual=" + pIdManual;
        mConexion.getDatos(query, callback);
    };
    
    this.agregarSeccion = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();

        mConexion.saveDato("INSERT INTO secciones SET ?", pObjeto, callback);
    };
    
    this.getSubSecciones = function (pIdSeccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "select pk_subseccion, nombre, indice from subsecciones WHERE fk_seccion=" + pIdSeccion;
        mConexion.getDatos(query, callback);
    };
    
    this.agregarSubseccion = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO subsecciones SET ?", pObjeto, callback);
    };
    
    this.getSubSeccion = function (pIdSubSeccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "select pk_subseccion, nombre, descripcion, indice from subsecciones WHERE pk_subseccion=" + pIdSubSeccion;
        mConexion.getDatos(query, callback);
    };
    
    this.borrarManual = function (pIdManual, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "DELETE FROM manuales WHERE pk_manual = ?";
        mConexion.getDatosSinInyection(query, pIdManual, callback);
    };
    
    this.borrarSeccion = function (pIdSeccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "DELETE FROM secciones WHERE pk_seccion = ?";
        mConexion.getDatosSinInyection(query, pIdSeccion, callback);
    };
    
    this.borrarSubseccion = function (pIdSubseccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        var query = "DELETE FROM subsecciones WHERE pk_subseccion = ?";
        mConexion.getDatosSinInyection(query, pIdSubseccion, callback);
    };
};

exports.ConexionManuales = ConexionManuales;