package com.example.gustavovargas.movilv20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Logica.testTeorico;


public class ResultadoDeTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_de_test);
    }

    @Override
    public void onStart(){
        super.onStart();
        testTeorico test = testTeorico.getInstance();
        ((TextView) findViewById(R.id.lbl_correctas)).setText(""+test.preguntasCorrectas);
        ((TextView) findViewById(R.id.lbl_incorrectas)).setText(""+(Constantes.maximoNumPregTestTeo-test.preguntasCorrectas));
        if(test.preguntasCorrectas>Constantes.numMiniPregNotaAprov){
            ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.aprobado);
            ((ImageView) findViewById(R.id.img_resultado)).setImageResource(R.drawable.felicidades);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(Constantes.tiempoVibracionAprovado, -1);
        }else{
            ((TextView) findViewById(R.id.lbl_resultado)).setText(R.string.reprobado);
            ((ImageView) findViewById(R.id.img_resultado)).setImageResource(R.drawable.reprovastes);
        }
    }


    public void terminarTestTeorico(View view){
        testTeorico test = testTeorico.getInstance();
        test.reiniciarTestTeorico();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            testTeorico test = testTeorico.getInstance();
            test.reiniciarTestTeorico();
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
