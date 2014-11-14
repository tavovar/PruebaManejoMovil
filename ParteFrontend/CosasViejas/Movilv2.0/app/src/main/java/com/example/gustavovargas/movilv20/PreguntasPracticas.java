package com.example.gustavovargas.movilv20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gustavovargas.movilv20.Logica.testTeorico;


public class PreguntasPracticas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_practicas);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preguntas_practicas, menu);
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
                            //testTeorico test = testTeorico.getInstance();
                            //test.reiniciarTestTeorico();
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
