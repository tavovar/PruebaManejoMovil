/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Random = function () {
    this.usuarios = [];
    this.usuarios = {};

    this.AgregarUsuario = function (pIdUsuario) {
        var numeros = [];
        var mUsuario = {};
        this.usuarios[pIdUsuario] = numeros;
    };

    this.BuscarRandom = function (pIdUsuario, pRandom) {
        //console.log(this.usuarios[pIdUsuario]);
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

    this.getRandom = function (pNumeroPregunta, pIdUsuario, pMinimo, pMaximo) {
        if (pNumeroPregunta === 0 || this.usuarios[pIdUsuario] === null || this.usuarios[pIdUsuario] === undefined) {
            //console.log("AGREGANDO UN NUEVO USUARIO");
            this.AgregarUsuario(pIdUsuario);
        }
        var random;

        for (var i = 0; i < 10; i++) {
            random = Math.floor(Math.random() * (pMaximo - pMinimo)) + pMinimo;
            if (this.BuscarRandom(pIdUsuario, random) === false) {
                break;
            }
        }
        this.usuarios[pIdUsuario].push(random);
        //console.log(this.usuarios);
        return random;
    };
};

var VariablesRandom = new Random();

AdminPreguntas = function () {
    var Constantes = require('../Constantes/Constantes.js');
    var ObjetoConstantes = new Constantes.Constantes();

    var conexionPreguntas = require('../Datos/ConexionPreguntas.js');
    this.ConPreguntas = new conexionPreguntas.ConexionPreguntas();

    this.getPreguntas = function (pIdUsuario, pNumeroPregunta, pManual, pFuncion) {
        var mAutoReferencia = this;
        var ConexionPreguntasBase = this.ConPreguntas;
        ConexionPreguntasBase.getExtremosPreguntas(function (pFilas) {
            var Maximo = pFilas[0]['MAX(pk_preguntas)'] + 1;
            var Minimo = pFilas[0]['MIN(pk_preguntas)'];
            var ramdom = VariablesRandom.getRandom(pNumeroPregunta, pIdUsuario, Minimo, Maximo);
            ConexionPreguntasBase.getPreguntaEspecifica(pManual, ramdom, function (pFilas) {
                if (pFilas.length === 0) {
                    mAutoReferencia.getPreguntas(pIdUsuario, pNumeroPregunta, pFuncion);
                }
                else {
                    pFuncion(pFilas);
                }
            });
        });
        return;
        //return this.ConPreguntas.getPreguntas(pIdUsuario, pNumeroPregunta, pManual, pFuncion);
    };

    this.getPreguntasSubSeccion = function (pIdSubSeccion, pFuncion) {
        return this.ConPreguntas.getPreguntasSubSeccion(pIdSubSeccion, pFuncion);
    };

    this.agregarPregunta = function (pObjeto, pFuncion) {
        var mObjeto = {encabezado: pObjeto.encabezado, correcta: pObjeto.correcta, incorrecta_1: pObjeto.incorrecta_1,
            incorrecta_2: pObjeto.incorrecta_2, incorrecta_3: "", fk_subseccion: pObjeto.fk_subseccion};

        this.ConPreguntas.agregarPregunta(mObjeto, pFuncion);
    };

    this.agregarPreguntaDinamica = function (pObjeto, pFuncion) {
        var mObjeto = {ruta_imagen: pObjeto.ruta, accion: pObjeto.accion, velocidad: pObjeto.velocidad};
        this.ConPreguntas.agregarPreguntaDinamica(mObjeto, pFuncion);
    };

    this.getPreguntasDinamicas = function (pFuncion) {
        return this.ConPreguntas.getPreguntasDinamicas(pFuncion);
    };

    this.getPreguntaDinamica = function (pIdUsuario, pNumeroPregunta, pFuncion) {
        var mAutoReferencia = this;
        var ConexionPreguntasBase = this.ConPreguntas;
        ConexionPreguntasBase.getExtremosPreguntasDinamicas(function (pFilas) {
            var Maximo = pFilas[0]['MAX(pk_pregunta_dinamica)'] + 1;
            var Minimo = pFilas[0]['MIN(pk_pregunta_dinamica)'];
            var ramdom = VariablesRandom.getRandom(pNumeroPregunta, pIdUsuario, Minimo, Maximo);
            ConexionPreguntasBase.getPreguntaDinamicaEspecifica(ramdom, function (pFilas) {
                if (pFilas.length === 0) {
                    mAutoReferencia.getPreguntaDinamica(pIdUsuario, pNumeroPregunta, pFuncion);
                }
                else {
                    pFuncion(pFilas);
                }
            });
        });
        
        //return this.ConPreguntas.getPreguntaDinamica(pIdUsuario, pNumeroPregunta, pFuncion);
    };

    this.borrarPreguntas = function (pIdPregunta, pFuncion) {
        return this.ConPreguntas.borrarPreguntas(pIdPregunta, pFuncion);
    };

    this.borrarPreguntasDinamicas = function (pIdPreguntaDinamica, pFuncion) {
        return this.ConPreguntas.borrarPreguntasDinamicas(parseInt(pIdPreguntaDinamica), pFuncion);
    };
};

exports.AdminPreguntas = AdminPreguntas;

