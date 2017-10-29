package com.example.andre.trb1;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroParticipante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarParticipante();
            }
        });
    }

    private void cadastrarParticipante(){
        Participante participante = new Participante(Parcel.obtain());

        EditText etNome = (EditText) findViewById(R.id.et_nome);
        EditText etEmail = (EditText) findViewById(R.id.et_email);

        participante.setNome(etNome.getText().toString());
        participante.setEmail(etEmail.getText().toString());

        Toast.makeText(getBaseContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("PARTICIPANTE", participante);
        setResult(RESULT_OK, intent);
        finish();
    }
}
