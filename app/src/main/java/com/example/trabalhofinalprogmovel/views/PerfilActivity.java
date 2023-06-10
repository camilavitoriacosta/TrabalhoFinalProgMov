package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private Intent intentLista;
    private Intent intentEdicao;
    private int idLeitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intentLista = new Intent(this, ListaActivity.class);
        intentEdicao = new Intent(this, UsuarioCadastro.class);
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        // carregar informações do leitor passado por parametro


        binding.listaDesejosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentLista.putExtra("listaLeituras", false);
                startActivity(intentLista);
            }
        });

        binding.LeiturasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentLista.putExtra("listaLeituras", true);
                startActivity(intentLista);
            }
        });

        binding.editarLeitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentLista.putExtra("cadastro", true);
                intentLista.putExtra("id_leitor", idLeitor);
                startActivity(intentEdicao);
            }
        });
    }
}