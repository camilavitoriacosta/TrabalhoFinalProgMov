package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.databinding.ActivityAdicionarLivroBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;

public class AdicionarLivroActivity extends AppCompatActivity {
    private ActivityAdicionarLivroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cadastroLivroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cadastrar livro
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
}