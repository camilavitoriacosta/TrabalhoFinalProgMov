package com.example.trabalhofinalprogmovel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalhofinalprogmovel.entities.Leitor;
import com.example.trabalhofinalprogmovel.entities.Livro;

import java.util.List;

@Dao
public interface LeitorDao {
    @Query("SELECT * FROM Leitor WHERE leitorId = :id LIMIT 1")
    Livro getLeitor(int id);

    @Query("SELECT * FROM Leitor")
    List<Leitor> getAll();

    @Insert
    void insertAll(Leitor... leitor);

    @Update
    void update(Leitor leitor);

    @Delete
    void delete(Leitor leitor);
}
