package com.example.rotciv.fifa_daves;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;

public class PrincipalActivity extends AppCompatActivity {

    Button brnavancar,btnPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        binding();

        btnAvancarClique();

        cliquebtnPartida();

        MediaPlayer mp = Audio();

        mp.start();
    }

    @NonNull
    private MediaPlayer Audio() {
        MediaPlayer mp = MediaPlayer.create(PrincipalActivity.this, R.raw.menu);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                mp.release();
            }

        });
        return mp;
    }

    private void cliquebtnPartida() {
        btnPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioDAO usuariodao = new UsuarioDAO(getApplicationContext());
                usuariodao.open();
                int contador = usuariodao.count();

                if(contador %2 ==0){
                    Intent intent = new Intent(getApplicationContext(),ConfrontoActivity.class);
                    startActivity(intent);

                }else
                {
                    Toast.makeText(getApplicationContext(),"Por favor cadastre um número par de Jogadores",Toast.LENGTH_SHORT).show();

                }
                usuariodao.close();


            }
        });
    }

    private void btnAvancarClique() {
        brnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.menu);
        mediaPlayer.start();
    }

    private void binding() {
        brnavancar = findViewById(R.id.brnavancar);
        btnPartida = findViewById(R.id.btnPartida);
    }
}
