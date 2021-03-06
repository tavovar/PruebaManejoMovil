package com.example.gustavovargas.movilv20.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gustavovargas.movilv20.R;


public class menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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


    public void callPruebaTeorica(View view) {
        //Log.d("Lab1", "Estoy en el callMaps()");
        Intent intent = new Intent(getApplicationContext(), Preguntas.class);
        startActivity(intent);
    }

    public void callSugerencias(View view) {
        //Log.d("Lab1", "Estoy en el callMaps()");
        Intent intent = new Intent(getApplicationContext(), Sugerencias.class);
        startActivity(intent);
    }

    public void callResultados(View view) {
        Intent intent = new Intent(getApplicationContext(), estadisticas.class);
        startActivity(intent);
    }

    public void callPruebaPractica(View view) {
        Intent intent = new Intent(getApplicationContext(), preguntasInstruciones.class);
        startActivity(intent);
    }

}
