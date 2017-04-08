package com.lighthouse.finallh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DisplaySingleEvent  extends AppCompatActivity {
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



        final String eventName = myintent.getStringExtra("name");
        System.out.println(eventName + " from Single Display");
        Firebase root = new Firebase("https://appjamlh.firebaseio.com/Events");

        eventView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot Snapshot) {

                //for (int i = 0; i < tags.size(); i++)
                for (DataSnapshot messageSnapshot : Snapshot.getChildren()){
                    if (messageSnapshot.child("name").getValue() .equals(eventName)) {

                        adapter.add((String) messageSnapshot.child("name").getValue());
                        num.add((String) messageSnapshot.child("name").getValue());
                        adapter.add((String)messageSnapshot.child("location").getValue());
                        num.add((String)messageSnapshot.child("location").getValue());
                        adapter.add((String)messageSnapshot.child("Comment").getValue());
                        num.add((String) messageSnapshot.child("Comment").getValue());

                        for (DataSnapshot tagSnapshot : messageSnapshot.child("tags").getChildren()) {
                            adapter.add(tagSnapshot.getKey());
                            num.add(tagSnapshot.getKey());
                        }
                        for (DataSnapshot tagSnapshot : messageSnapshot.child("date").getChildren()) {
                            adapter.add(tagSnapshot.getValue().toString());
                            num.add(tagSnapshot.getValue().toString());
                        }

                        for (DataSnapshot tagSnapshot : messageSnapshot.child("time").getChildren()) {
                            adapter.add(tagSnapshot.getValue().toString());
                            num.add(tagSnapshot.getValue().toString());
                        }


                    }
                    }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });




    }// end of onCreate


}
