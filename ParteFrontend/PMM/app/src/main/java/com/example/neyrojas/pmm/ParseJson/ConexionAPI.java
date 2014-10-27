package com.example.neyrojas.pmm.ParseJson;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import com.example.neyrojas.pmm.Modelos.Pregunta;
import com.example.neyrojas.pmm.Modelos.Respuesta;
import com.example.neyrojas.pmm.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class ConexionAPI {


    public Pregunta[] solicitarPreguntasTeoricas(){

        //location of file to retrieve
        String url = "http://www.pruebamanejomovil.com/app/preguntas";
        Pregunta listaPreguntas[] = new Pregunta[40];

        HttpClient httpclient = new DefaultHttpClient();

        // Prepare a request object
        HttpGet httpget = new HttpGet(url);

        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            // Examine the response status
            Log.i("Praeda",response.getStatusLine().toString());

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                //String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
            }
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
