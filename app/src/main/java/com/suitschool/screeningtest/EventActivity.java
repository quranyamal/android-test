package com.suitschool.screeningtest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    int numEvent = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        ArrayList<Event> events = new ArrayList<>();

        Event event1 = new Event("Intership di Suitmedia", "1 Juni 2017", "suitmedia.jpg");
        Event event2 = new Event("Pulang kampung", "25 Juni 2017", "pantaipadang.jpg");
        Event event3 = new Event("Cari jodoh", "7 Juli 2017", "jodoh.jpg");
        Event event4 = new Event("Traveling", "20 Agus 2017", "traveling.jpg");
        Event event5 = new Event("Ngambizz...", "25 Agus 2017", "ngambis.jpg");
        Event event6 = new Event("Laloe", "25 Agus 2017", "ngambis.jpg");

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);


        ListView listView = (ListView) findViewById(R.id.event_view);
        final EventListViewAdapter adapter = new EventListViewAdapter(this, events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String judulAcara = adapter.getItem(position).getJudul();
                Intent intent = new Intent(EventActivity.this, MenuActivity.class);
                intent.putExtra("ACARA", judulAcara);
                startActivityForResult(intent, 1);
                finish();
            }
        });
    }
}
