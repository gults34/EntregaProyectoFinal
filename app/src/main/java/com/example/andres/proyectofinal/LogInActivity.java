package com.example.andres.proyectofinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LogInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private UserHelper userHelper;
    private ImageView imageView;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean silent = settings.getBoolean("myBoolean", false);



        if ( settings.contains("usuario")  && !settings.getString("usuario", "default").equals("")) {
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();
        }
        imageView = (ImageView) findViewById(R.id.imageView);
        username = (EditText)findViewById(R.id.editTextu);
        password =  (EditText) findViewById(R.id.editTextc);
        userHelper = new UserHelper(getApplicationContext());
        layout = findViewById(R.id.layout);

        downloadImage(layout);
    }

    public void showUserList(View view) {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    public void pantallaSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }

    /*public void abrirRecycler(View view) {
        Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
        startActivity(intent);
    }*/

    public void compruebaLogIn(View view) {
        View vi = this.getCurrentFocus();
        if (vi != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        Cursor c = userHelper.existsUsername(String.valueOf(username.getText()));
        Cursor v = userHelper.getPassByUsername(String.valueOf(username.getText()));
        if (username.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(layout,"Username Inválido",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (password.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(layout,"Contraseña Inválida",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (!c.moveToFirst()) {
            Snackbar snackbar = Snackbar.make(layout, "Este usuario no existe", Snackbar.LENGTH_LONG);
            snackbar.show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Cuenta incorrecta");

            builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Inicio de sesión", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (v.moveToFirst() && !password.getText().toString().equals(v.getString(v.getColumnIndex("pass")))) {
            Snackbar snackbar = Snackbar.make(layout,"Esta contraseña no corresponde a este usuario",Snackbar.LENGTH_LONG);
            snackbar.show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Cuenta incorrecta");

            builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Inicio de sesión", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            SharedPreferences settings = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();

            Cursor j = userHelper.getUser(String.valueOf(username.getText()));
            editor.putInt("puntuacion", j.getColumnIndex("puntuacion"));
            editor.putString("usuario", username.getText().toString());
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
        }
    }

    public void downloadImage(View v) {
        new Thread(new Runnable() {
            private Bitmap loadImageFromNetwork(String url) {
                try {
                    Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(url)
                                    .getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void run() {
                final Bitmap bitmap = loadImageFromNetwork("http://www.cdlibre.org/clase/0405amaya/04057m/Miguel_Bruni/stile/img1.jpg");
                Message msg = new Message();
                msg.what = 100;
                msg.obj = bitmap;
                mhHandler.sendMessage(msg);
            }
        }).start();
    }

    private Handler mhHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 100 && msg.obj != null) {
                BitmapDrawable ob = new BitmapDrawable(getResources(), (Bitmap)msg.obj);
                layout.setBackground(ob);
            }
        }
    };
}
