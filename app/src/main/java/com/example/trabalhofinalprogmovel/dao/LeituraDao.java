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

    @Query("SELECT GROUP_CONCAT(Livro.genero) as generos_mais_lidos\n" +
            "FROM Leitura\n" +
            "INNER JOIN Livro ON Leitura.livroId = Livro.livroId\n" +
            "WHERE Leitura.leitorId = :idLeitor\n" +
            "GROUP BY Livro.genero\n" +
            "ORDER BY COUNT(*) DESC\n" +
            "LIMIT 1;")
    String getGeneroMaisLidoPorLeitor(int idLeitor);

    @Query("SELECT SUM(Livro.quantidadeDePaginas) FROM Leitura INNER JOIN  Livro ON Leitura.livroId = Livro.livroId WHERE leitorId = :idLeitor")
    int getQuantidadePaginasLidasPorLeitor(int idLeitor);

    @Query("SELECT * FROM Leitura  INNER JOIN  Livro ON Leitura.livroId = Livro.livroId WHERE leitorId = :idLeitor AND Livro.genero = :genero")
    List<Leitura> getLeiturasPorLeitorEGenero(int idLeitor, String genero);

    @Query("SELECT * FROM Leitura WHERE leitorId = :idLeitor AND nota = :nota")
    List<Leitura> getLeiturasPorLeitorENota(int idLeitor, int nota);

    @Query("SELECT DISTINCT nota FROM Leitura  WHERE nota IS NOT NULL")
    List<Integer> getLeiturasNota();

    @Insert
    void insertAll(Leitura... leitura);

    @Update
    void update(Leitura leitura);

    @Delete
    void delete(Leitura leitura);

}
