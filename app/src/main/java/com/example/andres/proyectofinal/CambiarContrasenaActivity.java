package com.example.andres.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CambiarContrasenaActivity extends AppCompatActivity {

    private EditText antigua;
    private EditText nueva;
    private EditText repetida;
    private UserHelper userHelper;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Cambiar contraseña");

        antigua = (EditText)findViewById(R.id.editTextCA);
        nueva =  (EditText) findViewById(R.id.editTextCN);
        repetida =  (EditText) findViewById(R.id.editTextCNR);
        userHelper = new UserHelper(getApplicationContext());
        layout = findViewById(R.id.layout);

        layout.setBackgroundResource(R.drawable.fondoazul);
    }

    public void cambioContraseña(View v) {
        View vi = this.getCurrentFocus();
        userHelper = new UserHelper(getApplicationContext());
        if (vi != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean silent = settings.getBoolean("myBoolean", false);

        Cursor c = userHelper.getUser(settings.getString("usuario", "default"));
        if (nueva.getText().toString().equals("") || repetida.getText().toString().equals("") || antigua.getText().toString().equals("")) {
            Snackbar snackbar = Snackbar.make(layout,"Rellena todos los campos",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (c.moveToFirst() && !c.getString(c.getColumnIndex("pass")).equals(antigua.getText().toString())) {
            Snackbar snackbar = Snackbar.make(layout,"La contraseña antigua no es correcta",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (!nueva.getText().toString().equals(repetida.getText().toString())) {
            Snackbar snackbar = Snackbar.make(layout,"Las contraseñas no coinciden",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put("pass", String.valueOf(nueva.getText().toString()));
            userHelper.cambiarContraseña(settings.getString("usuario", "default"),valuesToStore);
            finish();
        }
        userHelper.close();
    }
}
