package com.example.andres.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Menú Principal");
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
}
