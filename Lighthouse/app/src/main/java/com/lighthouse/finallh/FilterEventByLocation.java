package com.lighthouse.finallh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FilterEventByLocation extends AppCompatActivity {

    public List<String> num = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_display);
        final ListView eventView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,  android.R.id.text1);
        Firebase.setAndroidContext(getApplicationContext());
        Intent myintent = getIntent();



        final String locName = myintent.getStringExtra("location");
        System.out.println(locName + " from location filter");
        Firebase root = new Firebase("https://appjamlh.firebaseio.com/Events");

        eventView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot Snapshot) {

                //for (int i = 0; i < tags.size(); i++)
                for (DataSnapshot messageSnapshot : Snapshot.getChildren()) {
                    if (messageSnapshot.child("location").getValue().equals(locName)) {

                        adapter.add((String) messageSnapshot.child("name").getValue());
                        num.add((String) messageSnapshot.child("name").getValue());


                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


        eventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DisplaySingleEvent.class);
                intent.putExtra("name", value);
                System.out.println(value);
                startActivity(intent);
            }
        });

    }// end of onCreate

}
