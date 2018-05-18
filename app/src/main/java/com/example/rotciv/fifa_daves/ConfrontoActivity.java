package com.example.rotciv.fifa_daves;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.rotciv.fifa_daves.Adapter.AdapterConfronto;
import com.example.rotciv.fifa_daves.Dao.ConfrontoDAO;
import com.example.rotciv.fifa_daves.Dao.JogadorDAO;
import com.example.rotciv.fifa_daves.Dao.TimeDAO;
import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;
import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Jogador;
import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfrontoActivity extends AppCompatActivity {
    ListView lstConfronto;
    private Confronto confronto;
    private List<Usuario> ListaUsuario;
    List<Confronto> ListaConfronto;
    Button btnTerminarRodada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confronto);
        binding();

        Intent intent = new Intent(getApplicationContext(),GifActivity.class);
        startActivity(intent);
        audio();

        setarlistaPersonalizada();
        listaDuelos();
        btnTerminar();
    }

    private void btnTerminar() {
        btnTerminarRodada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JogadorDAO jogadorDAO = new JogadorDAO(getApplicationContext());
                jogadorDAO.open();
                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                usuarioDAO.open();

                List<Jogador> ListJogador = jogadorDAO.findArtilheiro();
                    for (int i =0; i < ListJogador.size(); i++){
                        Jogador jogador =ListJogador.get(i);
                        Usuario usuario = usuarioDAO.buscarUsuarioPorTime(jogador.getTime().getId());

                        usuarioDAO.inserirPonto(usuario,5);
                    }
                jogadorDAO.close();
                usuarioDAO.close();
                playSound();
                Intent intent = new Intent(getApplicationContext(),UltimaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setarlistaPersonalizada() {
        lstConfronto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itn = new Intent(getApplicationContext(),JogadorActivity.class);
                Confronto confronto  = ListaConfronto.get(position);
                itn.putExtra("confronto", confronto);

                startActivityForResult(itn,1);
            }
        });
    }

    private void audio() {
        MediaPlayer mp = MediaPlayer.create(ConfrontoActivity.this, R.raw.sound);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });
    }

    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();
    }

    public void listaDuelos(){
        ListaConfronto = new ArrayList<>();

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuarioDAO.open();

        ConfrontoDAO confrontoDAO = new ConfrontoDAO(getApplicationContext());
        confrontoDAO.open();

        Confronto confronto;

        ListaUsuario = usuarioDAO.findAll();

        for (int i=0; i <= ListaUsuario.size()/2; i++){
            Usuario usuario1 = usuarioAleatorio(ListaUsuario,true);
            Usuario usuario2 = usuarioAleatorio(ListaUsuario,true);

            confronto = new Confronto(i+1,usuario1,usuario2);

            confrontoDAO.gravarConfronto(confronto);

            ListaConfronto.add(confronto);
        }
        usuarioDAO.close();
        confrontoDAO.close();

        AdapterConfronto adpConfronto = new AdapterConfronto(getApplicationContext(),ListaConfronto);
        lstConfronto.setAdapter(adpConfronto);


    }

    private Usuario usuarioAleatorio(List<Usuario> listaUsuario,boolean remover) {
        int idUsu = 0;
        if (listaUsuario.size() >0){
            Random random = new Random();
            idUsu = random.nextInt(listaUsuario.size());
        }
        Usuario usuario = listaUsuario.get(idUsu);
        if (remover){
            listaUsuario.remove(usuario);
        }
        return usuario;
    }

    private void binding() {
        lstConfronto = findViewById(R.id.lstConfronto);
        btnTerminarRodada = findViewById(R.id.btnTerminarRodada);
    }
}
