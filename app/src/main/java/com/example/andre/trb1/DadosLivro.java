package com.example.andre.trb1;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DadosLivro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_livro);

        Livro livro = new Livro(Parcel.obtain());

        if(getIntent().getParcelableExtra("LIVRO") != null){
            livro = getIntent().getParcelableExtra("LIVRO");
        }

        TextView titulo = (TextView) findViewById(R.id.text_titulo_livro);
        TextView editora = (TextView) findViewById(R.id.text_editora_livro);
        TextView ano_de_publicacao = (TextView) findViewById(R.id.text_ano_livro);

        titulo.setText(livro.toString());
        editora.setText(livro.getEditora());
        ano_de_publicacao.setText(String.valueOf(livro.getAnoPublicacao()));

        ListView listaReservas = (ListView) findViewById(R.id.list_reservas);
        ArrayAdapter<Participante> adapter = new ArrayAdapter<>(
                getBaseContext(),
                android.R.layout.simple_list_item_1,
                livro.getParticipantes());
        listaReservas.setAdapter(adapter);
    }
}
