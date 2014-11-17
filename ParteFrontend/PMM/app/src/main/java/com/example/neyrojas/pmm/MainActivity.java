package com.example.neyrojas.pmm;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.neyrojas.pmm.Adapters.lugarAdapter;
import com.example.neyrojas.pmm.Modelos.Lugares;
import com.example.neyrojas.pmm.ParseJson.ServiceHandler;

public class MainActivity extends Activity {

    //Arrays para lugares y su adaptador
    JSONArray lugaresJSON = null;
    ArrayList<Lugares> lugaresList;
    ArrayList<Lugares> busquedaList;
    lugarAdapter adapter;
    ArrayList<String> lLUGARES;
    lugarAdapter busquedaAdapter;

    //EditText para buscar
    AutoCompleteTextView edtBusqueda;

    //URL del JSON de lugares
    private static String url = "http://www.pruebamanejomovil.com:8080/app/lugares";

    //Nombres de nodos de JSON
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_LATITUD = "latitud";
    private static final String TAG_LONGITUD = "longitud";
    private static final String TAG_TELEFONO = "telefono";
    private static final String TAG_LUGAR = "lugar";
    private static final String TAG_PK_SUCURSAL = "pk_sucursal";
    private static final String TAG_FK_PAIS = "fk_pais";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Arreglo que almacena los lugares
        lugaresList = new ArrayList<Lugares>();
        busquedaList = new ArrayList<Lugares>();
        //Listview y adaptador
        listView = (ListView)findViewById(R.id.listView);
        adapter = new lugarAdapter(getApplicationContext(), R.layout.lista_lugares, lugaresList);
        listView.setAdapter(adapter);

        //Funcion para cuando se da click en un elemento del listview
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;

                //Llama al metodo para ver el detalle segun el item seleccionado
                callMaps(view, lugaresList.get(itemPosition).getLatitud(), lugaresList.get(itemPosition).getLongitud(),
                        lugaresList.get(itemPosition).getNombre(), lugaresList.get(itemPosition).getTelefono(),
                        lugaresList.get(itemPosition).getLugar());
            }
        });
        // Calling async task to get json
        new GetLugares().execute();

        lLUGARES = new ArrayList<String>();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lLUGARES);
        //adapterSearch  = new busquedaAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, lugaresList);
        edtBusqueda = (AutoCompleteTextView)findViewById(R.id.buscarText);
        edtBusqueda.setAdapter(adapter2);
        ImageButton btnMapa = (ImageButton)findViewById(R.id.btnSearch);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lugarSearch();
            }
        });
    }

    /**
     * Async task class to get JSON by making HTTP call
     * */
    private class GetLugares extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    lugaresJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < lugaresJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = lugaresJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        Lugares lugar = new Lugares();
                        lugar.setNombre(object.getString(TAG_NOMBRE));
                        lugar.setLatitud(object.getDouble(TAG_LATITUD));
                        lugar.setLongitud(object.getDouble(TAG_LONGITUD));
                        lugar.setTelefono(object.getString(TAG_TELEFONO));
                        lugar.setLugar(object.getString(TAG_LUGAR));
                        lugar.setSucursal(object.getInt(TAG_PK_SUCURSAL));
                        lugar.setPais(object.getInt(TAG_FK_PAIS));
                        //Agrega el objeto lugar al ArrayList<Lugares>
                        lugaresList.add(lugar);
                        lLUGARES.add(lugar.getNombre());
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
            listView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void callMaps(View view, double pLat, double pLng, String pName, String pPhone, String pPlace) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("Nombre", pName);
        intent.putExtra("Telefono", pPhone);
        intent.putExtra("Lugar", pPlace);
        intent.putExtra("Lat", pLat);
        intent.putExtra("Lng", pLng);
        startActivity(intent);
    }

    public void lugarSearch() {
        if (busquedaList.size() > 0){
            busquedaList.clear();
        }
        String pTexto = edtBusqueda.getText().toString();
        if (pTexto.equals(null)){
            //No hace nada y deja las cosas como estan
        }else{
            for (int i = 0; i < lugaresList.size(); i++){
                if (lugaresList.get(i).getNombre().contains(pTexto)){
                    busquedaList.add(lugaresList.get(i));
                }//else if (lugaresList.get(i).getLugar().contains(pTexto)){
                    //busquedaList.add(lugaresList.get(i));
                //}
            }
            if (busquedaList.size() > 0){
                busquedaAdapter = new lugarAdapter(getApplicationContext(), R.layout.lista_lugares, busquedaList);
                listView.setAdapter(busquedaAdapter);
            }else{
                Toast.makeText(getApplicationContext(), "No se encontraron coincidencias", Toast.LENGTH_LONG).show();
            }
        }
    }
}
