package com.suitschool.screeningtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {
    EditText inputNama;
    String mInputNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inputNama = (EditText) findViewById(R.id.home_nama_input);
        inputNama.requestFocus();
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mInputNama = inputNama.getText().toString();
        editor.putString("NAMA", mInputNama);
        editor.commit();

        startActivity(intent);
    }
}
