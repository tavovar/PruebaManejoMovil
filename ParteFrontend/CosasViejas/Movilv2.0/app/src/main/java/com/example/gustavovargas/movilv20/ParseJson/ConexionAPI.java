package com.example.gustavovargas.movilv20.ParseJson;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Pregunta;
import com.example.gustavovargas.movilv20.Modelos.Respuesta;
import com.example.gustavovargas.movilv20.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class ConexionAPI {


    public Pregunta solicitarPreguntasTeoricas() {
        BufferedReader in = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI(Constantes.URLPreguntas);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return JsonToPregunta(in.readLine());
        } catch (Exception e) {
            Log.v("Error ---------> ", e.toString());
            return null;
        }
    }



    public boolean SugerenciaTestJson(int preguntasCorrectas, double usuario, int tipo) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(Constantes.tipoResultado, tipo);
        jsonObj.put(Constantes.preguntasCorrectasResultado, preguntasCorrectas);
        jsonObj.put(Constantes.idUsuarioResultado,usuario);
        jsonObj.put(Constantes.fecha, new Date());
        //Log.v("JSON Resultado",jsonObj.toString());
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            URI website = new URI(Constantes.URLPreguntas);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("Json", jsonObj.toString()));
            //nameValuePair.add(new BasicNameValuePair("id", "123456789"));
            request.setURI(website);
            request.setEntity(new UrlEncodedFormEntity(nameValuePair));
            httpclient.execute(request);
            Log.v("Correcto --------------------------> ",jsonObj.toString());
            return true;
        } catch (Exception e) {
            Log.v("Error --------------------------> ", e.toString());
            return false;
        }
    }



    public boolean ResultadoTestJson(int preguntasCorrectas, double usuario, int tipo) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(Constantes.tipoResultado, tipo);
        jsonObj.put(Constantes.preguntasCorrectasResultado, preguntasCorrectas);
        jsonObj.put(Constantes.idUsuarioResultado,usuario);
        jsonObj.put(Constantes.fecha, new Date());
        BufferedReader in = null;
        //Log.v("JSON Resultado",jsonObj.toString());
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            URI website = new URI(Constantes.URLResultados);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("Json", jsonObj.toString()));
            //nameValuePair.add(new BasicNameValuePair("id", "123456789"));
            request.setURI(website);
            request.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Log.v("Correcto --------------------------> ",in.readLine()+"    --->");
            return true;
        } catch (Exception e) {
            Log.v("Error --------------------------> ", e.toString());
            return false;
        }
    }





    private Pregunta JsonToPregunta(String json) throws JSONException{
        //Log.v("---------> ", " First line: " + json);
        Pregunta pregunta = new Pregunta();
        JSONArray objArray = new JSONArray(json);
        JSONObject obj = objArray.getJSONObject(0);
        Random rand = new Random();
        int i = rand.nextInt(2);
        Respuesta r1 = new Respuesta();
        r1.respuesta = obj.getString(Constantes.respuestaCorrecta);
        r1.correcta = true;
        Respuesta r2 = new Respuesta();
        r2.respuesta = obj.getString(Constantes.respuestaIncorrecta_1);
        r2.correcta = false;
        Respuesta r3 = new Respuesta();
        r3.respuesta = obj.getString(Constantes.respuestaIncorrecta_2);
        r3.correcta = false;
        //Log.v("NUMERO RANDOM DE RESPUESTA",""+i);
        if(i==0){
            Respuesta r[] = {r1, r2, r3};
            pregunta.respuestas = r;
        }else if(i==1){
            Respuesta r[] = {r2, r1, r3};
            pregunta.respuestas = r;
        }else{
            Respuesta r[] = {r2, r3, r1};
            pregunta.respuestas = r;
        }
        pregunta.pregunta = obj.getString(Constantes.encabezadoPregunta);
        pregunta.id = obj.getInt(Constantes.idPregunta);
        pregunta.idSubseccion = obj.getInt(Constantes.idSubseccion);
        return pregunta;
    }
}
