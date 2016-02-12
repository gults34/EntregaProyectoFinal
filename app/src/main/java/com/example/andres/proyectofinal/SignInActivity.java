package com.example.andres.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText cpassword;
    EditText email;
    UserHelper userHelper;
    View layout;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    username = (EditText)findViewById(R.id.editTextu);
    password =  (EditText) findViewById(R.id.editTextc);
    cpassword = (EditText)findViewById(R.id.editTextcc);
    userHelper = new UserHelper(getApplicationContext());
    layout = findViewById(R.id.layout);

    layout.setBackgroundResource((R.drawable.fondoazul));
}

public void addUser(View view) {
    View vi = this.getCurrentFocus();
    if (vi != null) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    Cursor c = userHelper.existsUsername(String.valueOf(username.getText()));
    if (username.getText().toString().equals("")) {
        Snackbar snackbar = Snackbar.make(layout,"Username Inválido",Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    else if (c.moveToFirst())  {
        Snackbar snackbar = Snackbar.make(layout,"Ya existe este usuario",Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    else if (password.getText().toString().equals("")) {
        Snackbar snackbar = Snackbar.make(layout,"Contraseña Inválida",Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    else if (!password.getText().toString().equals(cpassword.getText().toString())) {
        Snackbar snackbar = Snackbar.make(layout,"Las contraseñas no coinciden",Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    else {
        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put("username", String.valueOf(username.getText()));
        valuesToStore.put("pass", String.valueOf(password.getText()));
        valuesToStore.put("foto", "-1");
        valuesToStore.put("punt", -1);
        valuesToStore.put("noti", -1);
        userHelper.createUser(valuesToStore, "User");

        Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(intent);
    }
}
}
