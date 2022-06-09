package com.example.projeto_final_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    SQLiteDatabase bd_cadastro;
    private ConexaoBD conexaoBD;

    public CadastroDAO(Context context){
        conexaoBD = new ConexaoBD(context);
        bd_cadastro = conexaoBD.getWritableDatabase();
    }
    private ContentValues gerarValores(Cadastro objCadastro){
        ContentValues values = new ContentValues();
        values.put("nome", objCadastro.getNome());
        values.put("dia", objCadastro.getDia());
        values.put("servico", objCadastro.getServico());
        values.put("preco", objCadastro.getPreco());

       return values;


    }
    public void cadastrarServico(Cadastro objCadastro){
       this.bd_cadastro.insert("tb_cadastro",null,this.gerarValores(objCadastro));
    }
    public List<Cadastro> consultarServicoBD(){
        List<Cadastro> listaCadastro = new ArrayList<>();

        String [] todosOsCampos = {"*"};

        Cursor cursor = this.bd_cadastro.query("tb_cadastro",todosOsCampos,
             null,null,null,null, null  );

        while (cursor.moveToNext()){
            Cadastro cadastro = new Cadastro();

            cadastro.setId(cursor.getInt(0));
            cadastro.setNome(cursor.getString(1));
            cadastro.setDia(cursor.getString(2));
            cadastro.setServico(cursor.getString(3));
            cadastro.setPreco(cursor.getFloat(4));


            listaCadastro.add(cadastro);
        }
        return  listaCadastro;
    }
    public void deletarCadastro(Cadastro cadastro){
        this.bd_cadastro.delete("tb_cadastro","ID = ?",new String[]{String.valueOf(cadastro.getId())});
    }

    public void alterarCadastro(Cadastro cadastro){
        this.bd_cadastro.update("tb_cadastro",gerarValores(cadastro),"id = ?",new String[]{String.valueOf(cadastro.getId())});
    }

}
