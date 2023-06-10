package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.databinding.ActivityAdicionarLeituraBinding;

public class AdicionarLeituraActivity extends AppCompatActivity {
    private ActivityAdicionarLeituraBinding binding;
    private Intent intent;
    private boolean ehTelaDeCadastroLeitura;
    private int idLeitura;
    private int idDesejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarLeituraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ehTelaDeCadastroLeitura = getIntent().getBooleanExtra("adicionar_leitura", true);
        idLeitura = getIntent().getIntExtra("id_leitura", -1);
        idDesejo = getIntent().getIntExtra("id_desejo", -1);

        if(ehTelaDeCadastroLeitura){
            if(idLeitura >= 0){
                preencherCamposLeitura();
                binding.cadastroBtn.setText(getString(R.string.editar));
            }

            binding.cadastroBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idLeitura >= 0){
                        // atualizar leitura
                    }
                    else{
                        // adicionar leitura
                    }
                }
            });

            binding.excluirbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // excluir leitura
                }
            });
        }
        else{
            binding.campoComentario.setVisibility(View.GONE);
            binding.comentarioInput.setVisibility(View.GONE);
            binding.campoNota.setVisibility(View.GONE);
            binding.notaInput.setVisibility(View.GONE);

            if(idDesejo >= 0){
                preencherCamposDesejo();
                binding.cadastroBtn.setText(getString(R.string.editar));
            }

            binding.cadastroBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idDesejo >= 0){
                        //atualizar Desejo
                    }
                    else{
                        // adicionar desejo
                    }
                }
            });

            binding.excluirbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // excluir desejo
                }
            });
        }

        binding.cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void preencherCamposLeitura(){
        // buscar leitura e preencher informações
    }
    protected void preencherCamposDesejo(){
        // buscar desejo e preencher informações
    }
}
