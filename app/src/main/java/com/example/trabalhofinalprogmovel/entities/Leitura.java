package com.example.trabalhofinalprogmovel.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.trabalhofinalprogmovel.database.LocalDatabase;

@Entity(foreignKeys = {@ForeignKey(entity = Leitor.class, parentColumns = "leitorId",
        childColumns = "leitorId", onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Livro.class, parentColumns = "livroId",
        childColumns = "livroId", onDelete = ForeignKey.CASCADE)})
public class Leitura {
    @PrimaryKey(autoGenerate = true)
    private int leituraId;
    private int leitorId;
    private int livroId;
    private String comentario;
    private int nota;
    private String titulo;

    public Leitura() {
    }

    public Leitura(int leitorId, int livroId, String titulo, String comentario, int nota) {
        this.leitorId = leitorId;
        this.livroId = livroId;
        this.titulo = titulo;
        this.comentario = comentario;
        this.nota = nota;
    }

    public int getLeituraId() {
        return leituraId;
    }

    public void setLeituraId(int leituraId) {
        this.leituraId = leituraId;
    }

    public int getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(int leitorId) {
        this.leitorId = leitorId;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return getTitulo() + " - Nota: " + getNota();
    }
}
