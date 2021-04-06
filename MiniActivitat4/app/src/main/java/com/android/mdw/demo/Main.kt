package com.android.mdw.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class Main : Activity(), View.OnClickListener {
    private var newintent: Intent? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val btnInicio = findViewById<View>(R.id.btnInicio) as Button
        val btnFin = findViewById<View>(R.id.btnFin) as Button
        btnInicio.setOnClickListener(this)
        btnFin.setOnClickListener(this)
        newintent = Intent(this, ElServicio::class.java)
    }

    override fun onClick(src: View) {
        when (src.id) {
            R.id.btnInicio -> startService(newintent)
            R.id.btnFin -> stopService(newintent)
        }
    }
}