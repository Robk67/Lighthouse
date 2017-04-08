package com.lighthouse.finallh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DisplayEventsByTags extends AppCompatActivity {

    public List<Long> num = new ArrayList<Long>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_display);
        final ListView eventView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<Long> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,  android.R.id.text1);
        Firebase.setAndroidContext(getApplicationContext());
        Intent myintent = getIntent();
        final ArrayList<String> tags = myintent.getStringArrayListExtra("tags");
        System.out.println("the intent" + tags);
        Firebase root = new Firebase("https://appjamlh.firebaseio.com/Events");
        eventView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot Snapshot) {

                for (int i = 0; i < tags.size(); i++)
                    for (DataSnapshot messageSnapshot : Snapshot.getChildren())
                       for (DataSnapshot tagSnapshot : messageSnapshot.child("tags").getChildren())
                           if (tags.get(i).equals(tagSnapshot.getKey()))
                                if (!num.contains(Long.parseLong(tagSnapshot.getValue().toString()))) {
                                     adapter.add(Long.parseLong(tagSnapshot.getValue().toString()));
                                     num.add(Long.parseLong(tagSnapshot.getValue().toString()));

                                    }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

    }// end of onCreate

    }// end of Display Class




