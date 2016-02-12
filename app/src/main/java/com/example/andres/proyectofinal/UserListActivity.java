package com.example.andres.proyectofinal;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class UserListActivity extends ListActivity {

    private ArrayList<String> listItems=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);

        userHelper = new UserHelper(getApplicationContext());

        Cursor c = userHelper.getAllUsers();
        if (c.moveToFirst()) {
            do {
                adapter.add("Username: " + c.getString(c.getColumnIndex("username")) + "/Pass:" + c.getString(c.getColumnIndex("pass")) + "/Punt:" + c.getInt(c.getColumnIndex("punt")) + "Foto:" + c.getString(c.getColumnIndex("foto")));
            } while (c.moveToNext());
        }
    }
}

