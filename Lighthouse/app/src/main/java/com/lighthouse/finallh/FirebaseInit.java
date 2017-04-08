package com.lighthouse.finallh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FirebaseInit extends AppCompatActivity {
    ArrayList<String> test =  new ArrayList<String>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fire_init);
        test.add("free");
        test.add("oncampus");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void addEvent(View v) {

        Time now = new Time();
        now.setToNow();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        String[] tags = new String[2];
        tags[0] = "free";
        tags[1] = "oncampus";
        Firebase.setAndroidContext(this);


        dataBaseHandler DB = new dataBaseHandler();
        DB.addEvent("STEM",formattedDate,formattedDate, c.getTime().toString(),
                c.getTime().toString(), "jacaranda",tags,"good event");






    }// end of AddEvent

    public void filterEvent(View v) {




        Intent intent = new Intent(FirebaseInit.this, DisplayEventsByTags.class);

        intent.putStringArrayListExtra("tags", test);
        startActivity(intent);


    }// end of FilterEvent



}// end of class
