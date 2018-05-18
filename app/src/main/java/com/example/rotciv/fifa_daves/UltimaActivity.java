package com.example.rotciv.fifa_daves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rotciv.fifa_daves.Adapter.AdapterArtilheiro;
import com.example.rotciv.fifa_daves.Adapter.AdapterConfronto;
import com.example.rotciv.fifa_daves.Adapter.AdapterRanking;
import com.example.rotciv.fifa_daves.Dao.ConfrontoDAO;
import com.example.rotciv.fifa_daves.Dao.JogadorDAO;
import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;
import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Jogador;
import com.example.rotciv.fifa_daves.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UltimaActivity extends AppCompatActivity {

    Button btnirprincipal;
   ListView listRanking, listArtilheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultima);

        binding();

        preencherRanking();
        preencherArtilheiro();
        irparaprincipal();

    }

    private void irparaprincipal() {
        btnirprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent);

            }
        });
    }

    private void preencherRanking() {
        List<Usuario> ListaUsuario;
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuarioDAO.open();

        ListaUsuario = usuarioDAO.Ranquear();
        usuarioDAO.close();

        AdapterRanking adpRanking = new AdapterRanking(getApplicationContext(),ListaUsuario);
        listRanking.setAdapter(adpRanking);

    }

    private void preencherArtilheiro() {
        List<Jogador> ListaJogador;
        JogadorDAO jogadorDAO = new JogadorDAO(getApplicationContext());
        jogadorDAO.open();

        ListaJogador = jogadorDAO.findArtilheiro();
        jogadorDAO.close();

        AdapterArtilheiro adpArtilheiro = new AdapterArtilheiro(getApplicationContext(),ListaJogador);
        listArtilheiro.setAdapter(adpArtilheiro);

    }

    private void binding() {
        listRanking = findViewById(R.id.listRanking);
        listArtilheiro = findViewById(R.id.listArtilheiro);
        btnirprincipal = findViewById(R.id.btnirprincipal);
    }
}
