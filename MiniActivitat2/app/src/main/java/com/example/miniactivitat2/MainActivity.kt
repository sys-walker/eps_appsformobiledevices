package com.example.miniactivitat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val editText: EditText = findViewById(R.id.editText)
        val editText2: EditText = findViewById(R.id.editText2)

        button.setOnClickListener{
            var intent = Intent(this, Activity2::class.java)
            intent.putExtra("text", editText.text.toString())
            intent.putExtra("rep", editText2.text.toString().toInt())
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            var textview: TextView = findViewById(R.id.textView4)
            val returnString = data!!.getStringExtra("MESSAGE")
            textview.text = returnString
        }
    }




}