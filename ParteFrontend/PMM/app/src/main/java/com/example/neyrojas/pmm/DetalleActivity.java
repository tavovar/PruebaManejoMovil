package com.example.neyrojas.pmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends Activity {

    private double Lat;
    private double Lng;
    private String Nom;
    private String Tel;
    private String Des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Nom = getIntent().getStringExtra("Nombre");
        Tel = getIntent().getStringExtra("Telefono");
        Des = getIntent().getStringExtra("Lugar");
        Lat = getIntent().getDoubleExtra("Lat", 10.000000);
        Lng = getIntent().getDoubleExtra("Lng", -84.000000);
        TextView titulo = (TextView) findViewById(R.id.nombreLugar);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        titulo.setText(Nom);
        descripcion.setText("Direccion: " + Des + ", contacte al: " + Tel);

        //Toast.makeText(getApplicationContext(),"Lat: " +
        //        Lat + " y Long: " + Lng, Toast.LENGTH_LONG).show();

        Button btnMapa = (Button)findViewById(R.id.btnMap);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callMap(v, Lat, Lng);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detalle, menu);
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

    public void callMap(View view, double pLat, double pLng) {
        Intent intent = new Intent(this, lugarActivity.class);
        intent.putExtra("Lat", pLat);
        intent.putExtra("Lng", pLng);
        startActivity(intent);
    }
}