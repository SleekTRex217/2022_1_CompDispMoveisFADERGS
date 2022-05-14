package com.example.petshopdosguri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spTipoPet;
    private EditText etNome, etTelefone, etEmail, etNomePet;
    private Button btnSalvar;
    private RadioGroup rgCl;
    private RadioButton rbSim, rbNao;
    private TipoPet tipoPet;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTipoPet = findViewById(R.id.spTipoPet);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etNomePet = findViewById(R.id.etNomePet);
        btnSalvar = findViewById(R.id.btnSalvar);
        rgCl = findViewById(R.id.rgCl);
        rbSim = findViewById(R.id.rbSim);
        rbNao = findViewById(R.id.rbNao);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        carregarTipoPet();
    }

    private void carregarTipoPet() {
        TipoPet fake = new TipoPet(0, "Selecione o seu Pet...");
        List<TipoPet> lista = TipoPetDAO.getTipoPet(this);
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spTipoPet.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add("Cadastrar Tipo de Pet...");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.toString().equals("Cadastrar Tipo de Pet...")){
            cadastrarTipoPet();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        Cliente cliente = new Cliente();
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String emai = etEmail.getText().toString();
        String nomepet = etNomePet.getText().toString();
        int radioButtonReg = rgCl.getCheckedRadioButtonId();
        String rdCheck = ((RadioButton) findViewById(radioButtonReg)).getText().toString();
            if(rdCheck.equals(getString(R.string.rb_Sim))){
                cliente.promo = "Sim";
            } else {
                cliente.promo = "Não";
            }

        if(nome.isEmpty() || telefone.isEmpty()){
            Toast.makeText(this,"Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
        } else {
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setEmail(emai);
            cliente.setNomePet(nomepet);
            cliente.setPromo(rdCheck);
            cliente.setTipoPet((TipoPet) spTipoPet.getSelectedItem());
            ClienteDAO.inserir(this, cliente);
            finish();
        }
    }

    private void cadastrarTipoPet(){
        AlertDialog.Builder alerta =new AlertDialog.Builder(this);
        alerta.setTitle("Cadastrar Tipo de Pet");
        alerta.setIcon(android.R.drawable.ic_input_add);

        EditText etNomeTipoPet = new EditText(this);
        etNomeTipoPet.setHint("Digite aqui o tipo de Pet...");
        alerta.setView(etNomeTipoPet);

        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nome = etNomeTipoPet.getText().toString();
                if(!nome.isEmpty()){
                    TipoPetDAO.inserir(MainActivity.this, nome);
                    carregarTipoPet();
                }
            }
        });
        alerta.show();
    }
}