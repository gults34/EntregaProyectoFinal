package com.example.andres.proyectofinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres.animaciongirar.GirarImagen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MemoryFragment extends Fragment implements View.OnClickListener {

    private FrameLayout frameLayout;
    private TextView fallosPant;
    private GirarImagen girarImagen;
    private ImageView[] imagenes;
    private ArrayList<Pair<ImageView,Integer>> cartas;
    private Boolean[] numeros;
    private Integer cartasLevantadas = 0;
    private Pair<Integer,Integer> carta1;
    private Pair<Integer,Integer> carta2;
    private Integer acabado = 0;
    private Integer fallos = 0;
    private Boolean valiente = false;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_memory, container, false);
        frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);

        imagenes = new ImageView[16];

        fallosPant = (TextView) rootView.findViewById(R.id.textViewFallos);

        setHasOptionsMenu(true);

        imagenes[0] = (ImageView) rootView.findViewById(R.id.imageView);
        imagenes[1] = (ImageView) rootView.findViewById(R.id.imageView2);
        imagenes[2] = (ImageView) rootView.findViewById(R.id.imageView3);
        imagenes[3] = (ImageView) rootView.findViewById(R.id.imageView4);
        imagenes[4] = (ImageView) rootView.findViewById(R.id.imageView5);
        imagenes[5] = (ImageView) rootView.findViewById(R.id.imageView6);
        imagenes[6] = (ImageView) rootView.findViewById(R.id.imageView7);
        imagenes[7] = (ImageView) rootView.findViewById(R.id.imageView8);
        imagenes[8] = (ImageView) rootView.findViewById(R.id.imageView9);
        imagenes[9] = (ImageView) rootView.findViewById(R.id.imageView10);
        imagenes[10] = (ImageView) rootView.findViewById(R.id.imageView11);
        imagenes[11] = (ImageView) rootView.findViewById(R.id.imageView12);
        imagenes[12] = (ImageView) rootView.findViewById(R.id.imageView13);
        imagenes[13] = (ImageView) rootView.findViewById(R.id.imageView14);
        imagenes[14] = (ImageView) rootView.findViewById(R.id.imageView15);
        imagenes[15] = (ImageView) rootView.findViewById(R.id.imageView16);

        imagenes[0].setOnClickListener(this);
        imagenes[1].setOnClickListener(this);
        imagenes[2].setOnClickListener(this);
        imagenes[3].setOnClickListener(this);
        imagenes[4].setOnClickListener(this);
        imagenes[5].setOnClickListener(this);
        imagenes[6].setOnClickListener(this);
        imagenes[7].setOnClickListener(this);
        imagenes[8].setOnClickListener(this);
        imagenes[9].setOnClickListener(this);
        imagenes[10].setOnClickListener(this);
        imagenes[11].setOnClickListener(this);
        imagenes[12].setOnClickListener(this);
        imagenes[13].setOnClickListener(this);
        imagenes[14].setOnClickListener(this);
        imagenes[15].setOnClickListener(this);

        generarTablero();
        return rootView;
    }

    public void generarTablero() {
        girarImagen = new GirarImagen(getContext());
        cartas = new ArrayList<>(16);
        numeros = new Boolean[16];
        valiente = false;
        cartas.add(Pair.create(imagenes[0], -1));
        cartas.add(Pair.create(imagenes[1], -1));
        cartas.add(Pair.create(imagenes[2], -1));
        cartas.add(Pair.create(imagenes[3], -1));
        cartas.add(Pair.create(imagenes[4], -1));
        cartas.add(Pair.create(imagenes[5], -1));
        cartas.add(Pair.create(imagenes[6], -1));
        cartas.add(Pair.create(imagenes[7], -1));
        cartas.add(Pair.create(imagenes[8], -1));
        cartas.add(Pair.create(imagenes[9], -1));
        cartas.add(Pair.create(imagenes[10], -1));
        cartas.add(Pair.create(imagenes[11], -1));
        cartas.add(Pair.create(imagenes[12], -1));
        cartas.add(Pair.create(imagenes[13], -1));
        cartas.add(Pair.create(imagenes[14], -1));
        cartas.add(Pair.create(imagenes[15], -1));
        for (int k = 0; k < 16; ++k) {
            girarImagen.flipImage(getResources().getDrawable(R.drawable.estrella), cartas.get(k).first);
        }
        Arrays.fill(numeros, Boolean.FALSE);
        Random r = new Random();
        int i = 0;
        while(i < 16) {
            int n = r.nextInt(16);
            if (n % 2 == 0 && !numeros[n] && !numeros[n + 1]) { //añade la imageview a esa posicion random y pone true la primera
                cartas.set(i, Pair.create(cartas.get(i).first, n));
                //cartas[n] = imagenes[i];
                Log.v("imageview",""+cartas.get(i).first);
                Log.v("integer",""+cartas.get(i).second);
                numeros[n] = true;
                ++i;
            } else if (n % 2 == 0 && numeros[n] && !numeros[n + 1]) { //añade la imageview a esa posicion random y pone true la segunda (primera ocupada)
                cartas.set(i, Pair.create(cartas.get(i).first, n + 1));
                //cartas[n+1] = imagenes[i];
                Log.v("imageview",""+cartas.get(i).first);
                Log.v("integer",""+cartas.get(i).second);
                numeros[n + 1] = true;
                ++i;
            } else if (n % 2 != 0 && !numeros[n] && !numeros[n - 1]) {
                cartas.set(i, Pair.create(cartas.get(i).first, n - 1));
                //cartas[n-1] = imagenes[i];
                Log.v("imageview",""+cartas.get(i).first);
                Log.v("integer",""+cartas.get(i).second);
                numeros[n - 1] = true;
                ++i;
            } else if (n % 2 != 0 && !numeros[n] && numeros[n - 1]) {
                cartas.set(i, Pair.create(cartas.get(i).first, n));
                //cartas[n] = imagenes[i];
                Log.v("imageview",""+cartas.get(i).first);
                Log.v("integer",""+cartas.get(i).second);
                numeros[n] = true;
                ++i;
            }

        }
        valiente = false;
        fallos = 0;
        fallosPant.setText("" + fallos);
        acabado = 0;
        carta1 = Pair.create(-1,-1);
        carta2 = Pair.create(-1,-1);
        cartasLevantadas = 0;
    }

    public Drawable setImagen (Integer num) {
        switch(num) {
            case 0:
                return getResources().getDrawable(R.drawable.charmander1);
            case 1:
                return getResources().getDrawable(R.drawable.charmander1, null);
            case 2:
                return getResources().getDrawable(R.drawable.bulbasaur2);
            case 3:
                return getResources().getDrawable(R.drawable.bulbasaur2);
            case 4:
                return getResources().getDrawable(R.drawable.pikachu3);
            case 5:
                return getResources().getDrawable(R.drawable.pikachu3);
            case 6:
                return getResources().getDrawable(R.drawable.psyduck4);
            case 7:
                return getResources().getDrawable(R.drawable.psyduck4);
            case 8:
                return getResources().getDrawable(R.drawable.snorlax5);
            case 9:
                return getResources().getDrawable(R.drawable.snorlax5);
            case 10:
                return getResources().getDrawable(R.drawable.squirtle6);
            case 11:
                return getResources().getDrawable(R.drawable.squirtle6);
            case 12:
                return getResources().getDrawable(R.drawable.evee7);
            case 13:
                return getResources().getDrawable(R.drawable.evee7);
            case 14:
                return getResources().getDrawable(R.drawable.gengar8);
            case 15:
                return getResources().getDrawable(R.drawable.gengar8);
            case -1:
                return getResources().getDrawable(R.drawable.estrella);
            default:
                return null;
        }
    }

    public void onClick (View v) {
        switch(v.getId()) {
            case R.id.imageView:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(0, cartas.get(0).second));
                    girarImagen.flipImage(setImagen(cartas.get(0).second), cartas.get(0).first);
                }
                break;
            case R.id.imageView2:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(1, cartas.get(1).second));
                    girarImagen.flipImage(setImagen(cartas.get(1).second), cartas.get(1).first);
                }
                break;
            case R.id.imageView3:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(2, cartas.get(2).second));
                    girarImagen.flipImage(setImagen(cartas.get(2).second), cartas.get(2).first);
                }
                break;
            case R.id.imageView4:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(3, cartas.get(3).second));
                    girarImagen.flipImage(setImagen(cartas.get(3).second), cartas.get(3).first);
                }
                break;
            case R.id.imageView5:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(4, cartas.get(4).second));
                    girarImagen.flipImage(setImagen(cartas.get(4).second), cartas.get(4).first);
                }
                break;
            case R.id.imageView6:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(5, cartas.get(5).second));
                    girarImagen.flipImage(setImagen(cartas.get(5).second), cartas.get(5).first);
                }
                break;
            case R.id.imageView7:
                if (cartasLevantadas < 2)
                    gestionToque(Pair.create(6, cartas.get(6).second));
                girarImagen.flipImage(setImagen(cartas.get(6).second), cartas.get(6).first);
                break;
            case R.id.imageView8:
                if (cartasLevantadas < 2)
                    gestionToque(Pair.create(7, cartas.get(7).second));
                girarImagen.flipImage(setImagen(cartas.get(7).second), cartas.get(7).first);
                break;
            case R.id.imageView9:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(8, cartas.get(8).second));
                    girarImagen.flipImage(setImagen(cartas.get(8).second), cartas.get(8).first);
                }
                break;
            case R.id.imageView10:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(9, cartas.get(9).second));
                    girarImagen.flipImage(setImagen(cartas.get(9).second), cartas.get(9).first);
                }
                break;
            case R.id.imageView11:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(10, cartas.get(10).second));
                    girarImagen.flipImage(setImagen(cartas.get(10).second), cartas.get(10).first);
                }
                break;
            case R.id.imageView12:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(11, cartas.get(11).second));
                    girarImagen.flipImage(setImagen(cartas.get(11).second), cartas.get(11).first);
                }
                break;
            case R.id.imageView13:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(12, cartas.get(12).second));
                    girarImagen.flipImage(setImagen(cartas.get(12).second), cartas.get(12).first);
                }
                break;
            case R.id.imageView14:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(13, cartas.get(13).second));
                    girarImagen.flipImage(setImagen(cartas.get(13).second), cartas.get(13).first);
                }
                break;
            case R.id.imageView15:
                if (cartasLevantadas < 2) {
                    gestionToque(Pair.create(14, cartas.get(14).second));
                    girarImagen.flipImage(setImagen(cartas.get(14).second), cartas.get(14).first);
                }
                break;
            case R.id.imageView16:
                if (cartasLevantadas < 2)
                    gestionToque(Pair.create(15, cartas.get(15).second));
                girarImagen.flipImage(setImagen(cartas.get(15).second), cartas.get(15).first);
                break;
        }
        Log.v("cartaslevantadas", cartasLevantadas + "");

    }

    public void gestionToque(Pair<Integer,Integer> p) {
        if (carta1.first != p.first) {
            ++cartasLevantadas;
            if (carta1.first == -1) carta1 = Pair.create(p.first,p.second);
            else carta2 = Pair.create(p.first,p.second);
            Log.v("carat1", carta1.first + " " + carta1.second + "");
            Log.v("carat2",carta2.first+" "+carta2.second+"");
            valiente = !valiente;
            if (valiente) {
                MyTask task = new MyTask();
                task.execute();

            }
        }
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... param) {
            if (valiente) {
                while (valiente) ;
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "proceso finalizado: ";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("cartaslevantadas", cartasLevantadas + "");
            if ((carta1.second%2 == 0 && carta2.second == carta1.second+1) || (carta1.second%2 != 0 && carta1.second-1 == carta2.second)) {
                girarImagen.flipImage(getResources().getDrawable(R.drawable.tranparente), cartas.get(carta1.first).first);
                girarImagen.flipImage(getResources().getDrawable(R.drawable.tranparente), cartas.get(carta2.first).first);
                cartas.get(carta1.first).first.setEnabled(false);
                cartas.get(carta2.first).first.setEnabled(false);
                acabado += 2;
                if (acabado == 16) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Enhorabuena");
                    builder.setMessage("¿Qué quieres hacer ahora?");

                    builder.setPositiveButton("Nueva Partida", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Nueva Partida", Toast.LENGTH_LONG).show();
                            generarTablero();
                        }
                    });
                    builder.setNegativeButton("Volver al menú", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Volver al menú", Toast.LENGTH_LONG).show();
                            if (mListener != null) mListener.onFragmentInteraction("end", 1);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    for (int k = 0; k < 16; ++k) {
                        cartas.get(k).first.setEnabled(true);
                    }
                }
            }
            else {
                girarImagen.flipImage(getResources().getDrawable(R.drawable.estrella),cartas.get(carta1.first).first);
                girarImagen.flipImage(getResources().getDrawable(R.drawable.estrella), cartas.get(carta2.first).first);
            }
            ++fallos;
            fallosPant.setText("" + fallos);
            carta1 = Pair.create(-1,-1);
            carta2 = Pair.create(-1,-1);
            cartasLevantadas = 0;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.memory_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.replay:
                generarTablero();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
