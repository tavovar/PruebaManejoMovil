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
        mConexion.getDatos("select * from preguntas", callback);
        mConexion.close();
    };

    this.agregarPregunta = function (pObjeto, callback) {
        var mObjeto = {encabezado: pObjeto.encabezado, correcta: pObjeto.correcta, incorrecta_1: pObjeto.incorrecta_1,
            incorrecta_2: pObjeto.incorrecta_2, incorrecta_3: "", fk_subseccion: pObjeto.fk_subseccion };
        
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.saveDato("INSERT INTO preguntas SET ?", mObjeto, callback);
        mConexion.close();
    };
};

exports.ConexionPreguntas = ConexionPreguntas;