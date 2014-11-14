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
        var query = "select pk_seccion, nombre from secciones WHERE fk_manual=" + pIdManual;
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
        var query = "select pk_subseccion, nombre from subsecciones WHERE fk_seccion=" + pIdSeccion;
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
        var query = "select pk_subseccion, nombre, descripcion from subsecciones WHERE pk_subseccion=" + pIdSubSeccion;
        mConexion.getDatos(query, callback);
    };
};

exports.ConexionManuales = ConexionManuales;