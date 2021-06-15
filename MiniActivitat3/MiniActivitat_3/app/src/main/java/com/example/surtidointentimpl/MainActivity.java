package com.example.surtidointentimpl;


import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		Button btn1 = findViewById(R.id.button1);
		Button btn2 = findViewById(R.id.button2);
		Button btn3 = findViewById(R.id.button3);
		Button btn4 = findViewById(R.id.button4);
		Button btn5 = findViewById(R.id.button5);
		Button btn6 = findViewById(R.id.button6);
		Button btn7 = findViewById(R.id.button7);
		Button btn8 = findViewById(R.id.button8);
		Button btn9 = findViewById(R.id.button9);
		Button btn10 = findViewById(R.id.button10);
		Button btn11 = findViewById(R.id.button11);


		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);


		if (Build.VERSION.SDK_INT >= 23)
			if (! ckeckPermissions())
				requestPermissions();
	}

	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if(requestCode == 0) {
            if (resultCode == RESULT_OK) {
                ImageView img = findViewById(R.id.imageView);
                Uri uri = data.getData();
                try {
                    InputStream str = getContentResolver().openInputStream(uri);
                    Bitmap bit = BitmapFactory.decodeStream(str);
                    img.setImageBitmap(bit);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                TextView tw = findViewById(R.id.textContact);
                Uri uri = data.getData();
                Cursor cu = getContentResolver().query(uri, null, null, null, null);
                cu.moveToFirst();
                int nameIndex = cu.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cu.getString(nameIndex);
                tw.setText(name);
            }
        } else if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				TextView tw = findViewById(R.id.textView3);
				Bundle passedData = data.getExtras();
				String mess = passedData.getString(getString(R.string.mess));
				tw.setText(mess);

			}
		}
	}

	public void onClick (View v) {
		Intent in;
		final String lat = "41.60788";
		final String lon = "0.623333";
		final String url = "http://www.eps.udl.cat/";
		final String adressa = "Carrer de Jaume II, 69, Lleida";
		final String textoABuscar = "escuela politecnica superior";

		switch (v.getId()) {
			//Localización por coordenadas
			case R.id.button1:
				Toast.makeText(this, getString(R.string.opcio1), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + ',' + lon));
				startActivity(in);
				break;
			//Localización por direccion
			case R.id.button2:
				Toast.makeText(this, getString(R.string.opcio2), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + adressa));
				startActivity(in);
				break;
			//Acceder a la web
			case R.id.button3:
				Toast.makeText(this, getString(R.string.opcio3), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(in);
				break;
			//Buscar Google
			case R.id.button4:
				Toast.makeText(this, getString(R.string.opcio4), Toast.LENGTH_LONG).show();
				//in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + "escola politecnica superior UdL"));
				in = new Intent(Intent.ACTION_WEB_SEARCH);
				in.putExtra(SearchManager.QUERY, textoABuscar);
				startActivity(in);
				break;
			//Llamar a tlf
			case R.id.button5:
				Toast.makeText(this, getString(R.string.opcio5), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getText(R.string.telef)));
				startActivity(in);
				break;
			//Marcar tlf
			case R.id.button6:
				Toast.makeText(this, getString(R.string.opcio6), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getText(R.string.telef)));
				startActivity(in);
				break;
			//Acceder contactos
			case R.id.button7:
				Toast.makeText(this, getString(R.string.opcio7), Toast.LENGTH_LONG).show();

				//obligatoria
//				in = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
//				startActivity(in);

				//optatiu
				in = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(in, 1);
				break;
			//Enviar SMS
			case R.id.button8:
				Toast.makeText(this, getString(R.string.opcio8), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_SENDTO);
				in.setData(Uri.parse("sms:" + getText(R.string.smsDest2)));
				in.putExtra(Intent.EXTRA_TEXT, getText(R.string.textMiss));
				startActivity(in);
				break;
			//Enviar email
			case R.id.button9:
				Toast.makeText(this, getString(R.string.opcio9), Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_SENDTO);
				in.setData(Uri.parse("mailto:" + getText(R.string.mail)));
				in.putExtra(Intent.EXTRA_TEXT, getText(R.string.textMiss));
				in.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.demo));
				startActivity(in);
				break;
			//Acceder galeria
			case R.id.button10:
				Toast.makeText(this, getString(R.string.opcio10), Toast.LENGTH_LONG).show();

				//obligatoria
//				in = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//				startActivity(in);

				//optatiu
				in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(in, 0);
				break;

			//Acceder a ThirdActivity optativa
			case R.id.button11:
				Toast.makeText(this, R.string.third, Toast.LENGTH_LONG).show();
				in = new Intent(Intent.ACTION_PICK);
				startActivityForResult(in, 2);
				break;
		}
	}

	private boolean ckeckPermissions() {
		if (Build.VERSION.SDK_INT >= 23) {
			if (ActivityCompat.checkSelfPermission(getApplicationContext(),
					Manifest.permission.CALL_PHONE) ==
					PackageManager.PERMISSION_GRANTED)
				return true;
			else
				return false;
		}
		else
			return true;
	}

	private void requestPermissions() {
		ActivityCompat.requestPermissions(MainActivity.this,
				new String[]{Manifest.permission.CALL_PHONE},
				0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
