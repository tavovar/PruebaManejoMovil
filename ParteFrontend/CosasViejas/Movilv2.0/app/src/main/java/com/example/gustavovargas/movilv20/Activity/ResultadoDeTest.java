package com.example.gustavovargas.movilv20.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Logica.testPractico;
import com.example.gustavovargas.movilv20.Logica.testTeorico;
import com.example.gustavovargas.movilv20.R;


public class ResultadoDeTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_de_test);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onStart(){
        super.onStart();
        int tipo = getIntent().getIntExtra("TipoTest",0);
        if(tipo == Constantes.tipoTeorico) {
            testTeorico test = testTeorico.getInstance();
            ((TextView) findViewById(R.id.lbl_correctas)).setText("" + test.preguntasCorrectas);
            ((TextView) findViewById(R.id.lbl_incorrectas)).setText("" + (Constantes.maximoNumPregTestTeo - test.preguntasCorrectas));
            float resultado = (test.preguntasCorrectas * 1.0f) / (Constantes.maximoNumPregTestTeo * 1.0f) * 100;
            Log.v("Nota", resultado + "");
            test.guardarResultadosTestTeorico();
            ((TextView) findViewById(R.id.lbl_NotaResultado)).setText((String.format("%.2f", resultado)) + "%");
            if (test.preguntasCorrectas > Constantes.numMiniPregNotaAprov) {
                ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.aprobado);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(Constantes.tiempoVibracionAprovado, -1);
            } else {
                ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.reprobado);
            }
        }else{
            testPractico test = testPractico.getInstance();
            ((TextView) findViewById(R.id.lbl_correctas)).setText("" + test.preguntasCorrectas);
            ((TextView) findViewById(R.id.lbl_incorrectas)).setText("" + (Constantes.maximoNumPregTestPractico - test.preguntasCorrectas));
            float resultado = (test.preguntasCorrectas * 1.0f) / (Constantes.maximoNumPregTestPractico * 1.0f) * 100;
            Log.v("Nota", resultado + "");
            test.guardarResultadosTestPractico();
            ((TextView) findViewById(R.id.lbl_NotaResultado)).setText((String.format("%.2f", resultado)) + "%");
            if (test.preguntasCorrectas > Constantes.maximoNumPregTestPractico) {
                ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.aprobado);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(Constantes.tiempoVibracionAprovado, -1);
            } else {
                ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.reprobado);
            }
        }
    }


    public void terminarTestTeorico(View view){
        testTeorico test = testTeorico.getInstance();
        test.reiniciarTestTeorico();
        testPractico test1 = testPractico.getInstance();
        test1.reiniciarTestTeorico();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            testTeorico test = testTeorico.getInstance();
            test.reiniciarTestTeorico();
            testPractico test1 = testPractico.getInstance();
            test1.reiniciarTestTeorico();
            finish();
            // Esto es lo que hace mi botón al pulsar ir a atrás
            //Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultado_de_test, menu);
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
}
