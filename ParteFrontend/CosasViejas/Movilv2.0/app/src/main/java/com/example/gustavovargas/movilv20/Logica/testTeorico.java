package com.example.gustavovargas.movilv20.Logica;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Pregunta;
import com.example.gustavovargas.movilv20.Modelos.Respuesta;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class testTeorico {

    private Pregunta listaPreguntas[];
    public int preguntaActual=-1;
    public boolean resultado;
    public int preguntasCorrectas;

    private static testTeorico instance = null;

    public static testTeorico getInstance() {
        if(instance == null) {
            instance = new testTeorico();
        }
        return instance;
    }

    protected testTeorico(){
        ConexionAPI test = new ConexionAPI();
        listaPreguntas = test.solicitarPreguntasTeoricas();
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }


    public Pregunta SiguintePregunta(){
        preguntaActual++;
        if (preguntaActual< Constantes.maximoNumPregTestTeo){
            return listaPreguntas[preguntaActual];
        }else{
            if(preguntasCorrectas>Constantes.numMiniPregNotaAprov){
                resultado = true;
            }else{
                resultado = false;
            }
            return null;
        }
    }

    public boolean revisarRespuesta(int respuesta){
        Pregunta pregunta = listaPreguntas[(preguntaActual)];
        if(pregunta.respuestas[respuesta].correcta == true){
            preguntasCorrectas++;
            return true;
        }else{
            return false;
        }
    }

    public int respuestaCorrecta(){
        Pregunta pregunta = listaPreguntas[(preguntaActual)];
        if(pregunta.respuestas[0].correcta == true){
            return 1;
        }else if (pregunta.respuestas[1].correcta == true){
            return 2;
        }else{
            return 3;
        }
    }

    public void reiniciarTestTeorico(){
        ConexionAPI test = new ConexionAPI();
        listaPreguntas = test.solicitarPreguntasTeoricas();
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }

}
