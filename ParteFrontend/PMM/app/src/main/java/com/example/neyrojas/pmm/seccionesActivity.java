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
import android.widget.TabHost;

import com.example.neyrojas.pmm.Adapters.seccionAdapter;
import com.example.neyrojas.pmm.Adapters.seccionAdapter2;
import com.example.neyrojas.pmm.Modelos.SeccionesManual;
import com.example.neyrojas.pmm.ParseJson.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class seccionesActivity extends Activity {

    //Indice del manual
    int fkmanual;
    TabHost th;

    //Arrays para secciones y su adaptador
    JSONArray seccionesJSON = null;
    ArrayList<SeccionesManual> seccionesList;
    seccionAdapter adapterSeccionPorNombre;
    seccionAdapter2 adapterSeccionPorIndice;

    //URL del JSON
    private static String urlSecciones = "http://www.pruebamanejomovil.com:8080/app/secciones?fk_manual=";
    private static String urlGetSeccion = "";

    //Nombres de nodos de JSON
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_INDICE = "indice";
    private static final String TAG_PK_SECCION = "pk_seccion";

    ListView listViewInd;
    ListView listViewNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones);

        fkmanual = getIntent().getIntExtra("Pkmanual", 1);
        urlGetSeccion = urlSecciones + fkmanual;

        //Arreglo que almacena los lugares
        seccionesList = new ArrayList<SeccionesManual>();

        //Tab
        th = (TabHost)findViewById(R.id.thSecciones);
        th.setup();
        TabHost.TabSpec ts1 = th.newTabSpec("Por Indice");
        ts1.setIndicator("Por Indice");
        ts1.setContent(R.id.tabIndice);
        th.addTab(ts1);

        th.setup();
        TabHost.TabSpec ts2 = th.newTabSpec("Por Nombre");
        ts2.setIndicator("Por Nombre");
        ts2.setContent(R.id.tabNombre);
        th.addTab(ts2);

        //Listview y adaptador
        listViewInd = (ListView)findViewById(R.id.listViewSeccion);
        listViewNom = (ListView)findViewById(R.id.listViewNombre);
        adapterSeccionPorIndice = new seccionAdapter2(getApplicationContext(), R.layout.lista_manuales, seccionesList);
        adapterSeccionPorNombre = new seccionAdapter(getApplicationContext(), R.layout.lista_manuales, seccionesList);
        listViewInd.setAdapter(adapterSeccionPorIndice);
        listViewNom.setAdapter(adapterSeccionPorNombre);

        //Botones
        /*Button btnNombre = (Button)findViewById(R.id.btnNombre);
        btnNombre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listViewInd.setAdapter(adapterSeccionPorNombre);
            }
        });

        Button btnIndice = (Button)findViewById(R.id.btnIndice);
        btnIndice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listViewInd.setAdapter(adapterSeccionPorIndice);
            }
        });*/

        //Funcion para cuando se da click en un elemento del listview
        listViewInd.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Llama a las secciones del manual
                callSubsecciones(view, seccionesList.get(position).getSeccion());
                //Toast.makeText(getApplicationContext(),"Manual seleccionado: "+
                //        manualesList.get(position).getManual(), Toast.LENGTH_SHORT).show();
            }
        });
        //Funcion para cuando se da click en un elemento del listview
        listViewNom.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Llama a las secciones del manual
                callSubsecciones(view, seccionesList.get(position).getSeccion());
                //Toast.makeText(getApplicationContext(),"Manual seleccionado: "+
                //        manualesList.get(position).getManual(), Toast.LENGTH_SHORT).show();
            }
        });

        // Calling async task to get json
        new GetSecciones().execute();
    }

    /**
     * SECCIONES - Async task class to get JSON by making HTTP call
     * */
    private class GetSecciones extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(seccionesActivity.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlGetSeccion, ServiceHandler.GET);
            Log.d("Request: ", "> " + urlGetSeccion);
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    seccionesJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < seccionesJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = seccionesJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        SeccionesManual seccion = new SeccionesManual();
                        seccion.setNombre(object.getString(TAG_NOMBRE));
                        seccion.setSeccion(object.getInt(TAG_PK_SECCION));
                        seccion.setIndice(object.getInt(TAG_INDICE));
                        //Agrega el objeto lugar al ArrayList<SeccionesManual>
                        seccionesList.add(seccion);
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
            listViewInd.setAdapter(adapterSeccionPorIndice);
            listViewNom.setAdapter(adapterSeccionPorNombre);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.secciones, menu);
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

    public void callSubsecciones(View view, int pFkseccion) {
        Intent intent = new Intent(this, subseccionesActivity.class);
        intent.putExtra("Fkseccion", pFkseccion);
        startActivity(intent);
    }

}
