package com.example.trabalhofinalprogmovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.databinding.ActivityMainBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityUsuarioCadastroBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.iniciarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsuarioCadastro.class);
                intent.putExtra("cadastro", true);
                startActivity(intent);
            }
        });

        binding.retomarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsuarioCadastro.class);
                intent.putExtra("cadastro", false);
                startActivity(intent);
            }
        });
    }
}