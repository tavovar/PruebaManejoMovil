package com.example.gustavovargas.movilv20.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Logica.testPractico;
import com.example.gustavovargas.movilv20.Logica.testTeorico;
import com.example.gustavovargas.movilv20.Modelos.PreguntaPractica;
import com.example.gustavovargas.movilv20.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class PreguntasPracticas extends Activity implements SensorEventListener {


    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    int velocidad;
    int interaccion;
    testPractico test;
    CountDownTimer timer;
    private Bitmap loadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_preguntas_practicas);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        velocidad=50;
        interaccion=1;
        ((TextView) findViewById(R.id.lbl_velocidad)).setText(velocidad + "Kmh");
        test = testPractico.getInstance();
        PreguntaPractica pregunta = test.SiguintePregunta();
        ((TextView) findViewById(R.id.lbl_numPreguntaPractica)).setText(test.preguntaActual + "");
        cargarImagen(Constantes.URLImagenes+pregunta.pathImagen);
        timerTiempoRespuesta();
    }


    private void cargarImagen(String url){
        URL imageUrl = null;
        try {
            imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
            ((ImageView) findViewById(R.id.img_senalTestPractico)).setImageBitmap(loadedImage);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
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
                            timer.cancel();
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

    public void nuevaPregunta(){
        // Creating alert Dialog with one Button

        AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();


        // Setting OK Button
        alertDialog1.setButton("Siguiente", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                test = testPractico.getInstance();
                PreguntaPractica pregunta = test.SiguintePregunta();
                if (pregunta != null) {
                    ((TextView) findViewById(R.id.lbl_numPreguntaPractica)).setText(test.preguntaActual + "");
                    cargarImagen(Constantes.URLImagenes+pregunta.pathImagen);
                    timerTiempoRespuesta();
                } else {
                    Intent intent = new Intent(getBaseContext(), ResultadoDeTest.class);
                    intent.putExtra("TipoTest",Constantes.tipoPractico);
                    finish();
                    startActivity(intent);
                }
            }
        });
        alertDialog1.show();
    }

    private float mSensorX;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mSensorX = event.values[1];
            if(mSensorX<-2){
                interaccion=0;
                ((ImageView) findViewById(R.id.img_derecha)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.img_izquierda)).setVisibility(View.VISIBLE);
            }else if(mSensorX>2){
                interaccion=2;
                ((ImageView) findViewById(R.id.img_izquierda)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.img_derecha)).setVisibility(View.VISIBLE);
            }else{
                interaccion=1;
                ((ImageView) findViewById(R.id.img_derecha)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.img_izquierda)).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void aumentarVeocidad(View view) {
        if(velocidad< Constantes.velocidadMaxima){
            velocidad++;
        }
        ((TextView) findViewById(R.id.lbl_velocidad)).setText(velocidad+"Kmh");
    }

    public void bajarVelocidad(View view) {
        if (velocidad > Constantes.velocidadMinima) {
                velocidad--;
        }
        ((TextView) findViewById(R.id.lbl_velocidad)).setText(velocidad + "Kmh");
    }

    public void timerTiempoRespuesta() {
        timer = new CountDownTimer(Constantes.tiempoLimite*1000, Constantes.refrecamientoDelTiempo*1000) {
            int tiempo = Constantes.tiempoLimite;
            @Override public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.lbl_tiempo)).setText(""+tiempo);
                tiempo--;
            } @Override public void onFinish() {
                ((TextView) findViewById(R.id.lbl_tiempo)).setText("0");
                siguientePregunta();
            } }.start();
    }

    public void siguientePregunta(){
        //timer.cancel();
        test = testPractico.getInstance();
        if(test.revisarRespuesta(interaccion,velocidad)){
            Toast.makeText(this, R.string.correcto, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.incorrecto, Toast.LENGTH_SHORT).show();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(Constantes.tiempoVibracionIncorrecto);
        }
        nuevaPregunta();
    }
}
