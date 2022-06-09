package com.example.projeto_final_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView listCadastro;
    private CadastroDAO cadastroDAO;
    private MenuItem icCadastrar;
    private SearchView icConsultar;
    private List<Cadastro> todosCadastro;
    private List<Cadastro> cadastroFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        getSupportActionBar().hide();

        listCadastro = findViewById(R.id.listCadastro);
        cadastroDAO = new CadastroDAO(this);

        todosCadastro = cadastroDAO.consultarServicoBD();
        cadastroFiltrados.addAll(todosCadastro);

        ArrayAdapter<Cadastro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cadastroFiltrados);
        listCadastro.setAdapter(adapter);

        registerForContextMenu(listCadastro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_pric, menu);
        icConsultar = (SearchView) menu.findItem(R.id.icConsultar).getActionView();
        icConsultar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarCadastroPorNome(s);
                return false;
            }
        });
        return true;

    }

    public void procurarCadastroPorNome(String nome) {
        cadastroFiltrados.clear();
        for (int i = 0; i < todosCadastro.size(); i++) {
            if (todosCadastro.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
                cadastroFiltrados.add(todosCadastro.get(i));
            }
        }
        listCadastro.invalidateViews();
    }
    public void abrirTelaCadastro(MenuItem item){
        Intent i = new Intent(ListaActivity.this,MainActivity.class);
        startActivity(i);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_contexto,menu);
    }

    public void excluirCadastro(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Cadastro cadastroDeletado = cadastroFiltrados.get(menuInfo.position);

        AlertDialog confirmacao = new AlertDialog.Builder(this).setIcon(R.drawable.ic_atencao)
                .setTitle("Atenção!")
                .setMessage("Deseja excluir realmente esse serviço?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cadastroDAO.deletarCadastro(cadastroDeletado);
                        cadastroFiltrados.remove(cadastroDeletado);
                        todosCadastro.remove(cadastroDeletado);

                        listCadastro.invalidateViews();
                    }
                }).create();
        confirmacao.show();

    }

    public void alterarCadastro(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        Cadastro cadastroAlterado = cadastroFiltrados.get(menuInfo.position);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("cadastro", cadastroAlterado);
        startActivity(i);
    }
}