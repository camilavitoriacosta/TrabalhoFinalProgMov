package com.example.trabalhofinalprogmovel.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Leitor {
    @PrimaryKey(autoGenerate = true)
    private int leitorId;

    private String nome;
    private String email;
    private String senha;

    public Leitor() {
    }

    public Leitor(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(int leitorId) {
        this.leitorId = leitorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
