package com.example.neyrojas.pmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);
        // Defined Array values to show in ListView
        String[] values = new String[] { "Alajuela",
                "Chacarita",
                "La Uruca"
        };
        // Define a new Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                if (itemPosition == 0){
                    callMaps(view, 10.004359, -84.226710, itemValue);
                } else if (itemPosition == 1){
                    callMaps(view, 9.979898, -84.770196, itemValue);
                } else if (itemPosition == 2){
                    callMaps(view, 9.946440, -84.093877, itemValue);
                }
            }
        });
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

    public void callMaps(View view, double pLat, double pLng, String pName) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("Nombre", pName);
        intent.putExtra("Lat", pLat);
        intent.putExtra("Lng", pLng);
        startActivity(intent);
    }
}
