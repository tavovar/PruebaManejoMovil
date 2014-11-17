package com.example.neyrojas.pmm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.neyrojas.pmm.Adapters.paisAdapter;
import com.example.neyrojas.pmm.Modelos.Pais;
import com.example.neyrojas.pmm.Modelos.Usuario;
import com.example.neyrojas.pmm.ParseJson.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class menu extends Activity {

    //Arrays para paises y su adaptador
    JSONArray paisesJSON = null;
    ArrayList<Pais> paisesList;
    paisAdapter adapterPais;

    //URL del JSON
    private static String urlPaises = "http://www.pruebamanejomovil.com:8080/app/paises";
    //Nombres de nodos de JSON
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_PK_PAIS = "pk_pais";
    Spinner spinnerPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // PARA PAISES
        //Arreglo que almacena los paises
        paisesList = new ArrayList<Pais>();
        //Spinner y adaptador
        spinnerPais  = (Spinner)findViewById(R.id.spinnerPaises1);
        adapterPais = new paisAdapter(getApplicationContext(), R.layout.lista_paises, paisesList);
        spinnerPais.setAdapter(adapterPais);

        //Funcion para cuando se da click en un elemento del spinner PAIS
        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                //Accion a realizar cuando se selecciona un pais
                Usuario.getInstance().pais=paisesList.get(pos).getPais(); //Toma el pk_pais del pais seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView parent) {
                // TODO Auto-generated method stub
                // Do nothing.
            }
        });

        // Calling async task to get json
        new GetPaises().execute();
    }

    /**
     * PAISES - Async task class to get JSON by making HTTP call
     * */
    private class GetPaises extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog2;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog2 = new ProgressDialog(menu.this);
            pDialog2.setMessage("Cargando, por favor espere unos segundos...");
            pDialog2.setCancelable(false);
            pDialog2.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlPaises, ServiceHandler.GET);

            Log.d("Request: ", "> " + urlPaises);
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    paisesJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < paisesJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = paisesJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        Pais pais = new Pais();
                        pais.setNombre(object.getString(TAG_NOMBRE));
                        pais.setPais(object.getInt(TAG_PK_PAIS));
                        //Agrega el objeto lugar al ArrayList<Pais>
                        paisesList.add(pais);
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
            if (pDialog2.isShowing())
                pDialog2.dismiss();
            /**
             * Updating parsed JSON data into ListView
             */
            spinnerPais.setAdapter(adapterPais);
        }

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

    //Llama a la parte de Prueba Teorica
    public void callPruebaTeorica(View view) {
        Intent intent = new Intent(getApplicationContext(), Preguntas.class);
        startActivity(intent);
    }

    //Llama a la parte de Sucursales disponibles
    public void callSucursales(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //Llama a la parte de Consultar un manual
    public void callManual(View view) {
        Intent intent = new Intent(getApplicationContext(), manualActivity.class);
        startActivity(intent);
    }

    //Llama a la parte de Prueba Practica
    public void callPruebaPractica(View view) {
        Intent intent = new Intent(getApplicationContext(), preguntasInstruciones.class);
        startActivity(intent);
    }

    //Llama a la parte de enviar Sugerencia
    public void callSugerencia(View view) {
        Intent intent = new Intent(getApplicationContext(), Sugerencias.class);
        startActivity(intent);
    }

    //Llama a la parte de ver Resultados
    public void callResultados(View view) {
        Intent intent = new Intent(getApplicationContext(), estadisticas.class);
        startActivity(intent);
    }
}
