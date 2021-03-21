package com.example.miniactivitat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val editText: EditText = findViewById(R.id.editText)
        val editText2: EditText = findViewById(R.id.editText2)

        var text:String = intent.getStringExtra("title").toString()

        button.setOnClickListener{
            var intent = Intent(this, Activity2::class.java)
            intent.putExtra("text", editText.text.toString())
            intent.putExtra("rep", editText2.text.toString().toInt())
            startActivity(intent)
        }
    }




}