package com.example.andre.trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaLivros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);

        ArrayList<Livro> livros = new ArrayList<>();

        if(getIntent().getParcelableArrayListExtra("LIVROS") != null){
            livros = getIntent().getParcelableArrayListExtra("LIVROS");
        }

        ListView listaLivros = (ListView) findViewById(R.id.list_livros);
        ArrayAdapter<Livro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);
        listaLivros.setAdapter(adapter);
    }
}
