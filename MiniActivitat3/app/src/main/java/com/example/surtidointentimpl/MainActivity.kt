package com.example.surtidointentimpl

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val btn1: Button = findViewById<Button>(R.id.button1)
        val btn2: Button = findViewById<Button>(R.id.button2)
        val btn3: Button = findViewById<Button>(R.id.button3)
        val btn4: Button = findViewById<Button>(R.id.button4)
        val btn5: Button = findViewById<Button>(R.id.button5)
        val btn6: Button = findViewById<Button>(R.id.button6)
        val btn7: Button = findViewById<Button>(R.id.button7)
        val btn8: Button = findViewById<Button>(R.id.button8)
        val btn9: Button = findViewById<Button>(R.id.button9)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= 23) if (!ckeckPermissions()) requestPermissions()
    }


    override fun onClick(v: View) {
        val intent: Intent
        val lat: String = getString(R.string.lat)
        val lon: String = getString(R.string.lon)
        val url: String = getString(R.string.url)
        val address: String = getString(R.string.direccion)
        val textToSearch: String = getString(R.string.textoABuscar)
        when (v.id) {
            R.id.button1 -> {
                Toast.makeText(this, getString(R.string.opcion1), Toast.LENGTH_LONG).show()
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$lon"))
                startActivity(intent)
            }
            R.id.button2 -> {
                Toast.makeText(this, getString(R.string.opcion2), Toast.LENGTH_LONG).show()
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address"))
                //in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + textToSearch));
                startActivity(intent)
            }
            R.id.button3 -> {
                Toast.makeText(this, getString(R.string.opcion3), Toast.LENGTH_LONG).show()
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            R.id.button4 -> {
                Toast.makeText(this, getString(R.string.opcion4), Toast.LENGTH_LONG).show()
                //in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + textToSearch));
                intent = Intent(Intent.ACTION_WEB_SEARCH)
                intent.putExtra(SearchManager.QUERY, textToSearch)
                startActivity(intent)
            }
            R.id.button5 -> callPhone()
            R.id.button6 -> accessContacts()
            R.id.button7 -> dialPhone()
            R.id.button8 -> sendSMS()
            R.id.button9 -> sendEmail()
        }
    }


    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= 23) if (!ckeckPermissions()) requestPermissions()
    }

    private fun ckeckPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            ckeckPermissionsCallPhone() && ckeckPermissionsReadContacts()
        } else true
    }

    private fun ckeckPermissionsCallPhone(): Boolean {
        return ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun ckeckPermissionsReadContacts(): Boolean {
        return ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE),
                0)
    }

    private fun requestPermissionsCallPhone() {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CALL_PHONE),
                0)
    }

    private fun requestPermissionsReadContacts() {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS),
                0)
    }

    private fun accessContacts() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ckeckPermissionsReadContacts()) {
                accessContactsAction()
            } else {
                requestPermissionsReadContacts()
            }
        } else {
            accessContactsAction()
        }
    }

    private fun accessContactsAction() {
        val intent: Intent
        Toast.makeText(this, getString(R.string.opcion6), Toast.LENGTH_LONG).show()
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = ContactsContract.Contacts.CONTENT_URI
        startActivity(intent)
    }


    private fun callPhone() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ckeckPermissionsCallPhone()) {
                callPhoneAction()
            } else {
                requestPermissionsCallPhone()
            }
        } else {
            callPhoneAction()
        }
    }

    private fun callPhoneAction() {
        Toast.makeText(this, getString(R.string.opcion5), Toast.LENGTH_LONG).show()
        intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getText(R.string.telef)))
        startActivity(intent)
    }

    private fun dialPhone(){
        Toast.makeText(this, getString(R.string.opcion8), Toast.LENGTH_LONG).show()
        intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getText(R.string.telef)))
        startActivity(intent)
    }

    private fun sendSMS(){
        Toast.makeText(this, getString(R.string.opcion9), Toast.LENGTH_LONG).show()
        intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("sms:" + getText(R.string.telef))
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hola, que tal?")
        startActivity(intent)
    }

    private fun sendEmail(){
        Toast.makeText(this, getString(R.string.opcion10), Toast.LENGTH_LONG).show()
        intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:");
        intent.type = "text/plain";
        intent.putExtra(Intent.EXTRA_EMAIL, "abcde123@correu.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pràctica de Kotlin")
        intent.putExtra(Intent.EXTRA_TEXT, "La pràctica realitzada...")
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }
}