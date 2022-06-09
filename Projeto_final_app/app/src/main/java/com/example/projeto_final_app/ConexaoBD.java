package com.example.projeto_final_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final   String name = "bd_cadastro";
    private static final int version = 1;

    public ConexaoBD(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd_cadastro) {
        bd_cadastro.execSQL("CREATE TABLE tb_cadastro(id Integer not null primary key autoincrement,"+
               "nome varchar(100) not null,dia varchar(15), servico varchar(200),preco float(7,2)) " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
