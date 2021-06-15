package com.android.mdw.demo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ElServicio extends Service {

	private MediaPlayer music;
	private MediaPlayer noise;
	private int m = 0;
	private int n = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show();
		if(m == 1){
            music.stop();
        }

		if(n == 1){
		    noise.stop();
        }
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {
		Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show();
		String text = intent.getStringExtra("opcio");
		if(text.equals("Reproducir Sonido")){
			Toast.makeText(this, R.string.chooseNoise, Toast.LENGTH_LONG).show();
			noise = MediaPlayer.create(this, R.raw.train);
			noise.setLooping(true);
			n = 1;
			noise.start();
		}else {
			Toast.makeText(this, R.string.chooseMusic, Toast.LENGTH_LONG).show();
			music = MediaPlayer.create(this, R.raw.bob_marley_cybl);
			music.setLooping(true);
			m = 1;
			music.start();
		}
		return startid;		
	}	

}
