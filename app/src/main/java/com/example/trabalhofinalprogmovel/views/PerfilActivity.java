package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;
import com.example.trabalhofinalprogmovel.entities.Leitor;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private LocalDatabase db;
    private Intent intentListaLeitura;
    private Intent intentListaDesejo;
    private Intent intentEdicao;
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
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        intentListaLeitura = new Intent(this, ListaActivity.class);
        intentListaDesejo = new Intent(this, ListaDesejoActivity.class);
        intentEdicao = new Intent(this, UsuarioCadastro.class);

        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        carregarInformacoesLeitor();


        binding.listaDesejosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListaDesejo.putExtra("id_leitor", idLeitor);
                startActivity(intentListaDesejo);
                finish();
            }
        });

        binding.LeiturasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentListaLeitura.putExtra("id_leitor", idLeitor);
                startActivity(intentListaLeitura);
                finish();
            }
        });

        binding.editarLeitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentEdicao.putExtra("cadastro", true);
                intentEdicao.putExtra("id_leitor", idLeitor);
                startActivity(intentEdicao);
            }
        });
    }

    private void carregarInformacoesLeitor(){
        Leitor leitor = db.leitorDao().getLeitor(idLeitor);
        binding.nomeLeitor.setText(leitor.getNome());
        binding.emailLeitor.setText(leitor.getEmail());

        int livrosLidos = db.leituraDao().getLeiturasPorLeitor(idLeitor).size();
        binding.livrosLidos.setText(getString(R.string.livrosLidos) + livrosLidos);

        int paginasLidas = db.leituraDao().getQuantidadePaginasLidasPorLeitor(idLeitor);
        binding.paginasLidas.setText(getString(R.string.paginasLidas) + paginasLidas);

        String generoLido = db.leituraDao().getGeneroMaisLidoPorLeitor(idLeitor);
        if(generoLido == null){
            generoLido = "-";
        }
        binding.generoLido.setText(getString(R.string.generoLido) + generoLido);
    }
}