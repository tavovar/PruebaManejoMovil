/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


AdminPreguntas = function () {
    var Constantes = require('../Constantes/Constantes.js');
    var ObjetoConstantes = new Constantes.Constantes();

    var conexionPreguntas = require('../Datos/ConexionPreguntas.js');
    this.ConPreguntas = new conexionPreguntas.ConexionPreguntas();

    this.getPreguntas = function (pPais, pFuncion) {
        return this.ConPreguntas.getPreguntas(pPais, pFuncion);
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

    this.borrarPreguntas = function (pIdPregunta, pFuncion) {
        return this.ConPreguntas.borrarPreguntas(pIdPregunta, pFuncion);
    };

    this.borrarPreguntasDinamicas = function (pIdPreguntaDinamica, pFuncion) {
        return this.ConPreguntas.borrarPreguntasDinamicas(parseInt(pIdPreguntaDinamica), pFuncion);
    };
};

exports.AdminPreguntas = AdminPreguntas;

