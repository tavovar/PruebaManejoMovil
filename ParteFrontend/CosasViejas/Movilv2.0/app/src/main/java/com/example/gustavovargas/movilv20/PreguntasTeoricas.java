package com.example.gustavovargas.movilv20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Pregunta;
import com.example.gustavovargas.movilv20.Logica.testTeorico;


public class PreguntasTeoricas extends Activity {


    testTeorico test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_teoricas);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        test = testTeorico.getInstance();
        Pregunta pregunta = test.SiguintePregunta();
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
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
        }else{
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.estilos);
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
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.estilos);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.rojo);
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

    public void respuesta3(View view){
        if(test.revisarRespuesta(2)){
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
        }else if(test.respuestaCorrecta()==1){
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 300 milliseconds
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundResource(R.drawable.rojo);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundResource(R.drawable.estilos);
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


    public void cargarSiguientePregunta() {
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();


        // Setting OK Button
        alertDialog1.setButton("Siguiente", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Pregunta pregunta = test.SiguintePregunta();
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
}
