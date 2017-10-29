package com.example.andre.trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);

        ArrayList<Participante> participantes = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();

        if(getIntent().getParcelableArrayListExtra("PARTICIPANTES") != null) {
            participantes = getIntent().getParcelableArrayListExtra("PARTICIPANTES");
        }
        if(getIntent().getParcelableArrayListExtra("LIVROS") != null) {
            livros = getIntent().getParcelableArrayListExtra("LIVROS");
        }

        final Spinner listaParticipantes = (Spinner) findViewById(R.id.spinner_participantes);
        ArrayAdapter<Participante> adapterParticipantes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participantes);
        listaParticipantes.setAdapter(adapterParticipantes);

        final Spinner listaLivros = (Spinner) findViewById(R.id.spinner_livros);
        ArrayAdapter<Livro> adapterLivro = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);
        listaLivros.setAdapter(adapterLivro);

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_reserva);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarReserva(listaParticipantes, listaLivros);
            }
        });
    }

    private void cadastrarReserva(Spinner listaParticipantes, Spinner listaLivros) {
        Participante participante = (Participante) listaParticipantes.getSelectedItem();
        Livro livro = (Livro) listaLivros.getSelectedItem();
        livro.addParticipante(participante);

        Toast.makeText(getBaseContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
