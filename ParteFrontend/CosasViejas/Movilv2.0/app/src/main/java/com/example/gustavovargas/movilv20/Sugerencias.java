package com.example.gustavovargas.movilv20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Usuario;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;

import org.json.JSONException;


public class Sugerencias extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugerencias);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sugerencias, menu);
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


    public void enviarSugerencia(View view) throws JSONException {
        String correo = ((EditText) findViewById(R.id.txt_email_sugerencia)).getText().toString();
        String sugerencia = ((EditText) findViewById(R.id.txt_sugerencia)).getText().toString();
        Switch switchRespuesta = (Switch) findViewById(R.id.switch_respuesta);
        if (sugerencia.length() > 10 && correo.length() > 8) {
            Log.v("ENVIAR SUGERENCIA", correo + "-" + sugerencia);
            Log.v("SWITCH", switchRespuesta.isChecked() + "");
            ConexionAPI test = new ConexionAPI();
            if (switchRespuesta.isChecked()) {
                test.SugerenciaTestJson(Usuario.getInstance().id, Constantes.respuestaSugerenciaSi, sugerencia, correo);
            } else {
                test.SugerenciaTestJson(Usuario.getInstance().id, Constantes.respuestaSugerenciaNo, sugerencia, correo);
            }
            ((EditText) findViewById(R.id.txt_email_sugerencia)).setText("");
            ((EditText) findViewById(R.id.txt_sugerencia)).setText("");
        } else {
            AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();

            alertDialog1.setTitle(R.string.restriccionessugerencia);
            // Setting OK Button
            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((EditText) findViewById(R.id.txt_email_sugerencia)).setText("");
                    ((EditText) findViewById(R.id.txt_sugerencia)).setText("");
                }
            });
            alertDialog1.show();
        }
    }
}
