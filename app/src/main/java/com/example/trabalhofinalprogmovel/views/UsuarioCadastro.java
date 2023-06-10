package com.example.trabalhofinalprogmovel.views;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.databinding.ActivityUsuarioCadastroBinding;

public class UsuarioCadastro extends AppCompatActivity {

    private ActivityUsuarioCadastroBinding binding;
    private Intent intent;
    private boolean ehTelaDeCadastro;
    private int idLeitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ehTelaDeCadastro = getIntent().getBooleanExtra("cadastro", true);
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        intent = new Intent(this, PerfilActivity.class);

        if(ehTelaDeCadastro) {
            binding.retomarJornadaBtn.setVisibility(View.GONE);
            if(idLeitor >= 0){
                carregarInformacoesLeitor();
                // setar texto botao para editar
            }
        }
        else{
            binding.iniciarJornadaBtn.setVisibility(View.GONE);
            binding.campoNome.setVisibility(View.GONE);
            binding.editNome.setVisibility(View.GONE);
        }

        binding.iniciarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verificar campos vazios
                // cadastrar usuario e obter usuario cadastrado
                // mandar id para proxima tela
                int idLeitor = 0;
                intent.putExtra("id_leitor", idLeitor);
                startActivity(intent);
            }
        });

        binding.retomarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obter usuario por email
                // verificar se senha que digitou está correta
                // mandar id para proxima tela
                int idLeitor = 0;
                intent.putExtra("id_leitor", idLeitor);
                startActivity(intent);
            }
        });
    }

    protected void carregarInformacoesLeitor(){
        // buscar leitor pelo id
    }
}