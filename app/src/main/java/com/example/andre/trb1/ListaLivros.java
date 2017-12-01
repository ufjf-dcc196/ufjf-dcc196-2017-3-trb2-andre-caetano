package com.example.andre.trb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andre.trb1.dbhelper.LivroHelper;

import java.util.ArrayList;

public class ListaLivros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);

        LivroHelper livroHelper = new LivroHelper(getApplicationContext());
        ArrayList<Livro> livros = livroHelper.buscarLivros();

        final ListView listaLivros = (ListView) findViewById(R.id.list_livros);
        ArrayAdapter<Livro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);
        listaLivros.setAdapter(adapter);

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livro = (Livro) listaLivros.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), DadosLivro.class);
                intent.putExtra("LIVRO", livro);
                startActivity(intent);
            }
        });
    }
}
