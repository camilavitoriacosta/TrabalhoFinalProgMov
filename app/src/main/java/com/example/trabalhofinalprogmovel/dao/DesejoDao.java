package com.example.trabalhofinalprogmovel.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitura;

import java.util.List;

@Dao
public interface DesejoDao {
    @Query("SELECT * FROM Desejo WHERE desejoId = :id LIMIT 1")
    Desejo getDesejoPorId(int id);

    @Query("SELECT * FROM Desejo WHERE leitorId = :idLeitor")
    List<Desejo> getDesejosPorLeitor(int idLeitor);

    @Insert
    void insertAll(Desejo... desejo);

    @Update
    void update(Desejo desejo);

    @Delete
    void delete(Desejo desejo);
}
