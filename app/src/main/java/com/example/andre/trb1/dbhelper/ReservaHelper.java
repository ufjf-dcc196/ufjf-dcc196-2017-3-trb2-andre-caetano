package com.example.andre.trb1.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import com.example.andre.trb1.Livro;
import com.example.andre.trb1.Participante;
import java.util.ArrayList;

public class ReservaHelper extends FeiraDBHelper {

    public ReservaHelper(Context context) {
        super(context);
    }

    public void inserirReserva(Participante participante, Livro livro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(FeiraContract.Reserva.COLUMN_NAME_PARTICIPANTE, participante.getId());
        dados.put(FeiraContract.Reserva.COLUMN_NAME_LIVRO, livro.getId());

        db.insert(FeiraContract.Reserva.TABLE_NAME, null, dados);
    }

    public ArrayList<Participante> buscarReservas(Livro livro) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+FeiraContract.Reserva.TABLE_NAME+
                " WHERE "+FeiraContract.Reserva.COLUMN_NAME_LIVRO+"="+livro.getId();
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<Participante> participantes = new ArrayList<>();
        while(cursor.moveToNext()) {
            Participante participante = new Participante(Parcel.obtain());

            participante.setId(cursor.getInt(cursor.getColumnIndex(FeiraContract.Participante._ID)));
            participante.setNome(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_NOME)));
            participante.setEmail(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_EMAIL)));
            participante.setHoraEntrada(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_HORA_ENTRADA)));
            participante.setHoraSaida(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_HORA_SAIDA)));

            participantes.add(participante);
        }
        cursor.close();

        return participantes;
    }
}
