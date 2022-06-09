 package com.example.projeto_final_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

    private EditText edtNome,edtDia,edtServico,edtpreco;
    private Button btnCadastrar,btnFicha;
    private CadastroDAO cadastrarDAO;
    private Cadastro objCadastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtDia = findViewById(R.id.edtData);
        edtServico = findViewById(R.id.edtTipoServico);
        edtpreco = findViewById(R.id.edtPreco);
        btnCadastrar = findViewById(R.id.btnListaDeCadastro);
        btnFicha = findViewById(R.id.btnFicha);

        cadastrarDAO = new CadastroDAO(this);

        Intent i = getIntent();
        if(i.hasExtra("cadastro")){
            objCadastro = (Cadastro) i.getSerializableExtra("cadastro");
            edtNome.setText(objCadastro.getNome());
            edtDia.setText(objCadastro.getDia());
            edtServico.setText(objCadastro.getServico());
            edtpreco.setText(String.valueOf(objCadastro.getPreco()));
            btnCadastrar.setText("alterar");

        }
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objCadastro == null ){
                    if(!edtNome.getText().toString().isEmpty() && !edtDia.getText().toString().isEmpty() && !edtpreco.getText().toString().isEmpty() && !edtServico.getText().toString().isEmpty() ) {
                        Cadastro objCadastro = new Cadastro();
                        objCadastro.setNome(edtNome.getText().toString());
                        objCadastro.setDia(edtDia.getText().toString());
                        objCadastro.setServico(edtServico.getText().toString());
                        objCadastro.setPreco(Float.parseFloat(edtpreco.getText().toString()));
                        cadastrarDAO.cadastrarServico(objCadastro);
                        Toast.makeText(MainActivity.this, "Cadastrado com sucesso ", Toast.LENGTH_SHORT).show();
                    }

               }else{
                   objCadastro.setNome(edtNome.getText().toString());
                   objCadastro.setDia(edtDia.getText().toString());
                   objCadastro.setServico(edtServico.getText().toString());
                   objCadastro.setPreco(Float.parseFloat(edtpreco.getText().toString()));
                   cadastrarDAO.alterarCadastro(objCadastro);
                   Toast.makeText(MainActivity.this, "Editado com sucesso ", Toast.LENGTH_SHORT).show();

               }
               edtNome.setText("");
               edtpreco.setText("");
               edtDia.setText("");
               edtServico.setText("");
               edtNome.requestFocus();
            }
        });
        btnFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ListaActivity.class);
                startActivity(i);
            }
        });
    }
}