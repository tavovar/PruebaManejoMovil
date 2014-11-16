/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Random = function () {
//    this.usuarios = [];
//    this.usuarios = {};
//
//    this.AgregarUsuario = function (pIdUsuario) {
//        var numeros = [];
//        var mUsuario = {};
//        this.usuarios[pIdUsuario] = numeros;
//    };
//
//    this.BuscarRandom = function (pIdUsuario, pRandom) {
//        console.log(this.usuarios[pIdUsuario]);
//        var mLargo = this.usuarios[pIdUsuario].length;
//        var mResultado = false;
//        for (var i = 0; i < mLargo; i++) {
//            if (this.usuarios[pIdUsuario][i] === pRandom) {
//                mResultado = true;
//                break;
//            }
//        }
//        return mResultado;
//    };
//
//    this.getRandom = function (pNumeroPregunta, pIdUsuario, pMinimo, pMaximo) {
//        if (pNumeroPregunta === 0 || this.usuarios[pIdUsuario] === null || this.usuarios[pIdUsuario] === undefined) {
//            console.log("AGREGANDO UN NUEVO USUARIO");
//            this.AgregarUsuario(pIdUsuario);
//        }
//        var random;
//
//        for (var i = 0; i < 10; i++) {
//            random = Math.floor(Math.random() * (pMaximo - pMinimo)) + pMinimo;
//            if (this.BuscarRandom(pIdUsuario, random) === false) {
//                break;
//            }
//        }
//        this.usuarios[pIdUsuario].push(random);
//        console.log(this.usuarios);
//        return random;
//    };
//};
//
//var VariablesRandom = new Random();

var ConexionPreguntas = function () {

    var conexionDB = require('./ConexionDB.js');
    this.conexion = null;

    this.getExtremosPreguntas = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("SELECT MAX(pk_preguntas), MIN(pk_preguntas) FROM preguntas", callback);
    };

    this.getPreguntaEspecifica = function (pIdManul, pIdPregunta, callback) {
        var mNuevaConexion = new conexionDB.ConexionDB();
        mNuevaConexion.conectar();
        var superquery = "SELECT pk_preguntas, encabezado, correcta , incorrecta_1, incorrecta_2, fk_subseccion FROM ( \
	SELECT pk_subseccion FROM  \
		(SELECT pk_seccion FROM secciones WHERE fk_manual = ?) \
		 tSec JOIN subsecciones tSub ON tSec.pk_seccion = tSub.fk_seccion \
	) tSecSub JOIN preguntas tPre ON tSecSub.pk_subseccion = tPre.fk_subseccion \
        WHERE pk_preguntas = ?;";
        //mConexion.getDatosSinInyection("SELECT * FROM preguntas_dinamicas WHERE pk_pregunta_dinamica = ?", ramdom, function (pFilas) {
        mNuevaConexion.getDatosSinInyection(superquery, [pIdManul, pIdPregunta], callback);
    };

//    this.getPreguntas = function (pIdUsuario, pNumeroPregunta, pManual, callback) {
//        var mAutoReferencia = this;
//        var mConexion = new conexionDB.ConexionDB();
//        mConexion.conectar();
//        var superquery = "SELECT pk_preguntas, encabezado, correcta , incorrecta_1, incorrecta_2, fk_subseccion FROM ( \
//	SELECT pk_subseccion FROM  \
//		(SELECT pk_seccion FROM secciones WHERE fk_manual = ?) \
//		 tSec JOIN subsecciones tSub ON tSec.pk_seccion = tSub.fk_seccion \
//	) tSecSub JOIN preguntas tPre ON tSecSub.pk_subseccion = tPre.fk_subseccion \
//        WHERE pk_preguntas = ?;";
//        //mConexion.getDatos("SELECT * FROM (SELECT pk_preguntas,encabezado,correcta,incorrecta_1,incorrecta_2,fk_subseccion  FROM preguntas ORDER BY RAND()) as randomTable LIMIT 1", callback);
//        mConexion.getDatos("SELECT MAX(pk_preguntas), MIN(pk_preguntas) FROM preguntas", function (pFilas) {
//            var Maximo = pFilas[0]['MAX(pk_preguntas)'] + 1;
//            var Minimo = pFilas[0]['MIN(pk_preguntas)'];
//            var ramdom = VariablesRandom.getRandom(pNumeroPregunta, pIdUsuario, Minimo, Maximo);
//            var mNuevaConexion = new conexionDB.ConexionDB();
//            mNuevaConexion.conectar();
//            //mConexion.getDatosSinInyection("SELECT * FROM preguntas_dinamicas WHERE pk_pregunta_dinamica = ?", ramdom, function (pFilas) {
//            mConexion.getDatosSinInyection(superquery, [pManual, ramdom], function (pFilas) {
//                if (pFilas.length === 0) {
//                    mAutoReferencia.getPreguntas(pIdUsuario, pNumeroPregunta, callback);
//                }
//                else {
//                    callback(pFilas);
//                }
//            });
//        });
//    };
    
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

    this.getExtremosPreguntasDinamicas = function (callback) {
        var mConexion = new conexionDB.ConexionDB();
        mConexion.conectar();
        mConexion.getDatos("SELECT MAX(pk_pregunta_dinamica), MIN(pk_pregunta_dinamica) FROM preguntas_dinamicas", callback);
    };

    this.getPreguntaDinamicaEspecifica = function (pIdPreguntaDinamica, callback) {
        var mNuevaConexion = new conexionDB.ConexionDB();
        mNuevaConexion.conectar();
        mNuevaConexion.getDatosSinInyection("SELECT * FROM preguntas_dinamicas WHERE pk_pregunta_dinamica = ?", pIdPreguntaDinamica, callback);
    };

//    this.getPreguntaDinamica = function (pIdUsuario, pNumeroPregunta, callback) {
//        var mAutoReferencia = this;
//        var mConexion = new conexionDB.ConexionDB();
//        mConexion.conectar();
//        mConexion.getDatos("SELECT MAX(pk_pregunta_dinamica), MIN(pk_pregunta_dinamica) FROM preguntas_dinamicas", function (pFilas) {
//            var Maximo = pFilas[0]['MAX(pk_pregunta_dinamica)'] + 1;
//            var Minimo = pFilas[0]['MIN(pk_pregunta_dinamica)'];
//            var ramdom = VariablesRandom.getRandom(pNumeroPregunta, pIdUsuario, Minimo, Maximo);
//            var mNuevaConexion = new conexionDB.ConexionDB();
//            mNuevaConexion.conectar();
//            mConexion.getDatosSinInyection("SELECT * FROM preguntas_dinamicas WHERE pk_pregunta_dinamica = ?", ramdom, function (pFilas) {
//                if (pFilas.length === 0) {
//                    mAutoReferencia.getPreguntaDinamica(pIdUsuario, pNumeroPregunta, callback);
//                }
//                else {
//                    callback(pFilas);
//                }
//            });
//        });
//    };

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