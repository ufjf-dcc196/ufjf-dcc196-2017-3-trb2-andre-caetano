package com.example.andre.trb1.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.andre.trb1.Livro;
import java.util.ArrayList;

public class LivroHelper extends FeiraDBHelper {

    public LivroHelper(Context context) {
        super(context);
    }

    public void inserirLivro(Livro livro) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put(FeiraContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
        dados.put(FeiraContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
        dados.put(FeiraContract.Livro.COLUMN_NAME_ANO_PUBLICACAO, livro.getAnoPublicacao());

        db.insert(FeiraContract.Livro.TABLE_NAME, null, dados);
    }

    public ArrayList<Livro> buscarLivros() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FeiraContract.Livro.SQL_SELECT_LIVRO, null);

        ArrayList<Livro> livros = new ArrayList<>();
        while(cursor.moveToNext()) {
            Livro livro = new Livro();

            livro.setId(cursor.getLong(cursor.getColumnIndex(FeiraContract.Livro._ID)));
            livro.setTitulo(cursor.getString(cursor.getColumnIndex(FeiraContract.Livro.COLUMN_NAME_TITULO)));
            livro.setEditora(cursor.getString(cursor.getColumnIndex(FeiraContract.Livro.COLUMN_NAME_EDITORA)));
            livro.setAnoPublicacao(cursor.getInt(cursor.getColumnIndex(FeiraContract.Livro.COLUMN_NAME_ANO_PUBLICACAO)));

            livros.add(livro);
        }
        cursor.close();

        return livros;
    }
}