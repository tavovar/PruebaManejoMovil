package com.example.gustavovargas.movilv20.ParseJson;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class ConexionAPI {


    public Pregunta[] solicitarPreguntasTeoricas(){


        Pregunta listaPreguntas[] = new Pregunta[40];
        BufferedReader in = null;
        String data = null;

        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            URI website = new URI("http://google.com");
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            // NEW CODE
            String line = in.readLine();
            Log.v("---------> "," First line: " + line);
            // END OF NEW CODE

            Log.v("---------> "," Connected ");




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


        } catch (Exception e) {
            Log.v("++++++++++++++++++++++++++++++++++++++",e.toString());
            for(int i = 0; i<40; i++){
                Respuesta r1 = new Respuesta();
                r1.respuesta = "Respuesta 1";
                Respuesta r2 = new Respuesta();
                r2.respuesta = "Respuesta 2";
                Respuesta r3 = new Respuesta();
                r3.respuesta = "Respuesta 3";
                if(i%2!=0){
                    r2.correcta = true;
                }else{
                    r1.correcta = true;
                }
                Respuesta r[] = {r1,r2,r3};
                Pregunta p = new Pregunta();
                p.pregunta = "Esta es la decripcion de la pregunta #"+i+" que debe de ser cargada del web services";
                p.respuestas = r;
                listaPreguntas[i] = p;
            }
        }

        return listaPreguntas;
    }
}
