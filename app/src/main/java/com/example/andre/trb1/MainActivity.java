package com.example.andre.trb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // lendo botões
        Button btnCadastroParticipante = (Button) findViewById(R.id.btn_cadastro_participante);
        Button btnCadastroLivro = (Button) findViewById(R.id.btn_cadastro_livro);
        Button btnCadastroReserva = (Button) findViewById(R.id.btn_cadastro_reserva);

        // tratando clicks nos botões
        setOnClick(btnCadastroParticipante, CadastroParticipante.class, 1);
        setOnClick(btnCadastroLivro, CadastroLivro.class, 2);
        setOnClick(btnCadastroReserva, CadastroReserva.class, 3);

        // adicionando dados iniciais
        participantes.add(new Participante("André Caetano Vidal", "andrecvidal@hotmail.com"));
        participantes.add(new Participante("Igor Knop", null));
        livros.add(new Livro("O Guia do Mochileiro das Galáxias", "Arqueiro", 2010));

        // criando a lista de participantes
        ListView listaParticipantes = (ListView) findViewById(R.id.list_participantes);
        ArrayAdapter<Participante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participantes);
        listaParticipantes.setAdapter(adapter);

        // tratando clicks na lista
        listaParticipantes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        listaParticipantes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    // função para tratar os clicks
    private void setOnClick(final Button btn, final Class activity, final int requestCode){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), activity);
                if(requestCode == 3){
                    intent.putParcelableArrayListExtra("PARTICIPANTES", participantes);
                    intent.putParcelableArrayListExtra("LIVROS", livros);
                }
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == 1){
                Participante novoParticipante = data.getParcelableExtra("PARTICIPANTE");
                participantes.add(novoParticipante);
            }
            if(requestCode == 2){
                Livro novoLivro = data.getParcelableExtra("LIVRO");
                livros.add(novoLivro);
            }
            if(requestCode == 3){
                Reserva novaReserva = data.getParcelableExtra("RESERVA");
                reservas.add(novaReserva);
            }
        }
    }
}
