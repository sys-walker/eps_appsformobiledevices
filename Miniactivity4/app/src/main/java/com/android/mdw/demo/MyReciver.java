package com.android.mdw.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String text = intent.getStringExtra("opcio");
        Intent in = new Intent(context, ElServicio.class);

        if(text.equals("Reproducir Sonido")){
            Toast.makeText(context, R.string.BroadcastNoise, Toast.LENGTH_LONG).show();
            in.putExtra("opcio", "Reproducir Sonido");
            context.startService(in);

        }else if(text.equals("Reproducir Cancion")){
            Toast.makeText(context, R.string.BroadcastMusic, Toast.LENGTH_LONG).show();
            in.putExtra("opcio", "Reproducir Cancion");
            context.startService(in);

        }else {
            Toast.makeText(context, R.string.BroadcastStop, Toast.LENGTH_LONG).show();
            context.stopService(in);
        }
    }
}
