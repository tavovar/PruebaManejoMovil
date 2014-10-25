package com.example.gustavovargas.movilv20.ParseJson;

import com.example.gustavovargas.movilv20.Modelos.Pregunta;
import com.example.gustavovargas.movilv20.Modelos.Respuesta;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class ConexionAPI {


    public Pregunta[] solicitarPreguntasTeoricas(){
        Pregunta listaPreguntas[] = new Pregunta[40];
        Respuesta r1 = new Respuesta();
        r1.respuesta = "Respuesta1";
        Respuesta r2 = new Respuesta();
        r2.respuesta = "Respuesta2";
        r2.correcta = true;
        Respuesta r3 = new Respuesta();
        r3.respuesta = "Respuesta3";
        Respuesta r[] = {r1,r2,r3};
        for(int i = 0; i<40; i++){
            Pregunta p = new Pregunta();
            p.pregunta = "Pregunta #"+i;
            p.respuestas = r;
            listaPreguntas[i] = p;
        }
        return listaPreguntas;
    }
}
