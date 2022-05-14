package com.example.petshopdosguri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PetShopActivity extends AppCompatActivity {

    public ListView lvClientes;
    public Button btnAdicionar;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_petshop);

        lvClientes = findViewById(R.id.lvClientes);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetShopActivity.this, MainActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        carregarClientes();
    }

    private void carregarClientes(){
        List<Cliente> lista = ClienteDAO.getClientes(this);

        if(lista.size() == 0){
            Cliente fake = new Cliente("Nenhum cliente cadastrado", "...", "...", "...", "...", null);
            lista.add(fake);
            lvClientes.setEnabled(false);
        } else {
            lvClientes.setEnabled(true);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvClientes.setAdapter(adapter);
    }
}
