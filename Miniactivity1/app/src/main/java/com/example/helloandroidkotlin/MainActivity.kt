package com.example.helloandroidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0: Button = findViewById(R.id.button)
        button0.setOnClickListener(this)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hello_world,menu)
        return true
    }

    override fun onClick(v: View?) {
        Toast.makeText(baseContext, R.string.greeting, Toast.LENGTH_SHORT).show()
    }
}