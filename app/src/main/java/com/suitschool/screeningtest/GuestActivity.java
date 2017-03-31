package com.suitschool.screeningtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLConnection;
import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity {
    private ArrayList<Guest> guests;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        guests = new ArrayList<>();
        new JSONParser().execute();
        Log.d("GUEST", "jumlah geust: " + guests.size());

//        Guest guest0 = new Guest(0, "amal", "27011997", 0);
//        Guest guest1 = new Guest(1, "ikhwan", "27011997", 1);
//        Guest guest2 = new Guest(2, "malik", "27011997", 2);
//        Guest guest3 = new Guest(3, "fari", "27011997", 3);
//        Guest guest4 = new Guest(4, "adi", "27011997", 4);
//
//        guests.add(guest0);
//        guests.add(guest1);
//        guests.add(guest2);
//        guests.add(guest3);
//        guests.add(guest4);
        Log.d("GUEST", "jumlah geust: " + guests.size());

        gridView = (GridView) findViewById(R.id.grid_view);
    }


    private class JSONParser extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler httpHandler = new HttpHandler();
            String urlStr = "http://dry-sierra-6832.herokuapp.com/api/people";
            String jsonStr = httpHandler.makeServiceCall(urlStr);

            Log.d("JSON", "JSON: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray guestJson = new JSONArray(jsonStr);
                    int numObject = guestJson.length();
                    for (int i = 0; i < numObject; i++) {
                        JSONObject jObj = guestJson.getJSONObject(i);
                        int id = jObj.getInt("id");
                        String nama = jObj.getString("name");
                        String ttl = jObj.getString("birthdate");
                        Guest guest = new Guest(id, nama, ttl, R.drawable.suitlogo);
                        guests.add(guest);
                        Log.d("JSON", "GUEST ADDED..................." + nama);
                    }
                } catch (JSONException jex) {
                    Log.e("JSON ERROR", "JSON EXXXXXXXCCEPTION");
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    Log.e("App", "Success: ");
                } catch (Exception ex) {
                    Log.e("App", "Failure", ex);
                }
            }

            final GuestAdapter adapter = new GuestAdapter(GuestActivity.this, guests);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(GuestActivity.this, "" + position,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GuestActivity.this, MenuActivity.class);
                    String tamu = adapter.getItem(position).getNama();
                    String ttl = adapter.getItem(position).getTtl();
                    intent.putExtra("NAMA_TAMU", tamu);
                    intent.putExtra("TTL", ttl);
                    startActivityForResult(intent, 2);
                    finish();
                }
            });
        }
    }
}