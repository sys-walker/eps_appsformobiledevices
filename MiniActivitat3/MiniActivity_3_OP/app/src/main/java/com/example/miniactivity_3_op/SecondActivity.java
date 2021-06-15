package com.example.miniactivity_3_op;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView tv = findViewById(R.id.textView2);
        Uri data = this.getIntent().getData();
        String url = data.toString();
        tv.setText(url);
    }
}
