package com.example.andres.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHelper extends SQLiteOpenHelper {


    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "bd";

    //Declaracion del nombre de la tabla
    public static final String USER_TABLE ="User";

    //sentencia global de cracion de la base de datos
    public static final String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE + " (username TEXT PRIMARY KEY UNIQUE, foto TEXT, pass TEXT, punt INTEGER, noti INTEGER);";



    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username,pass,punt,foto"};
        Cursor c = db.query(
                USER_TABLE,                             // The table to query
                columns,                                // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        return c;
    }

    public Cursor getUserPunt() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username,punt,foto"};
        Cursor c = db.query(
                USER_TABLE,                             // The table to query
                columns,                                // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        return c;
    }

    public Cursor getFotoByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"foto"};
        String[] where = {username};
        Cursor c = db.query(
                USER_TABLE,                                 // The table to query
                columns,                                    // The columns to return
                "username=?",                               // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    public Cursor getPassByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"pass"};
        String[] where = {username};
        Cursor c = db.query(
                USER_TABLE,                                 // The table to query
                columns,                                    // The columns to return
                "username=?",                               // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    public Cursor getNotByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"noti"};
        String[] where = {username};
        Cursor c = db.query(
                USER_TABLE,                                 // The table to query
                columns,                                    // The columns to return
                "username=?",                               // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"pass,foto,punt"};
        String[] where = {username};
        Cursor c = db.query(
                USER_TABLE,                                 // The table to query
                columns,                                    // The columns to return
                "username=?",                               // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    public Cursor existsUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username"};
        String[] where = {username};
        Cursor v = db.query(
                USER_TABLE,                                  // The table to query
                columns,                                    // The columns to return
                "username=?",                                   // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return v;
    }

    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DELETE FROM User", null);
    }

    public long cambiarContraseña(String username, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {username};
        long p = db.update(
                USER_TABLE,                                   // The table to query
                contentValues,
                "Username=?",                               // The columns for the WHERE clause
                where                                       // The values for the WHERE clause
        );
        return p;
    }

    public long setPoints(String username, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {username};
        long p = db.update(
                USER_TABLE,                                   // The table to query
                contentValues,
                "Username=?",                               // The columns for the WHERE clause
                where                                       // The values for the WHERE clause
        );
        return p;
    }
    public long setNotificationMode(String username, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {username};
        long p = db.update(
                USER_TABLE,                                   // The table to query
                contentValues,
                "Username=?",                               // The columns for the WHERE clause
                where                                       // The values for the WHERE clause
        );
        return p;
    }


    public long addFoto(String username, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {username};
        long p = db.update(
                USER_TABLE,                                   // The table to query
                contentValues,
                "Username=?",                               // The columns for the WHERE clause
                where                                       // The values for the WHERE clause
        );
        return p;
    }
}
