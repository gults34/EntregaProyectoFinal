package com.example.andres.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class RankingFragment extends Fragment {

    private FrameLayout frameLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private UserHelper userHelper;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        frameLayout = (FrameLayout) rootView.findViewById(R.id.frameLayout);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.mRecyclerView);

        mLinearLayout = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLinearLayout);

        mRecyclerView.setAdapter(new MyCustomAdapter(rellena()));

        return rootView;
    }

    public ArrayList<UserRecyclerView> rellena () {
        ArrayList<UserRecyclerView> ranking = new ArrayList<>();
        userHelper = new UserHelper(getContext());
        Cursor c = userHelper.getUserPunt();
        /*if (c.moveToFirst()) {
            do {
                ranking.add(new UserRecyclerView(c.getString((c.getColumnIndex("foto"))), c.getString((c.getColumnIndex("username"))), c.getInt(c.getColumnIndex("punt"))));
            }while (c.moveToNext());
        }*/

        c.moveToFirst();
        int i = 0;
        while (!c.isAfterLast()) {
            if (c.getInt(c.getColumnIndex("punt")) == -1) ranking.add(new UserRecyclerView(c.getString((c.getColumnIndex("foto"))), c.getString((c.getColumnIndex("username"))), c.getInt(c.getColumnIndex("punt"))));
            else {
                ranking.add(i, new UserRecyclerView(c.getString((c.getColumnIndex("foto"))), c.getString((c.getColumnIndex("username"))), c.getInt(c.getColumnIndex("punt"))));
                ++i;
            }
            c.moveToNext();
        }


        return ranking;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mListener.onFragmentInteraction("logout", 3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
