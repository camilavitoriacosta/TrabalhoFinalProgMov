package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityAdicionarLivroBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.entities.Livro;

public class AdicionarLivroActivity extends AppCompatActivity {
    private ActivityAdicionarLivroBinding binding;
    private LocalDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase((getApplicationContext()));

        binding.cadastroLivroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCamposCadastroOuEdicao()){
                    String titulo = binding.tituloLivroAdicao.getText().toString();
                    String genero = binding.generoInput.getText().toString();
                    String paginas = binding.paginasInput.getText().toString();

                    Livro livro = new Livro(titulo, genero, Integer.parseInt(paginas));
                    db.livroDao().insertAll(livro);
                }
                finish();
            }
        });

        binding.cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validarCampo(String campo, String valorCampo){
        if(valorCampo.equals("")){
            Toast.makeText(this, "O campo de " + campo +" é obrigatório", Toast.LENGTH_SHORT).show();
        }
        return valorCampo.equals("");
    }

    private boolean verificarCamposCadastroOuEdicao(){
        String titulo = binding.tituloLivroAdicao.getText().toString();
        String genero = binding.generoInput.getText().toString();
        String paginas = binding.paginasInput.getText().toString();
        return !validarCampo("titulo", titulo) && !validarCampo("genero", genero) && !validarCampo("paginas", paginas);
    }
}