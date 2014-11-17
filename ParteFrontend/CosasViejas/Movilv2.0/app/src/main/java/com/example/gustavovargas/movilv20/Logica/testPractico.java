package com.example.gustavovargas.movilv20.Logica;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.PreguntaPractica;
import com.example.gustavovargas.movilv20.Modelos.Usuario;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;

import org.json.JSONException;

/**
 * Created by gustavovargas on 12/11/14.
 */
public class testPractico {



    private PreguntaPractica pregunta;
    public int preguntaActual=-1;
    public boolean resultado;
    public int preguntasCorrectas;
    ConexionAPI test = new ConexionAPI();

    private static testPractico instance = null;
    public static testPractico getInstance() {
        if(instance == null) {
            instance = new testPractico();
        }
        return instance;
    }

    protected testPractico(){
        pregunta = test.solicitarPreguntasPractica(0,Usuario.getInstance().id);
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }


    public PreguntaPractica SiguintePregunta(){
        preguntaActual++;
        pregunta = test.solicitarPreguntasPractica(1,Usuario.getInstance().id);
        if (preguntaActual< Constantes.maximoNumPregTestPractico){
            return pregunta;
        }else{
            if(preguntasCorrectas>Constantes.numMiniPregNotaAprovPractico){
                resultado = true;
            }else{
                resultado = false;
            }
            return null;
        }
    }

    public boolean revisarRespuesta(int respuesta, int velocidad){
        if(pregunta.interaccion == respuesta && velocidad < pregunta.velocidad){
            preguntasCorrectas++;
            return true;
        }else{
            return false;
        }
    }

    public void reiniciarTestTeorico(){
        ConexionAPI test = new ConexionAPI();
        pregunta = test.solicitarPreguntasPractica(0,Usuario.getInstance().id);
        preguntaActual = -1;
        resultado = false;
        preguntasCorrectas = 0;
    }


    public void guardarResultadosTestPractico(){
        ConexionAPI test = new ConexionAPI();
        try {
            test.ResultadoTestJson(preguntasCorrectas, Usuario.getInstance().id,Constantes.tipoPractico);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }






}
