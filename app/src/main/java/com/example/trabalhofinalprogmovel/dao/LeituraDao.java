package com.example.trabalhofinalprogmovel.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.trabalhofinalprogmovel.entities.Leitura;

import java.util.List;

@Dao
public interface LeituraDao {
    @Query("SELECT * FROM Leitura WHERE livroId = :id LIMIT 1")
    Leitura getLeituraPorId(int id);

    @Query("SELECT * FROM Leitura WHERE leitorId = :idLeitor")
    List<Leitura> getLeiturasPorLeitor(int idLeitor);

    @Query("SELECT * FROM Leitura  INNER JOIN  Livro ON Leitura.livroId = Livro.livroId WHERE leitorId = :idLeitor AND Livro.genero = :genero")
    List<Leitura> getLeiturasPorLeitorEGenero(int idLeitor, String genero);

    @Query("SELECT * FROM Leitura WHERE leitorId = :idLeitor AND nota = :nota")
    List<Leitura> getLeiturasPorLeitorENota(int idLeitor, int nota);

    @Query("SELECT DISTINCT nota FROM Leitura")
    List<Integer> getLeiturasNota();

    @Insert
    void insertAll(Leitura... leitura);

    @Update
    void update(Leitura leitura);

    @Delete
    void delete(Leitura leitura);

}
