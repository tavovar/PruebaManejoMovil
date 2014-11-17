package com.example.gustavovargas.movilv20.ParseJson;

import android.util.Log;


import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.PreguntaPractica;
import com.example.gustavovargas.movilv20.Modelos.PreguntaTeorica;
import com.example.gustavovargas.movilv20.Modelos.Respuesta;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class ConexionAPI {


    public PreguntaTeorica solicitarPreguntasTeoricas(int numPregunta, long usuario, int pais) {
        BufferedReader in = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI(Constantes.URLPreguntas+"?pk_usuario="+usuario+"&numero_pregunta="+numPregunta+"&fk_manual="+pais);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return JsonToPreguntaTeorica(in.readLine());
        } catch (Exception e) {
            Log.v("Error Teorico ---------> ", e.toString());
            return null;
        }
    }

    public PreguntaPractica solicitarPreguntasPractica(int numPregunta, long usuario) {
        BufferedReader in = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI(Constantes.URLPreguntasPracticas+"?pk_usuario="+usuario+"&numero_pregunta="+numPregunta);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return JsonToPreguntaPractica(in.readLine());
        } catch (Exception e) {
            Log.v("Error Practica ---------> ", e.toString());
            return null;
        }
    }



    public boolean SugerenciaTestJson(double usuario, int respuesta, String sugerencia, String email) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(Constantes.idUsuarioSugerencia, usuario);
        jsonObj.put(Constantes.respuestaSugerencia, respuesta);
        jsonObj.put(Constantes.sugerencia, sugerencia);
        jsonObj.put(Constantes.email,email);
        //jsonObj.put(Constantes.fecha,new Date());
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            BufferedReader in = null;
            URI website = new URI(Constantes.URLSugerencias);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("Json", jsonObj.toString()));
            nameValuePair.add(new BasicNameValuePair("identificacion", Constantes.identificacion));
            request.setURI(website);
            request.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            Log.v("Correcto --------------------------> ",jsonObj.toString());
            Log.v("Respuesta --------------------------> ",in.readLine());
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
        Date date = new Date();
        Log.v("FechaResultado", (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate() );
        jsonObj.put(Constantes.fecha, (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
        BufferedReader in = null;
        //Log.v("JSON Resultado",jsonObj.toString());
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            URI website = new URI(Constantes.URLResultados);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("Json", jsonObj.toString()));
            nameValuePair.add(new BasicNameValuePair("identificacion", Constantes.identificacion));
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



    private PreguntaPractica JsonToPreguntaPractica(String json) throws JSONException{
        //Log.v("---------> ", " First line: " + json);
        PreguntaPractica pregunta = new PreguntaPractica();
        JSONArray objArray = new JSONArray(json);
        JSONObject obj = objArray.getJSONObject(0);
        pregunta.pathImagen = obj.getString(Constantes.pathImagenPregunta);
        pregunta.id = obj.getInt(Constantes.idPreguntaPractica);
        pregunta.interaccion = obj.getInt(Constantes.interacionPreguntaPractica);
        pregunta.velocidad = obj.getInt(Constantes.velocidadPreguntaPractica);
        return pregunta;
    }

    private PreguntaTeorica JsonToPreguntaTeorica(String json) throws JSONException{
        //Log.v("---------> ", " First line: " + json);
        PreguntaTeorica pregunta = new PreguntaTeorica();
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


    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
}
