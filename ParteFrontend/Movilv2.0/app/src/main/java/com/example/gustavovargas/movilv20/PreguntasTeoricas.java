package com.example.gustavovargas.movilv20;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
        test = new testTeorico();
        cargarSiguientePregunta();
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
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonCorrecto);
        }else if(test.respuestaCorrecta()==2){
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }else{
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }
        cargarSiguientePregunta();
    }

    public void respuesta2(View view){
        if(test.revisarRespuesta(1)){
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonCorrecto);
        }else if(test.respuestaCorrecta()==1){
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }else{
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }
        try {
            Thread.sleep(500);
            cargarSiguientePregunta();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void respuesta3(View view){
        if(test.revisarRespuesta(2)){
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonCorrecto);
        }else if(test.respuestaCorrecta()==1){
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }else{
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonCorrecto);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonIncorrecto);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonIncorrecto);
        }
        cargarSiguientePregunta();
    }


    public void cargarSiguientePregunta() {
        Pregunta pregunta = test.SiguintePregunta();
        if (pregunta != null) {
            ((TextView) findViewById(R.id.lbl_numeroPregunta)).setText((test.preguntaActual+1)+"");
            ((TextView) findViewById(R.id.lbl_pregunta)).setText(pregunta.pregunta);
            ((Button) findViewById(R.id.btn_respuesta1)).setText(pregunta.respuestas[0].respuesta);
            ((Button) findViewById(R.id.btn_respuesta2)).setText(pregunta.respuestas[1].respuesta);
            ((Button) findViewById(R.id.btn_respuesta3)).setText(pregunta.respuestas[2].respuesta);
            ((Button) findViewById(R.id.btn_respuesta1)).setBackgroundColor(Constantes.colorBotonSeleccion);
            ((Button) findViewById(R.id.btn_respuesta3)).setBackgroundColor(Constantes.colorBotonSeleccion);
            ((Button) findViewById(R.id.btn_respuesta2)).setBackgroundColor(Constantes.colorBotonSeleccion);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Test terminado", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
