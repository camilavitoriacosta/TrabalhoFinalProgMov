package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityAdicionarLeituraBinding;
import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitor;
import com.example.trabalhofinalprogmovel.entities.Leitura;
import com.example.trabalhofinalprogmovel.entities.Livro;

import java.util.List;

public class AdicionarLeituraActivity extends AppCompatActivity {
    private ActivityAdicionarLeituraBinding binding;
    private Intent intent;
    private LocalDatabase db;
    private boolean ehTelaDeCadastroLeitura;
    private int idLeitura;
    private int idDesejo;
    private int idLeitor;

    private List<Livro> livros;
    private Leitura leitura;
    private Desejo desejo;

    final AppCompatActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarLeituraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        intent = new Intent(this, AdicionarLivroActivity.class);

        ehTelaDeCadastroLeitura = getIntent().getBooleanExtra("adicionar_leitura", true);
        idLeitura = getIntent().getIntExtra("id_leitura", -1);
        idDesejo = getIntent().getIntExtra("id_desejo", -1);
        idLeitor = getIntent().getIntExtra("id_leitor", -1);

        configurarSpinnerTituloLivro();

        if(ehTelaDeCadastroLeitura){
            if(idLeitura >= 0){
                preencherCamposLeitura();
                binding.cadastroBtn.setText(getString(R.string.editar));
            }

            binding.cadastroBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(verificarCamposCadastroOuEdicaoLeitura()){
                        int index = binding.spinnerLivro.getSelectedItemPosition();
                        Livro livro = livros.get(index);
                        String comentario = binding.comentarioInput.getText().toString();
                        int nota = Integer.parseInt(binding.notaInput.getText().toString());

                        if(idLeitura >= 0){
                            Leitura leitura = db.leituraDao().getLeituraPorId(idLeitura);
                            leitura.setComentario(comentario);
                            leitura.setNota(nota);
                            leitura.setTitulo(livro.getTitulo());
                            leitura.setLivroId(livro.getLivroId());
                            db.leituraDao().update(leitura);
                        }
                        else{
                            leitura = new Leitura(idLeitor, livro.getLivroId(), livro.getTitulo(), comentario, nota);
                            db.leituraDao().insertAll(leitura);
                        }
                        finish();
                    }
                }
            });

            binding.excluirbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(activity)
                            .setTitle("Exclusão de Leitura")
                            .setMessage("Deseja excluir essa leitura?")
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.leituraDao().delete(db.leituraDao().getLeituraPorId(idLeitura));
                                    Toast.makeText(activity, "Leitura excluída com sucesso.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .setNegativeButton("Não", null)
                            .show();
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
                    int index = binding.spinnerLivro.getSelectedItemPosition();
                    Livro livro = livros.get(index);

                    if(verificarCamposCadastroOuEdicaoDesejo()){
                        if(idDesejo >= 0){
                            //atualizar Desejo
                        }
                        else{
                            desejo = new Desejo(idLeitor, livro.getLivroId(), livro.getTitulo());
                            db.desejoDao().insertAll(desejo);
                            finish();
                        }
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

        binding.adicionarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }

    protected void preencherCamposLeitura(){
        System.out.println(idLeitura);
        if(idLeitura >= 0){
            Leitura leitura = db.leituraDao().getLeituraPorId(idLeitura);
            System.out.println(leitura);
            binding.spinnerLivro.setSelection(leitura.getLeituraId() - 1);
            binding.comentarioInput.setText(leitura.getComentario());
            binding.notaInput.setText(Integer.toString(leitura.getNota()));
        }
    }

    protected void preencherCamposDesejo(){
        // buscar desejo e preencher informações
    }

    private void configurarSpinnerTituloLivro() {
        Spinner spinnerTituloLivro = binding.spinnerLivro;

        livros = db.livroDao().getAll();
        ArrayAdapter<Livro> livroAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);
        spinnerTituloLivro.setAdapter(livroAdapter);
    }

    private boolean validarCampo(String campo, String valorCampo){
        if(valorCampo.equals("")){
            Toast.makeText(this, "O campo de " + campo +" é obrigatório", Toast.LENGTH_SHORT).show();
        }
        return valorCampo.equals("");
    }

    private boolean verificarCamposCadastroOuEdicaoLeitura(){
        String livro = binding.spinnerLivro.getSelectedItem().toString();
        String comentario = binding.comentarioInput.getText().toString();
        String nota = binding.notaInput.getText().toString();
        return !validarCampo("Livro", livro) && !validarCampo("comentario", comentario) && !validarCampo("nota", nota);
    }

    private boolean verificarCamposCadastroOuEdicaoDesejo(){
        String livro = binding.spinnerLivro.getSelectedItem().toString();
        return !validarCampo("Livro", livro);
    }
}
