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
import android.widget.ListView;
import android.widget.Spinner;

import com.example.neyrojas.pmm.Adapters.manualAdapter;
import com.example.neyrojas.pmm.Adapters.paisAdapter;
import com.example.neyrojas.pmm.Modelos.Manual;
import com.example.neyrojas.pmm.Modelos.Pais;
import com.example.neyrojas.pmm.ParseJson.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class manualActivity extends Activity {

    //Arrays para paises y su adaptador
    JSONArray paisesJSON = null;
    ArrayList<Pais> paisesList;
    paisAdapter adapterPais;

    //Arrays para manuales y su adaptador
    JSONArray manualesJSON = null;
    ArrayList<Manual> manualesList;
    manualAdapter adapterManual;

    //URL del JSON
    private static String urlPaises = "http://www.pruebamanejomovil.com:8080/app/paises";
    private static String urlManual = "http://www.pruebamanejomovil.com:8080/app/manuales?fk_pais=";
    private static String urlGetManual = "";

    //Nombres de nodos de JSON
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_PK_MANUAL = "pk_manual";
    private static final String TAG_PK_PAIS = "pk_pais";

    ListView listView;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        // PARA PAISES
        //Arreglo que almacena los paises
        paisesList = new ArrayList<Pais>();
        //Spinner y adaptador
        spinner1  = (Spinner)findViewById(R.id.spinnerPaises);
        adapterPais = new paisAdapter(getApplicationContext(), R.layout.lista_paises, paisesList);
        spinner1.setAdapter(adapterPais);

        //Funcion para cuando se da click en un elemento del spinner PAIS
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                urlGetManual =  urlManual + paisesList.get(pos).getPais();
                manualesList.clear();
                new GetManuales().execute();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {
                // TODO Auto-generated method stub
                // Do nothing.
            }
        });

        // PARA MANUALES
        //Arreglo que almacena los lugares
        manualesList = new ArrayList<Manual>();
        //Listview y adaptador
        listView = (ListView)findViewById(R.id.listViewManual);
        adapterManual = new manualAdapter(getApplicationContext(), R.layout.lista_manuales, manualesList);
        listView.setAdapter(adapterManual);

        //Funcion para cuando se da click en un elemento del listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Llama a las secciones del manual
                callSecciones(view, manualesList.get(position).getManual());
                //Toast.makeText(getApplicationContext(),"Manual seleccionado: "+
                //        manualesList.get(position).getManual(), Toast.LENGTH_SHORT).show();
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
            pDialog2 = new ProgressDialog(manualActivity.this);
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
            spinner1.setAdapter(adapterPais);
        }

    }

    /**
     * MANUALES - Async task class to get JSON by making HTTP call
     * */
    private class GetManuales extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(manualActivity.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlGetManual, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    manualesJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < manualesJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = manualesJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        Manual manual = new Manual();
                        manual.setNombre(object.getString(TAG_NOMBRE));
                        manual.setManual(object.getInt(TAG_PK_MANUAL));
                        //Agrega el objeto lugar al ArrayList<Manual>
                        manualesList.add(manual);
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
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             */
            listView.setAdapter(adapterManual);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manual, menu);
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

    public void callSecciones(View view, int pPkmanual) {
        Intent intent = new Intent(this, seccionesActivity.class);
        intent.putExtra("Pkmanual", pPkmanual);
        startActivity(intent);
    }
}
