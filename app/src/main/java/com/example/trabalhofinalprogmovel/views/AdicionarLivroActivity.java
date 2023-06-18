package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityAdicionarLivroBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.entities.Livro;

public class AdicionarLivroActivity extends AppCompatActivity {
    private ActivityAdicionarLivroBinding binding;
    private LocalDatabase db;
    private Livro livro;
    private int idLeitor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.listaDesejo) {
            intent = new Intent(this, ListaDesejoActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else if (id == R.id.listaLeitura) {
            intent = new Intent(this, ListaActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else if(id == R.id.perfil ) {
            intent = new Intent(this, PerfilActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("id_leitor", -1);
        }

        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase((getApplicationContext()));
        idLeitor = getIntent().getIntExtra("id_leitor", -1);

        binding.cadastroLivroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCamposCadastroOuEdicao()){
                    String titulo = binding.tituloLivroAdicao.getText().toString();
                    String genero = binding.generoInput.getText().toString();
                    String paginas = binding.paginasInput.getText().toString();

                    livro = new Livro(titulo, genero, Integer.parseInt(paginas));
                    db.livroDao().insertAll(livro);
                    finish();
                }
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