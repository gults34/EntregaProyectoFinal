package com.example.andres.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class RankingFragment extends Fragment {

    private FrameLayout frameLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private UserHelper userHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if (c.moveToFirst()) {
            do {
                ranking.add(new UserRecyclerView(0, c.getString((c.getColumnIndex("username"))), c.getInt(c.getColumnIndex("punt"))));
            }while (c.moveToNext());
        }
        return ranking;
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
