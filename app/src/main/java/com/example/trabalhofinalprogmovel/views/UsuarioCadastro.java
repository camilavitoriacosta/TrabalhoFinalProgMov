package com.example.trabalhofinalprogmovel.views;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityUsuarioCadastroBinding;
import com.example.trabalhofinalprogmovel.entities.Leitor;

import java.security.MessageDigest;

public class UsuarioCadastro extends AppCompatActivity {
    final AppCompatActivity activity = this;
    private ActivityUsuarioCadastroBinding binding;
    private Intent intent;
    private LocalDatabase db;
    private boolean ehTelaDeCadastro;
    private int idLeitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase((getApplicationContext()));

        ehTelaDeCadastro = getIntent().getBooleanExtra("cadastro", true);
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
        intent = new Intent(this, PerfilActivity.class);

        if(ehTelaDeCadastro) {
            if(idLeitor >= 0){
                carregarInformacoesLeitor();
                binding.iniciarJornadaBtn.setVisibility(View.GONE);
                binding.campoSenha.setVisibility(View.GONE);
                binding.editSenha.setVisibility(View.GONE);
            }else{
                binding.acoesEdicao.setVisibility(View.GONE);
            }
            binding.retomarJornadaBtn.setVisibility(View.GONE);
        }
        else{
            binding.acoesEdicao.setVisibility(View.GONE);
            binding.iniciarJornadaBtn.setVisibility(View.GONE);
            binding.campoNome.setVisibility(View.GONE);
            binding.editNome.setVisibility(View.GONE);
        }

        binding.iniciarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCamposCadastro()){
                    String nome = binding.editNome.getText().toString();
                    String email = binding.editEmail.getText().toString();
                    String senha = criptografar(binding.editSenha.getText().toString());

                    Leitor leitor = new Leitor(nome, email, senha);
                    if(idLeitor >= 0){
                        leitor.setLeitorId(idLeitor);
                        db.leitorDao().update(leitor);
                    }
                    else {
                        db.leitorDao().insertAll(leitor);
                        Leitor leitorCadastrado = db.leitorDao().getLeitorPorEmail(email);
                        idLeitor = leitorCadastrado.getLeitorId();
                    }

                    intent.putExtra("id_leitor", idLeitor);
                    startActivity(intent);
                    }
                }
        });

        binding.retomarJornadaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCamposLogin()) {
                    String email = binding.editEmail.getText().toString();
                    String senha = criptografar(binding.editSenha.getText().toString());
                    Leitor leitor = db.leitorDao().getLeitorPorEmail(email);
                    idLeitor = leitor.getLeitorId();
                    System.out.println(idLeitor);

                    if(senha.equals(leitor.getSenha())){
                        intent.putExtra("id_leitor", idLeitor);
                        startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Senha invalida")
                                .setTitle("Senha invalida")
                                .setPositiveButton("Ok",  (DialogInterface.OnClickListener) (dialog, which) -> {
                                    dialog.cancel();
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });

        binding.editarLeitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCamposEdicao()) {
                    String nome = binding.editNome.getText().toString();
                    String email = binding.editEmail.getText().toString();
                    Leitor leitor = db.leitorDao().getLeitor(idLeitor);
                    leitor.setEmail(email);
                    leitor.setNome(nome);
                    db.leitorDao().update(leitor);

                    intent.putExtra("id_leitor", idLeitor);
                    startActivity(intent);
                }
            }
        });
    }

    protected void carregarInformacoesLeitor(){
        Leitor leitor = db.leitorDao().getLeitor(idLeitor);
        binding.editNome.setText(leitor.getNome());
        binding.editEmail.setText(leitor.getEmail());
    }

    private String criptografar(String senha){
        String senhaCriptografada;
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            senhaCriptografada = new String(messageDigest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return senhaCriptografada;
    }

    private boolean validarCampo(String campo, String valorCampo){
        if(valorCampo.equals("")){
            Toast.makeText(this, "O campo de " + campo +" é obrigatório", Toast.LENGTH_SHORT).show();
        }
        return valorCampo.equals("");
    }

    private boolean verificarCamposCadastro(){
        String nome = binding.editNome.getText().toString();
        String email = binding.editEmail.getText().toString();
        String senha = binding.editSenha.getText().toString();
        return !validarCampo("nome", nome) && !validarCampo("email", email) && !validarCampo("senha", senha);
    }

    private boolean verificarCamposEdicao(){
        String nome = binding.editNome.getText().toString();
        String email = binding.editEmail.getText().toString();
        return !validarCampo("nome", nome) && !validarCampo("email", email);
    }

    private boolean verificarCamposLogin(){
        String email = binding.editEmail.getText().toString();
        String senha = binding.editSenha.getText().toString();
        return !validarCampo("email", email) && !validarCampo("senha", senha);
    }
}