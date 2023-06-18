package com.example.trabalhofinalprogmovel.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.trabalhofinalprogmovel.R;
import com.example.trabalhofinalprogmovel.database.LocalDatabase;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaBinding;
import com.example.trabalhofinalprogmovel.databinding.ActivityListaDesejoBinding;
import com.example.trabalhofinalprogmovel.entities.Desejo;
import com.example.trabalhofinalprogmovel.entities.Leitura;

import java.util.List;

public class ListaDesejoActivity extends AppCompatActivity {
    private ActivityListaDesejoBinding binding;
    private Intent intent;
    private LocalDatabase db;
    private List<String> generos;
    private List<Desejo> desejosLeitor;
    private int idLeitor;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaDesejoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(this, AdicionarLeituraActivity.class);
        db = LocalDatabase.getDatabase(getApplicationContext());
        lista = binding.listView;
        idLeitor = getIntent().getIntExtra("id_leitor", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        configurarElementos();
    }

    private void configurarElementos() {
        configurarListaDesejos();
        configurarSpinnerGenero();
    }

    private void configurarListaDesejos(){
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
        lista.setAdapter(adapter);
        System.out.println(desejosLeitor);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                desejosLeitor = db.desejoDao().getDesejosPorLeitorPorGenero(idLeitor, generoSelecionado);
                ArrayAdapter<Desejo> adapter = new ArrayAdapter<>(ListaDesejoActivity.this, android.R.layout.simple_list_item_1, desejosLeitor);
                lista.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}