package com.example.trabalhofinalprogmovel.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabalhofinalprogmovel.dao.DesejoDao;
import com.example.trabalhofinalprogmovel.dao.LeitorDao;
import com.example.trabalhofinalprogmovel.dao.LeituraDao;
import com.example.trabalhofinalprogmovel.dao.LivroDao;
import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitor;
import com.example.trabalhofinalprogmovel.entities.Leitura;
import com.example.trabalhofinalprogmovel.entities.Livro;

@Database(entities = {Livro.class, Leitor.class, Leitura.class, Desejo.class}, version = 3)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "ReadingJournal").allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }

    public abstract LivroDao livroDao();
    public abstract LeitorDao leitorDao();
    public abstract LeituraDao leituraDao();
    public abstract DesejoDao desejoDao();

}