package com.example.andres.proyectofinal;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pant;
    private Button boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8,boton9,boton0,botons,botonr,botond,botoni,botonp,botonx,botonb,botonans;
    private double num1 = 0.0,num2 = 0.0,res = 0.0,ans = 0.0;
    private Integer op = 0; //1=suma,2=resta,3=mult,4=div
    private CoordinatorLayout coordinatorLayout;
    private View layout;
    private Boolean lastigual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.layout);

        pant = (TextView) findViewById(R.id.pantalla);
        boton0 = (Button) findViewById(R.id.button0);
        boton1 = (Button) findViewById(R.id.button1);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        boton4 = (Button) findViewById(R.id.button4);
        boton5 = (Button) findViewById(R.id.button5);
        boton6 = (Button) findViewById(R.id.button6);
        boton7 = (Button) findViewById(R.id.button7);
        boton8 = (Button) findViewById(R.id.button8);
        boton9 = (Button) findViewById(R.id.button9);
        botons = (Button) findViewById(R.id.buttons);
        botonr = (Button) findViewById(R.id.buttonr);
        botonx = (Button) findViewById(R.id.buttonx);
        botond = (Button) findViewById(R.id.buttond);
        botoni = (Button) findViewById(R.id.buttoni);
        botonp = (Button) findViewById(R.id.buttonp);
        botonb = (Button) findViewById(R.id.buttonb);
        botonans = (Button) findViewById(R.id.buttonans);

        boton0.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);
        boton6.setOnClickListener(this);
        boton7.setOnClickListener(this);
        boton8.setOnClickListener(this);
        boton9.setOnClickListener(this);
        botons.setOnClickListener(this);
        botonr.setOnClickListener(this);
        botonx.setOnClickListener(this);
        botond.setOnClickListener(this);
        botoni.setOnClickListener(this);
        botonp.setOnClickListener(this);
        botonb.setOnClickListener(this);
        botonans.setOnClickListener(this);
    }

    public void onClick(View v) {
        /*if (lastigual)  {
            lastigual = false;
            pant.setText("");
        }*/
        switch(v.getId()) {
            case R.id.button0:
                pant.setText(pant.getText()+"0");
                break;
            case R.id.button1:
                pant.setText(pant.getText()+"1");
                break;
            case R.id.button2:
                pant.setText(pant.getText()+"2");
                break;
            case R.id.button3:
                pant.setText(pant.getText()+"3");
                break;
            case R.id.button4:
                pant.setText(pant.getText()+"4");
                break;
            case R.id.button5:
                pant.setText(pant.getText()+"5");
                break;
            case R.id.button6:
                pant.setText(pant.getText()+"6");
                break;
            case R.id.button7:
                pant.setText(pant.getText()+"7");
                break;
            case R.id.button8:
                pant.setText(pant.getText()+"8");
                break;
            case R.id.button9:
                pant.setText(pant.getText()+"9");
                break;
            case R.id.buttonp:
                pant.setText(pant.getText()+".");
                break;
            case R.id.buttons:
                op = 1;
                if (pant.getText().toString() != "") num1 = Double.parseDouble(pant.getText().toString());
                pant.setText("");
                break;
            case R.id.buttonr:
                op = 2;
                if (pant.getText().toString() != "") num1 = Double.parseDouble(pant.getText().toString());
                pant.setText("");
                break;
            case R.id.buttond:
                op = 4;
                if (pant.getText().toString() != "") num1 = Double.parseDouble(pant.getText().toString());
                pant.setText("");
                break;
            case R.id.buttonx:
                op = 3;
                if (pant.getText().toString() != "") num1 = Double.parseDouble(pant.getText().toString());
                pant.setText("");
                break;
            case R.id.buttoni:
                if (pant.getText().toString() != "") num2 = Double.parseDouble(pant.getText().toString());
                switch(op) {
                    case 1:
                        res = num1 + num2;
                        break;
                    case 2:
                        res = num1 - num2;
                        break;
                    case 3:
                        res = num1 * num2;
                        break;
                    case 4:
                        if (num2 == 0) {
                            Snackbar snackbar = Snackbar.make(layout,"Estas haciendo una división entre 0",Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        num2 = 1.0;
                        res = num1 / num2;
                        break;
                }
                lastigual = true;
                ans = res;
                pant.setText(res+"");
                break;
            case R.id.buttonb:
                num1 = 0.0;
                num2 = 0.0;
                op = 0;
                pant.setText("");
                ans = 0.0;
                break;
            case R.id.buttonans:
                num1 = ans;
                pant.setText(""+ans);
                break;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.llamar:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                startActivity(intent);
                return true;
            case R.id.navegador:
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.es"));
                startActivity(i);
                return true;
            case R.id.notificacion:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TextView pant = (TextView) findViewById(R.id.pantalla);
        bundle.putString("result", pant.getText().toString());
        bundle.putDouble("num1",num1);
        bundle.putDouble("num2", num2);
        bundle.putDouble("op", op);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        TextView pant = (TextView) findViewById(R.id.pantalla);
        pant.setText(bundle.getString("result"));
        num1 = bundle.getInt("num1");
        num2 = bundle.getInt("num2");
        op = bundle.getInt("op");
    }
}
