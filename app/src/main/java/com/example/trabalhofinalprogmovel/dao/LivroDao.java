package com.example.trabalhofinalprogmovel.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.trabalhofinalprogmovel.entities.Leitura;
import com.example.trabalhofinalprogmovel.entities.Livro;

import java.util.List;

@Dao
public interface LivroDao {
    @Query("SELECT * FROM Livro WHERE livroId = :id LIMIT 1")
    Livro getLivro(int id);

    @Query("SELECT * FROM Livro")
    List<Livro> getAll();

    @Query("SELECT DISTINCT genero FROM Livro")
    List<Livro> getLivroGenero();

    @Insert
    void insertAll(Livro... livro);

    @Update
    void update(Livro livro);

    @Delete
    void delete(Livro livro);
}
