package com.example.trabalhofinalprogmovel.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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

    public Leitura() {
    }

    public Leitura(int leitorId, int livroId, String comentario, int nota) {
        this.leitorId = leitorId;
        this.livroId = livroId;
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
}
