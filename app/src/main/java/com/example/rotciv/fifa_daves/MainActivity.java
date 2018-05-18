package com.example.rotciv.fifa_daves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rotciv.fifa_daves.Dao.TimeDAO;
import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;
import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spnTime;
    EditText edtNome;
    Button btnCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding();
        preencherSpinner();

        btnCadastro();


    }

    private void btnCadastro() {
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                usuarioDAO.open();
                Time t =    lista.get(spnTime.getSelectedItemPosition());
                Usuario u = new Usuario(0,t,edtNome.getText().toString());

                if(usuarioDAO.gravarUsuario(u)){
                    Toast.makeText(getApplicationContext(),"Usuário "+u.getNome()+" cadastrado!",Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(0, android.R.anim.slide_out_right);
                }
                usuarioDAO.close();;
            }
        });
    }


    private List<Time> lista;

    private void preencherSpinner() {

        TimeDAO timeDAO = new TimeDAO(getApplicationContext());
        timeDAO.open();


        int idx = 0;

        this.lista  = timeDAO.findAllnaoSelecionado();

        String[] times = new String[lista.size()];

        for (Time t : lista) {
            times[idx++] = t.getNome();
        }
        timeDAO.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                times);
        spnTime.setAdapter(adapter);
    }


    private void binding() {
        spnTime = findViewById(R.id.spnTime);
        edtNome = findViewById(R.id.edtNome);
        btnCadastro = findViewById(R.id.btnCadastro);
    }
}
