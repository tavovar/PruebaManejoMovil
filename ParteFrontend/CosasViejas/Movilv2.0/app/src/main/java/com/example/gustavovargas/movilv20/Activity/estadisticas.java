package com.example.gustavovargas.movilv20.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.gustavovargas.movilv20.Adater.ResultadoAdapter;
import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Resultado;
import com.example.gustavovargas.movilv20.Modelos.Usuario;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;
import com.example.gustavovargas.movilv20.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class estadisticas extends Activity {

    TabHost th;
    ListView listView1;
    ListView listView2;
    ListView listView3;
    ArrayList<Resultado> resultadoList1;
    ArrayList<Resultado> resultadoList2;
    ArrayList<Resultado> resultadoList3;
    ResultadoAdapter adapter1;
    ResultadoAdapter adapter2;
    ResultadoAdapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        resultadoList1 = new ArrayList<Resultado>();
        resultadoList2 = new ArrayList<Resultado>();
        resultadoList3 = new ArrayList<Resultado>();

        th = (TabHost)findViewById(R.id.thEstadisticas);

        th.setup();
        TabSpec ts1 = th.newTabSpec("Todos");
        ts1.setIndicator("Todos");
        ts1.setContent(R.id.tab1);
        th.addTab(ts1);

        th.setup();
        TabSpec ts2 = th.newTabSpec("Semana");
        ts2.setIndicator("Semana");
        ts2.setContent(R.id.tab2);
        th.addTab(ts2);

        th.setup();
        TabSpec ts3 = th.newTabSpec("Mes");
        ts3.setIndicator("Mes");
        ts3.setContent(R.id.tab3);
        th.addTab(ts3);

        //Listview y adaptador
        listView1 = (ListView)findViewById(R.id.lv_todasNotas);
        adapter1 = new ResultadoAdapter(getApplicationContext(), R.layout.activity_resultados, resultadoList1);
        listView1.setAdapter(adapter1);
        //new GetLugares().execute();

        listView2 = (ListView)findViewById(R.id.lv_semana);
        adapter2 = new ResultadoAdapter(getApplicationContext(), R.layout.activity_resultados, resultadoList2);
        listView2.setAdapter(adapter2);
        //new GetLugares().execute();

        listView3 = (ListView)findViewById(R.id.lv_mes);
        adapter3 = new ResultadoAdapter(getApplicationContext(), R.layout.activity_resultados, resultadoList3);
        listView3.setAdapter(adapter3);
        new GetLugares().execute();
    }


    private class GetLugares extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(estadisticas.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ConexionAPI sh = new ConexionAPI();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constantes.URLResultados+"?fk_usuario="+Usuario.getInstance().id, ConexionAPI.GET);

            //Log.d("Response: ", "> " + jsonStr);
            JSONArray lugaresJSON = null;

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    lugaresJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < lugaresJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = lugaresJSON.getJSONObject(i);
                        //Crea un lugar segun el objeto en el JSONArray
                        Resultado resultado = new Resultado(object.getInt(Constantes.preguntasCorrectasResultado),object.getString(Constantes.fecha),object.getInt(Constantes.tipoResultado));
                        resultadoList1.add(resultado);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(Constantes.URLResultadosSemanas+"?fk_usuario="+Usuario.getInstance().id, ConexionAPI.GET);
            //Log.d("Response: ", "> " + jsonStr);
            int anterior = -1;
            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    lugaresJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < lugaresJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = lugaresJSON.getJSONObject(i);
                        if(anterior == object.getInt(Constantes.semana)) {
                            //Crea un lugar segun el objeto en el JSONArray
                            anterior = object.getInt(Constantes.semana);
                            Resultado resultado = new Resultado(object.getInt(Constantes.preguntasCorrectasResultado), object.getString(Constantes.fecha), object.getInt(Constantes.tipoResultado));
                            resultadoList2.add(resultado);
                        }else{
                            anterior = object.getInt(Constantes.semana);
                            Resultado resultado1 = new Resultado(anterior, object.getString(Constantes.fecha), 3);
                            resultadoList2.add(resultado1);
                            Resultado resultado = new Resultado(object.getInt(Constantes.preguntasCorrectasResultado), object.getString(Constantes.fecha), object.getInt(Constantes.tipoResultado));
                            resultadoList2.add(resultado);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(Constantes.URLResultadosMeses+"?fk_usuario="+Usuario.getInstance().id, ConexionAPI.GET);

           // Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Carga el JSONArray luego de realizar la peticion HTTP
                    lugaresJSON = new JSONArray(jsonStr);

                    // loop por todos los lugares - Para guardar en la lista de lugares
                    for (int i = 0; i < lugaresJSON.length(); i++) {
                        //Obtiene cada objeto en el JSONArray
                        JSONObject object = lugaresJSON.getJSONObject(i);
                        if(anterior == object.getInt(Constantes.mes)) {
                            //Crea un lugar segun el objeto en el JSONArray
                            anterior = object.getInt(Constantes.mes);
                            Resultado resultado = new Resultado(object.getInt(Constantes.preguntasCorrectasResultado), object.getString(Constantes.fecha), object.getInt(Constantes.tipoResultado));
                            resultadoList3.add(resultado);
                        }else{
                            anterior = object.getInt(Constantes.mes);
                            Resultado resultado1 = new Resultado(anterior, object.getString(Constantes.fecha), 4);
                            resultadoList3.add(resultado1);
                            Resultado resultado = new Resultado(object.getInt(Constantes.preguntasCorrectasResultado), object.getString(Constantes.fecha), object.getInt(Constantes.tipoResultado));
                            resultadoList3.add(resultado);
                        }
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
            listView1.setAdapter(adapter1);
            listView2.setAdapter(adapter2);
            listView3.setAdapter(adapter3);
        }


    }
}
