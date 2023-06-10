package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;

public class ListaActivity extends AppCompatActivity {
    private ActivityListaBinding binding;
    private Intent intent;
    private boolean ehListaLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(this, AdicionarLeituraActivity.class);
        ehListaLeitura = getIntent().getBooleanExtra("listaLeituras", false);

        if(ehListaLeitura){
            binding.listaTitulo.setText(getString(R.string.leituras));

            binding.adicionarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("adicionar_leitura", true);
                    startActivity(intent);
                }
            });
        }
        else{
            binding.listaTitulo.setText(getString(R.string.listaDeDesejos));
            binding.notaLinearLayout.setVisibility(View.GONE);

            binding.adicionarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("adicionar_leitura", false);
                    startActivity(intent);
                }
            });
        }
    }
}