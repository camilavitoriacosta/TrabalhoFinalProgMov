package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;
import com.example.trabalhofinalprogmovel.entities.Leitor;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private LocalDatabase db;
    private Intent intentLista;
    private Intent intentEdicao;
    private int idLeitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        intentLista = new Intent(this, ListaActivity.class);
        intentEdicao = new Intent(this, UsuarioCadastro.class);

        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        carregarInformacoesLeitor();


        binding.listaDesejosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentLista.putExtra("listaLeituras", false);
                intentLista.putExtra("id_leitor", idLeitor);
                startActivity(intentLista);
                finish();
            }
        });

        binding.LeiturasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentLista.putExtra("listaLeituras", true);
                intentLista.putExtra("id_leitor", idLeitor);
                startActivity(intentLista);
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