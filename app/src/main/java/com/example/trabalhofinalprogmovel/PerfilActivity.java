package com.example.trabalhofinalprogmovel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalhofinalprogmovel.databinding.ActivityPerfilBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityUsuarioCadastroBinding;

public class PerfilActivity extends AppCompatActivity {

    private ActivityPerfilBinding binding;
    private int idLeitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        // carregar informações do leitor passado por parametro
    }
}