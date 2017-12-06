package com.example.andre.trb1.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.andre.trb1.Participante;
import java.util.ArrayList;

public class ParticipanteHelper extends FeiraDBHelper {

    public ParticipanteHelper(Context context) {
        super(context);
    }

    public void inserirParticipante(Participante participante) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put(FeiraContract.Participante.COLUMN_NAME_NOME, participante.getNome());
        dados.put(FeiraContract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());
        dados.put(FeiraContract.Participante.COLUMN_NAME_HORA_ENTRADA, participante.getHoraEntrada());
        dados.put(FeiraContract.Participante.COLUMN_NAME_HORA_SAIDA, participante.getHoraSaida());

        db.insert(FeiraContract.Participante.TABLE_NAME, null, dados);
    }

    public ArrayList<Participante> buscarParticipantes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FeiraContract.Participante.SQL_SELECT_PARTICIPANTE, null);

        ArrayList<Participante> participantes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Participante participante = new Participante();

            participante.setId(cursor.getLong(cursor.getColumnIndex(FeiraContract.Participante._ID)));
            participante.setNome(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_NOME)));
            participante.setEmail(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_EMAIL)));
            participante.setHoraEntrada(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_HORA_ENTRADA)));
            participante.setHoraSaida(cursor.getString(cursor.getColumnIndex(FeiraContract.Participante.COLUMN_NAME_HORA_SAIDA)));

            participantes.add(participante);
        }
        cursor.close();

        return participantes;
    }

    public void atualizarHora(Participante participante, String horaEntrada, String horaSaida){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put(FeiraContract.Participante.COLUMN_NAME_HORA_ENTRADA, horaEntrada);
        dados.put(FeiraContract.Participante.COLUMN_NAME_HORA_SAIDA, horaSaida);

        String[] id = {participante.getId().toString()};
        db.update(FeiraContract.Participante.TABLE_NAME, dados, FeiraContract.Participante._ID+"= ?", id);
    }
}
