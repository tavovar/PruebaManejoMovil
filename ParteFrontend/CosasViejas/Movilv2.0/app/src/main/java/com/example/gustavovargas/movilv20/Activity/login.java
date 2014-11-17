package com.example.gustavovargas.movilv20.Activity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.gustavovargas.movilv20.Constantes.Constantes;
import com.example.gustavovargas.movilv20.Modelos.Resultado;
import com.example.gustavovargas.movilv20.Modelos.Usuario;
import com.example.gustavovargas.movilv20.ParseJson.ConexionAPI;
import com.example.gustavovargas.movilv20.R;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class login extends FragmentActivity {

    private LoginButton loginBtn;
    private Button postImageBtn;
    private Button updateStatusBtn;

    private UiLifecycleHelper uiHelper;

    private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

    private static String message = "Sample status posted from android app";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {
                    Usuario.getInstance().idF = Long.parseLong(user.getId());
                    Usuario.getInstance().correo = "";
                    Usuario.getInstance().nombre = user.getName();
                    Toast.makeText(getBaseContext(),
                            "Bienvenido "+user.getName(),
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), menu.class);
                    startActivity(intent);
                    new PostUsuario().execute();
                } else {
                    Toast.makeText(getBaseContext(),
                            "Inicie sesión",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            if (state.isOpened()) {
                buttonsEnabled(true);
                Log.d("FacebookSampleActivity", "Facebook session opened");
            } else if (state.isClosed()) {
                buttonsEnabled(false);
                Log.d("FacebookSampleActivity", "Facebook session closed");
            }
        }
    };

    public void buttonsEnabled(boolean isEnabled) {
//      postImageBtn.setEnabled(isEnabled);
        //updateStatusBtn.setEnabled(isEnabled);
    }


    public boolean checkPermissions() {
        Session s = Session.getActiveSession();
        if (s != null) {
            return s.getPermissions().contains("publish_actions");
        } else
            return false;
    }

    public void requestPermissions() {
        Session s = Session.getActiveSession();
        if (s != null)
            s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
                    this, PERMISSIONS));
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
//        buttonsEnabled(Session.getActiveSession().isOpened());
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }

    private class PostUsuario extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(login.this);
            pDialog.setMessage("Cargando, por favor espere unos segundos...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Crea la instancia del ServiceHandler
            ConexionAPI sh = new ConexionAPI();
            // Making a request to url and getting response
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();
            NameValuePair p = new NameValuePair() {
                @Override
                public String getName() {
                    return Constantes.jsonParameters;
                }

                @Override
                public String getValue() {
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put(Constantes.tipoUsuarioLogin, Constantes.tipoFacebook);
                        jsonObj.put(Constantes.idUsuarioLogin, Usuario.getInstance().idF);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return jsonObj.toString();
                }
            };
            parametros.add(p);

            NameValuePair p2 = new NameValuePair() {
                @Override
                public String getName() {
                    return "identificacion";
                }

                @Override
                public String getValue() {
                    return Constantes.identificacion+"";
                }
            };
            parametros.add(p2);
            String jsonStr = sh.makeServiceCall(Constantes.URLUsuarios, ConexionAPI.POST, parametros);

            Log.d("Respuesta Login: ", "> " + jsonStr);

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
                        Usuario.getInstance().id = object.getInt(Constantes.idUsuarioBaseDatos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

                return null;
            }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }


    }

}