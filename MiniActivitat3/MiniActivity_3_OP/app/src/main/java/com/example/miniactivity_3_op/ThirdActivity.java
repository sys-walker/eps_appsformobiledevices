package com.example.miniactivity_3_op;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    Button but;
    EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        but = findViewById(R.id.button);
        but.setOnClickListener(this);
        et = findViewById(R.id.editText);
    }

    public void onClick(View v){
        String message = this.et.getText().toString();
        if (message.isEmpty()) message = getString(R.string.defaulText);
        Intent in = new Intent();
        in.putExtra(getString(R.string.mess), message);
        setResult(RESULT_OK, in);
        finish();
    }
}
