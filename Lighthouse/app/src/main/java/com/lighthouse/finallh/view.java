package com.lighthouse.finallh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView nameView=(TextView)findViewById(R.id.result_txt);
       // nameView.setText(getIntent().getExtras().getString("locationName"));
        nameView.setText(getIntent().getExtras().getString("location"));

    }
}
