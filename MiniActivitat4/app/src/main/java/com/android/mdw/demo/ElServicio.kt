package com.android.mdw.demo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class ElServicio : Service() {
    private var player: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    override fun onCreate() {
        Toast.makeText(this, R.string.creaserv, Toast.LENGTH_LONG).show()
        player = MediaPlayer.create(this, R.raw.train)
        player.setLooping(true)
    }

    override fun onDestroy() {
        Toast.makeText(this, R.string.finaserv, Toast.LENGTH_LONG).show()
        player!!.stop()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startid: Int): Int {
        Toast.makeText(this, R.string.iniserv, Toast.LENGTH_LONG).show()
        player!!.start()
        return startid
    }
}