package com.example.miniactivity_3_op;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            TextView tv = findViewById(R.id.textView1);
            Bundle data = this.getIntent().getExtras();
            String text = data.getString(Intent.EXTRA_TEXT);
            tv.setText(text);
        } catch(Exception e){}

    }
}
