package com.android.mdw.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyHeadsetReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int state;
        Intent in = new Intent(context, ElServicio.class);
        state = intent.getIntExtra("state",0);

        if(state == 1){
            in.putExtra("opcio", "Reproducir Cancion");
            context.startService(in);
        }
    }
}
