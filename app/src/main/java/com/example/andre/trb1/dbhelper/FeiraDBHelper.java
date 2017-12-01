package com.example.andre.trb1.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FeiraDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "feira.db";
    private static final int DATABASE_VERSION = 1;

    FeiraDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeiraContract.Participante.SQL_CREATE_PARTICIPANTE);
        db.execSQL(FeiraContract.Livro.SQL_CREATE_LIVRO);
        db.execSQL(FeiraContract.Reserva.SQL_CREATE_RESERVA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FeiraContract.Participante.SQL_DROP_PARTICIPANTE);
        db.execSQL(FeiraContract.Livro.SQL_DROP_LIVRO);
        db.execSQL(FeiraContract.Reserva.SQL_DROP_RESERVA);
        onCreate(db);
    }
}