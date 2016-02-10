package com.example.andres.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView user,email,punt;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        imageView = (ImageView) findViewById(R.id.imageViewIcon);
        user = (TextView) findViewById(R.id.textViewUsuario);
        punt = (TextView) findViewById(R.id.textViewPunt);

        imageView.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean silent = settings.getBoolean("myBoolean", false);

        String usuario = settings.getString("usuario", "default");

        Log.v("usuario",usuario+"");

        user.setText(usuario.toString());

        Integer puntuacion = settings.getInt("puntuacion", 0);
        punt.setText(puntuacion.toString());

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewIcon:
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
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

    public void pantallaCambiarContraseña() {
        Intent intent = new Intent(getApplicationContext(), CambiarContrasenaActivity.class);
        startActivity(intent);
    }
}
