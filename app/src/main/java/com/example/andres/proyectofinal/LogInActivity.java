package com.example.andres.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private UserHelper userHelper;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText)findViewById(R.id.editTextu);
        password =  (EditText) findViewById(R.id.editTextc);
        userHelper = new UserHelper(getApplicationContext());
        layout = findViewById(R.id.layout);
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
            Snackbar snackbar = Snackbar.make(layout,"Este usuario no existe",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else if (v.moveToFirst() && !password.getText().toString().equals(v.getString(v.getColumnIndex("pass")))) {
            Snackbar snackbar = Snackbar.make(layout,"Esta contraseña no corresponde a este usuario",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            SharedPreferences settings = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("usuario", username.getText().toString());
            Cursor j = userHelper.getUser(String.valueOf(username.getText()));
            editor.putInt("puntuacion", j.getColumnIndex("puntuacion"));
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
        }
    }
}
