package com.example.andre.trb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.andre.trb1.dbhelper.ParticipanteHelper;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastroParticipante = (Button) findViewById(R.id.btn_cadastro_participante);
        Button btnCadastroLivro = (Button) findViewById(R.id.btn_cadastro_livro);
        Button btnCadastroReserva = (Button) findViewById(R.id.btn_cadastro_reserva);
        Button btnListarLivros = (Button) findViewById(R.id.btn_listar_livros);


        setOnClick(btnCadastroParticipante, CadastroParticipante.class);
        setOnClick(btnCadastroLivro, CadastroLivro.class);
        setOnClick(btnCadastroReserva, CadastroReserva.class);
        setOnClick(btnListarLivros, ListaLivros.class);

        final ListView listaParticipantes = (ListView) findViewById(R.id.list_participantes);
        listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = (Participante) listaParticipantes.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), DadosParticipante.class);
                intent.putExtra("PARTICIPANTE", participante);
                startActivity(intent);
            }
        });
        /*listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Participante participante = (Participante) listaParticipantes.getItemAtPosition(position);
                int index = participantes.indexOf(participante);
                participantes.get(index).registraHora(new Date());
                return true;
            }
        })*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        ParticipanteHelper participanteHelper = new ParticipanteHelper(getApplicationContext());
        ArrayList<Participante> participantes = participanteHelper.buscarParticipantes();

        ListView listaParticipantes = (ListView) findViewById(R.id.list_participantes);
        ArrayAdapter<Participante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participantes);
        listaParticipantes.setAdapter(adapter);
    }

    private void setOnClick(final Button btn, final Class activity){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), activity);
                startActivity(intent);
            }
        });
    }
}
