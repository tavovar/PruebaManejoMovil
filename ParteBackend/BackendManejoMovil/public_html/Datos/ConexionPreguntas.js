/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var ConexionPreguntas = function () {

    var conexionDB = require('./ConexionDB.js');

    this.conexion = null;

    this.getPreguntas = function (pPais, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        //mConexion.getDatos("SELECT * FROM preguntas ORDER BY pk_preguntas LIMIT 20", callback);
        mConexion.getDatos("SELECT * FROM (SELECT pk_preguntas,encabezado,correcta,incorrecta_1,incorrecta_2,fk_subseccion  FROM preguntas ORDER BY RAND()) as randomTable LIMIT 1", callback);
    };

    this.getPreguntasSubSeccion = function (pIdSubSeccion, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        //mConexion.getDatos("SELECT * FROM preguntas ORDER BY pk_preguntas LIMIT 20", callback);
        mConexion.getDatosSinInyection("SELECT pk_preguntas, encabezado FROM preguntas WHERE fk_subseccion = ?", pIdSubSeccion, callback);
    };

    this.agregarPregunta = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO preguntas SET ?", pObjeto, callback);
    };

    this.agregarPreguntaDinamica = function (pObjeto, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO preguntas_dinamicas SET ?", pObjeto, callback);
    };

    this.getPreguntasDinamicas = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        //mConexion.getDatos("SELECT * FROM preguntas ORDER BY pk_preguntas LIMIT 20", callback);
        mConexion.getDatos("SELECT * FROM preguntas_dinamicas", callback);
    };

    this.borrarPreguntas = function (pIdPregunta, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("DELETE FROM preguntas WHERE pk_preguntas = ?", pIdPregunta, callback);
    };

    this.borrarPreguntasDinamicas = function (pIdPreguntaDinamica, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("DELETE FROM preguntas_dinamicas WHERE pk_pregunta_dinamica = ?", pIdPreguntaDinamica, callback);
    };
};

exports.ConexionPreguntas = ConexionPreguntas;