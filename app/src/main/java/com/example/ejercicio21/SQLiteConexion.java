package com.example.ejercicio21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ejercicio21.transacciones.transacciones;

public class SQLiteConexion extends SQLiteOpenHelper
{
    public SQLiteConexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("SQLiteConexion", "onCreate: " + transacciones.CreateTableVideo);
        db.execSQL(transacciones.CreateTableVideo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(transacciones.DropTableVideo);
        onCreate(db);
    }
}
