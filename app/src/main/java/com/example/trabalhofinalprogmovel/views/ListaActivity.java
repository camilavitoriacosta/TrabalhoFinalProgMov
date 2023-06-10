package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;

public class ListaActivity extends AppCompatActivity {
    private ActivityListaBinding binding;
    private boolean ehListaLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ehListaLeitura = getIntent().getBooleanExtra("listaLeituras", false);

        if(ehListaLeitura){
            binding.listaTitulo.setText(getString(R.string.leituras));
        }
        else{
            binding.listaTitulo.setText(getString(R.string.listaDeDesejos));
            binding.notaLinearLayout.setVisibility(View.GONE);
        }
    }
}