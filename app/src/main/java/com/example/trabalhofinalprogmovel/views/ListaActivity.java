package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitura;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ActivityListaBinding binding;
    private Intent intent;
    private LocalDatabase db;
    private List<String> generos;
    private List<Integer> notas;
    private List<Leitura> leiturasLeitor;
    private int idLeitor;
    private ListView lista;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.listaDesejo) {
            intent = new Intent(this, ListaDesejoActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else if (id == R.id.listaLeitura) {
            intent = new Intent(this, ListaActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else if(id == R.id.perfil ) {
            intent = new Intent(this, PerfilActivity.class);
            intent.putExtra("id_leitor", idLeitor);
        }
        else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("id_leitor", -1);
        }

        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(this, AdicionarLeituraActivity.class);
        db = LocalDatabase.getDatabase(getApplicationContext());
        lista = binding.listView;
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        configurarListaLeitura();
        configurarSpinnerNota();
        configurarSpinnerGenero();
    }

    private void configurarListaLeitura(){
        binding.adicionarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("adicionar_leitura", true);
                intent.putExtra("id_leitor", idLeitor);
                intent.putExtra("id_leitura", -1);
                startActivity(intent);
            }
        });

        leiturasLeitor = db.leituraDao().getLeiturasPorLeitor(idLeitor);
        ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, leiturasLeitor);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id){
                Leitura leituraSelecionado = leiturasLeitor.get(position);
                intent.putExtra("id_leitura",
                        leituraSelecionado.getLeituraId());
                startActivity(intent);
            }
        });
    }

    private void configurarSpinnerGenero(){
        Spinner spinnerGenero = binding.spinnerGenero;
        generos = db.livroDao().getLivroGenero();

        // Criar lista temporária com texto informativo na primeira posição
        List<String> generosComTextoInformativo = new ArrayList<>();
        generosComTextoInformativo.add("Selecione um gênero");
        generosComTextoInformativo.addAll(generos);

        ArrayAdapter<String> generoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, generosComTextoInformativo);
        spinnerGenero.setAdapter(generoAdapter);

        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if (posicao > 0) {
                    String generoSelecionado = generos.get(posicao - 1); // Subtrair 1 para compensar o texto informativo
                    leiturasLeitor = db.leituraDao().getLeiturasPorLeitorEGenero(idLeitor, generoSelecionado);
                } else {
                    leiturasLeitor = db.leituraDao().getLeiturasPorLeitor(idLeitor);
                }
                ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(ListaActivity.this, android.R.layout.simple_list_item_1, leiturasLeitor);
                lista.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void configurarSpinnerNota(){
        Spinner spinnerNota = binding.spinnerNota;

        notas = db.leituraDao().getLeiturasNota();

        // Converter lista de Integer para lista de String
        List<String> notasString = new ArrayList<>();
        for (Integer nota : notas) {
            notasString.add(String.valueOf(nota));
        }

        // Adicionar texto informativo na primeira posição da lista de notas
        notasString.add(0, "Selecione uma nota");

        ArrayAdapter<String>  notaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notasString);
        spinnerNota.setAdapter(notaAdapter);

        spinnerNota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                String notaSelecionada = notasString.get(posicao);

                if (!notaSelecionada.equals("Selecione uma nota")) {
                    leiturasLeitor = db.leituraDao().getLeiturasPorLeitorENota(idLeitor, Integer.parseInt(notaSelecionada));
                } else {
                    leiturasLeitor = db.leituraDao().getLeiturasPorLeitor(idLeitor);
                }

                ArrayAdapter<Leitura> adapter = new ArrayAdapter<>(ListaActivity.this, android.R.layout.simple_list_item_1, leiturasLeitor);
                lista.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}