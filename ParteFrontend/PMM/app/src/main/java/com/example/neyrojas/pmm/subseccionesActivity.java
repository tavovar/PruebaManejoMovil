package com.example.neyrojas.pmm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.neyrojas.pmm.Adapters.subseccionAdapter;
import com.example.neyrojas.pmm.Modelos.SubseccionesManual;
import com.example.neyrojas.pmm.ParseJson.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class subseccionesActivity extends Activity {

    //Indice del manual
    int fkseccion;

    //Arrays para secciones y su adaptador
    JSONArray subseccionesJSON = null;
    ArrayList<SubseccionesManual> subseccionesList;
    subseccionAdapter adapterSubseccion;

    //URL del JSON
    private static String urlSubsecciones = "http://www.pruebamanejomovil.com:8080/app/subsecciones?fk_seccion=";
    private static String urlGetSubseccion = "";

    //Nombres de nodos de JSON
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_TEXTO = "descripcion";
    private static final String TAG_PK_SUBSECCION = "pk_subseccion";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsecciones);

        fkseccion = getIntent().getIntExtra("Fkseccion", 2);
        urlGetSubseccion = urlSubsecciones + fkseccion;

        //Arreglo que almacena los lugares
        subseccionesList = new ArrayList<SubseccionesManual>();
        //Listview y adaptador
        listView = (ListView)findViewById(R.id.listViewSubseccion);
        adapterSubseccion = new subseccionAdapter(getApplicationContext(), R.layout.vista_subsecciones, subseccionesList);
        listView.setAdapter(adapterSubseccion);

        // Calling async task to get json
        new GetSubsecciones().execute();
    }

    /**
     * SUBSECCIONES - Async task class to get JSON by making HTTP call
     * */
    private class GetSubsecciones extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(subseccionesActivity.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlGetSubseccion, ServiceHandler.GET);
            Log.d("Request: ", "> " + urlGetSubseccion);
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    subseccionesJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < subseccionesJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = subseccionesJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        SubseccionesManual subseccion = new SubseccionesManual();
                        subseccion.setNombre(object.getString(TAG_NOMBRE));
                        subseccion.setTexto(object.getString(TAG_TEXTO));
                        subseccion.setSubseccion(object.getInt(TAG_PK_SUBSECCION));
                        //Agrega el objeto lugar al ArrayList<SeccionesManual>
                        subseccionesList.add(subseccion);
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
            listView.setAdapter(adapterSubseccion);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subsecciones, menu);
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
