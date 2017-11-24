package com.example.andre.trb1;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DadosParticipante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_participante);

        Participante participante = new Participante(Parcel.obtain());

        if(getIntent().getParcelableExtra("PARTICIPANTE") != null) {
            participante = getIntent().getParcelableExtra("PARTICIPANTE");
        }

        TextView nome = (TextView) findViewById(R.id.text_nome_participante);
        TextView email = (TextView) findViewById(R.id.text_email_participante);
        TextView horarioEntrada = (TextView) findViewById(R.id.text_entrada_participante);
        TextView horarioSaida = (TextView) findViewById(R.id.text_saida_participante);

        nome.setText(participante.toString());
        email.setText(participante.getEmail());
        horarioEntrada.setText(participante.getHoraEntrada());
        horarioSaida.setText(participante.getHoraSaida());
    }
}
