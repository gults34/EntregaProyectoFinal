package com.example.andres.proyectofinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder> {

    ArrayList<UserRecyclerView> ranking;
    private UserHelper userHelper;
    MyCustomAdapter(ArrayList<UserRecyclerView> lista){
        ranking = lista;
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        if (ranking.get(position).getIcon().equals("-1")) {
            adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.icon0));
        }
        else adapterViewholder.icon.setImageURI(Uri.parse(ranking.get(position).getIcon()));
        if (ranking.get(position).getPunt().toString().equals("-1")) {
            adapterViewholder.punt.setText("N/A");
        }
        else adapterViewholder.punt.setText(ranking.get(position).getPunt().toString());
        adapterViewholder.name.setText(ranking.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView name;
        public TextView punt;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.punt = (TextView) itemView.findViewById(R.id.punt);
        }
    }
}
