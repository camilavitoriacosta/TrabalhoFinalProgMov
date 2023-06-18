package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitura;

import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ActivityListaBinding binding;
    private Intent intent;
    private boolean ehListaLeitura;
    private LocalDatabase db;

    private List<String> generos;

    private List<Integer> notas;
    private List<Leitura> leiturasLeitor;
    private List<Desejo> desejosLeitor;
    private int idLeitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(this, AdicionarLeituraActivity.class);
        db = LocalDatabase.getDatabase(getApplicationContext());

        ehListaLeitura = getIntent().getBooleanExtra("listaLeituras", false);
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        configurarElementos();
    }

    private void configurarElementos() {
        if(ehListaLeitura){
           configurarListaLeitura();
           configurarSpinnerNota();
        }
        else{
            configurarListaDesejos();
        }

        configurarSpinnerGenero();
    }

    private void configurarListaLeitura(){
        binding.listaTitulo.setText(getString(R.string.leituras));
        binding.adicionarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("adicionar_leitura", true);
                intent.putExtra("id_leitor", idLeitor);
                startActivity(intent);
            }
        });

        leiturasLeitor = db.leituraDao().getLeiturasPorLeitor(idLeitor);
        ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, leiturasLeitor);
        binding.listLivros.setAdapter(adapter);

        binding.listLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id){
                Leitura leituraSelecionado = leiturasLeitor.get(position);
                intent.putExtra("id_leitura",
                        leituraSelecionado.getLeituraId());
                startActivity(intent);
            }
        });
    }

    private void configurarListaDesejos(){
        binding.listaTitulo.setText(getString(R.string.listaDeDesejos));
        binding.notaLinearLayout.setVisibility(View.GONE);

        binding.adicionarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("adicionar_leitura", false);
                intent.putExtra("id_leitor", idLeitor);
                startActivity(intent);
            }
        });

        desejosLeitor = db.desejoDao().getDesejosPorLeitor(idLeitor);
        ArrayAdapter<Desejo> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, desejosLeitor);
        binding.listLivros.setAdapter(adapter);

        binding.listLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id){
                Desejo desejoSelecionado = desejosLeitor.get(position);
                intent.putExtra("id_desejo",
                        desejoSelecionado.getDesejoId());
                startActivity(intent);
            }
        });
    }

    private void configurarSpinnerGenero(){
        Spinner spinnerGenero = binding.spinnerGenero;

        generos = db.livroDao().getLivroGenero();
        ArrayAdapter<String>  generoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, generos);
        spinnerGenero.setAdapter(generoAdapter);

        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                String generoSelecionado = generos.get(spinnerGenero.getSelectedItemPosition());
                leiturasLeitor = db.leituraDao().getLeiturasPorLeitorEGenero(idLeitor, generoSelecionado);

                ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(ListaActivity.this, android.R.layout.simple_list_item_1, leiturasLeitor);
                binding.listLivros.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void configurarSpinnerNota(){
        Spinner spinnerNota = binding.spinnerNota;

        notas = db.leituraDao().getLeiturasNota();
        ArrayAdapter<Integer>  notaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notas);
        spinnerNota.setAdapter(notaAdapter);

        spinnerNota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                Integer notaSelecionado = notas.get(spinnerNota.getSelectedItemPosition());
                leiturasLeitor = db.leituraDao().getLeiturasPorLeitorENota(idLeitor, notaSelecionado);

                ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(ListaActivity.this, android.R.layout.simple_list_item_1, leiturasLeitor);
                binding.listLivros.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}