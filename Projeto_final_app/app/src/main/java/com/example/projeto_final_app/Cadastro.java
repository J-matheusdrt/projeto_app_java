package com.example.projeto_final_app;

import java.io.Serializable;

public class Cadastro implements Serializable {

    private int id;
    private String nome;
    private String dia;
    private String servico;
    private float preco;

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "\n" +
                "\nId:" + id +
                "\nNome Cliente: " + nome  +
                "\nData do Serviço: " + dia  +
                " \nTipo do Serviço: " + servico +
                " \nPrecinho cobrado: "+ preco;

    }
}
