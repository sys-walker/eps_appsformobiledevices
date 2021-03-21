package com.example.miniactivitat2

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val textView: TextView = findViewById(R.id.textView3)
        val button: Button = findViewById(R.id.button2)

        var text:String = intent.getStringExtra("text").toString()
        var rep:Int = intent.getIntExtra("rep", 1)

        if(rep == 0){
            textView.text = ""
        }

        while (rep != 0){
            textView.append(text)
            rep -= 1
        }

        button.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("title", textView.text.toString())
            startActivity(intent)
        }

    }
}