package com.android.mdw.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
  private Intent in;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Button btnInicio = (Button) findViewById(R.id.btnInicio);
    Button btnFin = (Button) findViewById(R.id.btnFin);

    btnInicio.setOnClickListener(this);
    btnFin.setOnClickListener(this);

    in = new Intent(this, ElServicio.class);
  }

  @Override
  public void onClick(View src) {
    switch (src.getId()) {
    case R.id.btnInicio:      
      startService(in);
      break;
    case R.id.btnFin:
      stopService(in);
      break;
    }
  }
}