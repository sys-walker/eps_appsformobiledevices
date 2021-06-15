package com.android.mdw.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
    private Intent in;
    Button btnSonido;
    Button btnCancion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnFin = (Button) findViewById(R.id.btnFin);

        btnSonido = (Button)findViewById(R.id.btnSonido);
        btnCancion = (Button)findViewById(R.id.btnCancion);

        btnSonido.setOnClickListener(this);
        btnCancion.setOnClickListener(this);
        btnFin.setOnClickListener(this);

        //Filter de el connectar i desconnectar auriculars (automàtic reciver)
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        MyHeadsetReciver reciver = new MyHeadsetReciver();
        this.registerReceiver(reciver, filter);

        //Manual Reciver
        in = new Intent(this, MyReciver.class);
    }

    @Override
    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.btnSonido:
                in.putExtra("opcio", btnSonido.getText());
                sendBroadcast(in);
                break;
            case R.id.btnCancion:
                in.putExtra("opcio", btnCancion.getText());
                sendBroadcast(in);
                break;
            case R.id.btnFin:
                in.putExtra("opcio", "Parar sondidos");
                sendBroadcast(in);
                break;
        }
    }

    /* Sense fer servir el BroadcastReciver directament el servei
    @Override
    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.btnSonido:
                in.putExtra("opcio", btnSonido.getText());
                startService(in);
                break;
            case R.id.btnCancion:
                in.putExtra("opcio", btnCancion.getText());
                startService(in);
                break;
            case R.id.btnFin:
                stopService(in);
                break;
        }
    }
    */
}