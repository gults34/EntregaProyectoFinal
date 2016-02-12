package com.example.andres.proyectofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Menú Principal");

        layout = (View) findViewById(R.id.layout);
        
    }

    public void pantallaCalculadora (View view) {
        Intent intent = new Intent(getApplicationContext(), CalculadoraActivity.class);
        startActivity(intent);
    }

    public void pantallaMemory (View view) {
        Intent intent = new Intent(getApplicationContext(), MemoryRankingActivity.class);
        startActivity(intent);
    }

    public void pantallaMusica (View view) {
        Intent intent = new Intent(getApplicationContext(), MusicaActivity.class);
        startActivity(intent);
    }
    public void pantallaPerfil (View view) {
        Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("puntuacion", -1);
                editor.putString("usuario", "");
                editor.apply();
                Intent inten = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(inten);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
