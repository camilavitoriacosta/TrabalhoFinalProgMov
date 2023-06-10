package com.example.trabalhofinalprogmovel;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.databinding.ActivityUsuarioCadastroBinding;

public class UsuarioCadastro extends AppCompatActivity {

    private ActivityUsuarioCadastroBinding binding;
    private boolean ehTelaDeCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ehTelaDeCadastro = getIntent().getBooleanExtra("cadastro", true);

        if(ehTelaDeCadastro) {
            binding.retomarJornadaBtn.setVisibility(View.GONE);
        }
        else{
            binding.iniciarJornadaBtn.setVisibility(View.GONE);
            binding.campoNome.setVisibility(View.GONE);
            binding.editNome.setVisibility(View.GONE);
        }
    }
}