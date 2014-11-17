package com.example.neyrojas.pmm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.neyrojas.pmm.Constantes.Constantes;
import com.example.neyrojas.pmm.Modelos.Pregunta;
import com.example.neyrojas.pmm.Logica.testTeorico;
import com.example.neyrojas.pmm.Modelos.PreguntaTeorica;
import com.example.neyrojas.pmm.Modelos.Resultado;
import com.example.neyrojas.pmm.Modelos.Usuario;
import com.example.neyrojas.pmm.ParseJson.ConexionAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PreguntasTeoricas extends Activity {


    testTeorico test;
    PreguntaTeorica pregunta;
    String idSubSeccion;
    String textoSubseccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_teoricas);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        test = testTeorico.getInstance();
        pregunta = test.SiguintePregunta();
        if (pregunta != null) {
            ((TextView) findViewById(R.id.lbl_numeroPregunta)).setText((test.preguntaActual+1)+"");
            ((TextView) findViewById(R.id.lbl_pregunta)).setText(pregunta.pregunta);
            ((Button) findViewById(R.id.btn_respuesta1)).setText(pregunta.respuestas[0].respuesta);
            ((Button) findViewById(R.id.btn_respuesta2)).setText(pregunta.respuestas[1].respuesta);
            ((Button) findViewById(R.id.btn_respuesta3)).setText(pregunta.respuestas[2].respuesta);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
        } else {
            Intent intent = new Intent(getBaseContext(), ResultadoDeTest.class);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preguntas_teoricas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void respuesta1(View view){
        if(test.revisarRespuesta(0)){
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
        }else if(test.respuestaCorrecta()==2){
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
        }else{
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
        }
        cargarSiguientePregunta();
    }

    public void respuesta2(View view){
        if(test.revisarRespuesta(1)){
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
        }else if(test.respuestaCorrecta()==1){
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
        }else{
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
        }
        cargarSiguientePregunta();
    }

    public void respuesta3(View view){
        if(test.revisarRespuesta(2)){
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
        }else if(test.respuestaCorrecta()==1){
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
        }else{
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
        }
        cargarSiguientePregunta();
    }


    public void cargarSiguientePregunta() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();


        // Setting OK Button
        alertDialog1.setButton("Siguiente", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                pregunta = test.SiguintePregunta();
                if (pregunta != null) {
                    //test.guardarResultadosTestTeorico();
                    ((TextView) findViewById(R.id.lbl_numeroPregunta)).setText((test.preguntaActual+1)+"");
                    ((TextView) findViewById(R.id.lbl_pregunta)).setText(pregunta.pregunta);
                    ((Button) findViewById(R.id.btn_respuesta1)).setText(pregunta.respuestas[0].respuesta);
                    ((Button) findViewById(R.id.btn_respuesta2)).setText(pregunta.respuestas[1].respuesta);
                    ((Button) findViewById(R.id.btn_respuesta3)).setText(pregunta.respuestas[2].respuesta);
                    ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
                    ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
                    ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
                } else {
                    Intent intent = new Intent(getBaseContext(), ResultadoDeTest.class);
                    finish();
                    intent.putExtra("TipoTest", Constantes.tipoTeorico);
                    startActivity(intent);
                }
            }
        });
        alertDialog1.show();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            //Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

            // Setting Dialog Title
            alertDialog2.setTitle(R.string.advertencia);

            // Setting Dialog Message
            alertDialog2.setMessage(R.string.mensajeConfirmacion);

            // Setting Icon to Dialog
            alertDialog2.setIcon(R.drawable.advertencia);

            // Setting Positive "Yes" Btn
            alertDialog2.setPositiveButton(R.string.si,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            testTeorico test = testTeorico.getInstance();
                            test.reiniciarTestTeorico();
                            finish();
                        }
                    });
            // Setting Negative "NO" Btn
            alertDialog2.setNegativeButton(R.string.no,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            // Showing Alert Dialog
            alertDialog2.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void consultarManual(View view){
        new GetManual().execute();

        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog2.setTitle(idSubSeccion);

        // Setting Dialog Message
        alertDialog2.setMessage(textoSubseccion);

        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }

    private class GetManual extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(PreguntasTeoricas.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ConexionAPI sh = new ConexionAPI();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constantes.URLSubSeccion+"?pk_subseccion="+ pregunta.idSubseccion, ConexionAPI.GET);

            //Log.d("Response: ", "> " + jsonStr);
            JSONArray lugaresJSON = null;

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    lugaresJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < lugaresJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = lugaresJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        idSubSeccion = object.getString("nombre");
                        textoSubseccion = object.getString("descripcion");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

        }
    }
}
