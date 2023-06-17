package com.example.trabalhofinalprogmovel.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Livro {
    @PrimaryKey(autoGenerate = true)
    private int livroId;
    private String titulo;
    private String genero;
    private int quantidadeDePaginas;

    public Livro() {

    }

    public Livro(String titulo, String genero, int quantidadeDePaginas) {
        this.titulo = titulo;
        this.genero = genero;
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(int quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    @Override
    public String toString() {
        return getTitulo();
    }
}
