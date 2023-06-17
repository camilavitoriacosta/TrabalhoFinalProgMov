package com.example.trabalhofinalprogmovel.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Leitor.class, parentColumns = "leitorId",
        childColumns = "leitorId", onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Livro.class, parentColumns = "livroId",
        childColumns = "livroId", onDelete = ForeignKey.CASCADE)})
public class Desejo {
    @PrimaryKey(autoGenerate = true)
    private int desejoId;
    private int leitorId;
    private int livroId;

    public Desejo() {
    }

    public Desejo(int leitorId, int livroId) {
        this.leitorId = leitorId;
        this.livroId = livroId;
    }

    public int getDesejoId() {
        return desejoId;
    }

    public void setDesejoId(int desejoId) {
        this.desejoId = desejoId;
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
}
