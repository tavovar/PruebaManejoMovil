/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Random() {
    this.usuarios = [];

    this.AgregarUsuario = function (pIdUsuario) {
        var numeros = [];
        var mUsuario = {pIdUsuario: numeros};
        this.usuarios.push(mUsuario);
    };

    this.BuscarRandom = function (pIdUsuario, pRandom) {
        var mLargo = this.usuarios[pIdUsuario].length;
        var mResultado = false;
        for (var i = 0; i < mLargo; i++) {
            if (this.usuarios[pIdUsuario][i] === pRandom) {
                mResultado = true;
                break;
            }
        }
        return mResultado;
    };

    this.getRandom = function (pIdUsuario, pMinimo, pMaximo) {
        if (this.usuarios[pIdUsuario] === null || this.usuarios[pIdUsuario] === undefined) {
            return;
        }
        var random;
        for (var i = 0; i < 10; i++) {
            random = Math.floor(Math.random() * (pMaximo - pMinimo)) + pMinimo;
            if (this.BuscarRandom(pIdUsuario,random)===false){
                return;
            }
        }
        console.log(this.usuarios);
        this.usuarios[pIdUsuario].push(random);
        return ramdom;
    };
}

var VariablesRandom = new Random();

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

    this.getEliminarPreguntasDinamicasUsuario = function (pIdUsuario, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatosSinInyection("DELETE FROM preguntas_enviadas WHERE pk_usuario = ?", pIdUsuario, callback);
    };

    this.getPreguntaDinamica = function (pIdUsuario, callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("SELECT MAX(pk_pregunta_dinamica), MIN(pk_pregunta_dinamica) FROM preguntas_dinamicas", function (pFilas) {
            var Maximo = pFilas[0]['MAX(pk_pregunta_dinamica)'] + 1;
            var Minimo = pFilas[0]['MIN(pk_pregunta_dinamica)'];

            var ramdom = VariablesRandom.getRandom(1, Minimo, Maximo);

            callback(ramdom + "");
        });
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