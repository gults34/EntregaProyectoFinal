package com.example.andres.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView user,email,punt;
    private UserHelper userHelper;
    private Button botonCambiar,botonGPS,botonLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Perfil");

        botonLogOut = (Button) findViewById(R.id.buttonLogOut);
        botonCambiar = (Button) findViewById(R.id.buttonCambiar);
        botonGPS = (Button) findViewById(R.id.buttonGPS);
        imageView = (ImageView) findViewById(R.id.imageViewIcon);
        user = (TextView) findViewById(R.id.textViewUsuario);
        punt = (TextView) findViewById(R.id.textViewPunt);

        imageView.setOnClickListener(this);
        botonCambiar.setOnClickListener(this);
        botonGPS.setOnClickListener(this);
        botonLogOut.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean silent = settings.getBoolean("myBoolean", false);

        String usuario = settings.getString("usuario", "default");

        user.setText(usuario.toString());

        userHelper = new UserHelper(getApplicationContext());

        Cursor c = userHelper.getUser(usuario);

        if (c.moveToFirst()) {
            if (c.getInt(2) == 0) punt.setText("N/A");
            else punt.setText(c.getInt(2)+"");
        }

        Cursor m = userHelper.getFotoByUsername(usuario);
        if (m.moveToFirst()) {
            String s = m.getString(0);
            Log.v("uri",s+"");
            if (!s.equals("-1") && !s.equals("null"))  {
                imageView.setImageURI(Uri.parse(s));
            }
            else {
                imageView.setImageResource(R.drawable.icon0);
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewIcon:
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
                break;
            case R.id.buttonCambiar:
                Intent intent = new Intent(getApplicationContext(), CambiarContrasenaActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonGPS:
                Intent i = new Intent(getApplicationContext(), LocalizacionActivity.class);
                startActivity(i);
                break;
            case R.id.buttonLogOut:
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("puntuacion", -1);
                editor.putString("usuario", "");
                editor.apply();
                Intent inten = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(inten);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                data.getData();
                Uri selectedImage = data.getData();
                Log.v("PICK", "Selected image uri" + selectedImage);

                ContentValues valuesToStore = new ContentValues();
                valuesToStore.put("foto", selectedImage.toString());

                SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                boolean silent = settings.getBoolean("myBoolean", false);

                String us = settings.getString("usuario", "default");
                userHelper = new UserHelper(getApplicationContext());
                userHelper.addFoto(us,valuesToStore);
                userHelper.close();
                try {
                    imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            Log.v("Result","Something happened");
        }
    }
}
