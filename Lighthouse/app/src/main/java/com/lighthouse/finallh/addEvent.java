package com.lighthouse.finallh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

public class addEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
       

    }


   public void addEventSave(View view){
       EditText eventName = ( EditText) findViewById(R.id.addEventname);

       EditText startDate = ( EditText) findViewById(R.id.AddEventStartdate);
       EditText endDate = (EditText) findViewById(R.id.AddEventEnddate);

       EditText startTime = ( EditText) findViewById(R.id.AddEventStarttime);
       EditText endTime = (EditText) findViewById(R.id.AddEventEndtime);

       EditText tags = ( EditText) findViewById(R.id.AddEventTags);

       EditText Comms = ( EditText) findViewById(R.id.addEventComments);

       EditText Locs = ( EditText) findViewById(R.id.addEventLocation);
       Firebase.setAndroidContext(this);


       dataBaseHandler DB = new dataBaseHandler();
       DB.addEvent(eventName.getText().toString(),startDate.getText().toString(),endDate.getText().toString(),
               startTime.getText().toString(), endTime.getText().toString(), Locs.getText().toString(),
               tags.getText().toString().split(","),Comms.getText().toString());





       Intent intent = new Intent(getApplicationContext(),FirebaseInit.class);
       startActivity(intent);

   }

}
