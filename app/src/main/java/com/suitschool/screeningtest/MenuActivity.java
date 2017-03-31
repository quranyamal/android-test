package com.suitschool.screeningtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    static final int ACARA_DIPILIH = 1;
    static final int GUEST_DIPILIH = 2;

    TextView namaTextView;
    Button eventButton, guestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String nama = "Nama : " + sharedPreferences.getString("NAMA", "error getString from shared preference");

        namaTextView = (TextView) findViewById(R.id.menu_nama_textview);
        namaTextView.setText(nama);
        eventButton = (Button) findViewById(R.id.menu_event_button);
        guestButton = (Button) findViewById(R.id.menu_guest_button);

        Log.d("CREATE", "onCreate MenuActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUME", "onResume MenuActivity");
        if (getIntent()!=null) {
            Log.d("intet", "get intent not null");
            if (getIntent().hasExtra("ACARA")) {
                Log.d("RESUMEEEEE", "acara has extraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                eventButton.setText(getIntent().getStringExtra("ACARA"));
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACARA_DIPILIH:
                if (resultCode == RESULT_OK)
                    eventButton.setText(getIntent().getStringExtra("ACARA"));
                break;
            case GUEST_DIPILIH:
                if (resultCode == RESULT_OK) {
                    guestButton.setText(getIntent().getStringExtra("NAMA_TAMU"));
                    String ttl = getIntent().getStringExtra("TTL");
                    Toast.makeText(MenuActivity.this, "" + ttl, Toast.LENGTH_SHORT).show();
                }
                break;
        };
    }

    public void onClickEvent(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void onClickGuest(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivity(intent);
    }
}
