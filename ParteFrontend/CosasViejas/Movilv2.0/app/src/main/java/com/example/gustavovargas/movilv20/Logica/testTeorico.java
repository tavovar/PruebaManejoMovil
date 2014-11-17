package com.example.gustavovargas.movilv20.Logica;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.PreguntaTeorica;
import com.example.gustavovargas.movilv20.Modelos.Usuario;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;

import org.json.JSONException;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class testTeorico {

    private PreguntaTeorica pregunta;
    public int preguntaActual=-1;
    public boolean resultado;
    public int preguntasCorrectas;
    ConexionAPI test = new ConexionAPI();

    private static testTeorico instance = null;

    public static testTeorico getInstance() {
        if(instance == null) {
            instance = new testTeorico();
        }
        return instance;
    }

    protected testTeorico(){
        pregunta = test.solicitarPreguntasTeoricas(0,Usuario.getInstance().id,Usuario.getInstance().pais);
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }


    public PreguntaTeorica SiguintePregunta(){
        preguntaActual++;
        pregunta = test.solicitarPreguntasTeoricas(1,Usuario.getInstance().id,Usuario.getInstance().pais);
        if (preguntaActual< Constantes.maximoNumPregTestTeo){
            return pregunta;
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
        if(pregunta.respuestas[respuesta].correcta == true){
            preguntasCorrectas++;
            return true;
        }else{
            return false;
        }
    }

    public int respuestaCorrecta(){
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
        pregunta = test.solicitarPreguntasTeoricas(0,Usuario.getInstance().id,Usuario.getInstance().pais);
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }


    public void guardarResultadosTestTeorico(){
        ConexionAPI test = new ConexionAPI();
        try {
            test.ResultadoTestJson(preguntasCorrectas, Usuario.getInstance().id,Constantes.tipoTeorico);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
