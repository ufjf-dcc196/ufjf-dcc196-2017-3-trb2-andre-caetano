package com.example.andre.trb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastroParticipante = (Button) findViewById(R.id.btn_cadastro_participante);
        Button btnCadastroLivro = (Button) findViewById(R.id.btn_cadastro_livro);
        Button btnCadastroReserva = (Button) findViewById(R.id.btn_cadastro_reserva);
        Button btnListarLivros = (Button) findViewById(R.id.btn_listar_livros);

        setOnClickForResult(btnCadastroParticipante, CadastroParticipante.class, 1);
        setOnClickForResult(btnCadastroLivro, CadastroLivro.class, 2);
        btnCadastroReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CadastroReserva.class);
                intent.putParcelableArrayListExtra("PARTICIPANTES", participantes);
                intent.putParcelableArrayListExtra("LIVROS", livros);
                startActivity(intent);
            }
        });
        btnListarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListaLivros.class);
                intent.putParcelableArrayListExtra("LIVROS", livros);
                startActivity(intent);
            }
        });

        participantes.add(new Participante("André Caetano Vidal", "andrecvidal@hotmail.com"));
        participantes.add(new Participante("Igor Knop", null));
        livros.add(new Livro("O Guia do Mochileiro das Galáxias", "Arqueiro", 2010));

        final ListView listaParticipantes = (ListView) findViewById(R.id.list_participantes);
        ArrayAdapter<Participante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participantes);
        listaParticipantes.setAdapter(adapter);

        listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = (Participante) listaParticipantes.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), DadosParticipante.class);
                intent.putExtra("PARTICIPANTE", participante);
                startActivity(intent);
            }
        });
        listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = (Participante) listaParticipantes.getItemAtPosition(position);
                int index = participantes.indexOf(participante);
                if( participantes.get(index).getHoraEntrada() == null
                        && participantes.get(index).getHoraSaida() == null) {
                    participantes.get(index).setHoraEntrada(Calendar.getInstance().getTime());
                }
                else if(participantes.get(index).getHoraSaida() == null){
                    participantes.get(index).setHoraSaida(Calendar.getInstance().getTime());
                }
                else{
                    participantes.get(index).setHoraEntrada(null);
                    participantes.get(index).setHoraSaida(null);
                }
                return false;
            }
        });
    }

    private void setOnClickForResult(final Button btn, final Class activity, final int requestCode){
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
        }
    }
}
