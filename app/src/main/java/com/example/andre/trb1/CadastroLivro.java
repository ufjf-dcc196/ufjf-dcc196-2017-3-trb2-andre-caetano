package com.example.andre.trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.andre.trb1.dbhelper.LivroHelper;

public class CadastroLivro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_livro);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarLivro();
            }
        });
    }

    private void cadastrarLivro() {
        Livro livro = new Livro();

        EditText etTitulo = (EditText) findViewById(R.id.et_titulo);
        EditText etEditora = (EditText) findViewById(R.id.et_editora);
        EditText etAnoPublicacao = (EditText) findViewById(R.id.et_ano_publicacao);

        livro.setTitulo(etTitulo.getText().toString());
        livro.setEditora(etEditora.getText().toString());
        if(!etAnoPublicacao.getText().toString().equals("")) {
            livro.setAnoPublicacao(Integer.parseInt(etAnoPublicacao.getText().toString()));
        }

        LivroHelper livroHelper = new LivroHelper(getApplicationContext());
        livroHelper.inserirLivro(livro);

        Toast.makeText(getBaseContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
