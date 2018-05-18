package com.example.rotciv.fifa_daves;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rotciv.fifa_daves.Dao.ConfrontoDAO;
import com.example.rotciv.fifa_daves.Dao.GolDAO;
import com.example.rotciv.fifa_daves.Dao.JogadorDAO;
import com.example.rotciv.fifa_daves.Dao.ResultadoDAO;
import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;
import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Gol;
import com.example.rotciv.fifa_daves.model.Jogador;
import com.example.rotciv.fifa_daves.model.Resultado;
import com.example.rotciv.fifa_daves.model.Usuario;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class JogadorActivity extends AppCompatActivity {
    Spinner spinnerJogadores1,spinnerJogadores2;
    TextView tvTime1Gols,tvTime2Gols,txvPlacarTime1,txvPlacarTime2;
    Button btnVoltar;
    ImageButton btnGravarGolsT1,btnGravarGolsT2;
    GifImageView bolaGif;

    private int placarT1=0;
    private int placarT2=0;

    List<Jogador> lista;
    List<Jogador> lista2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        System.out.println("Ok!");
        binding();
        bolaGif.setVisibility(View.INVISIBLE);
        Confronto confronto = RetornarConfronto();

        tvTime1Gols.setText(confronto.getUsuario1().getTime().getNome());
        tvTime2Gols.setText(confronto.getUsuario2().getTime().getNome());

        int idTime = confronto.getUsuario1().getTime().getId();
        int idTime2 = confronto.getUsuario2().getTime().getId();

        preencheSpinner1Jogador(idTime);
        preencheSpinner2Jogador(idTime2);

        Audio();


        gravarGoltime1();


        gravasGolsTime2();


        btnvoltar();

    }

    private void Audio() {
        MediaPlayer mp = MediaPlayer.create(JogadorActivity.this, R.raw.golesse);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

                mp.release();
            }

        });
    }

    private void gravarGoltime1() {
        btnGravarGolsT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                    JogadorDAO jogadorDAO = new JogadorDAO(getApplicationContext());
                    jogadorDAO.open();

                    Jogador jog = lista.get(spinnerJogadores1.getSelectedItemPosition());
                    if(jogadorDAO.marcarGol(jog)){
                    Toast.makeText(getApplicationContext(),"Golllllllllll do "+jog.getNome()+" !",Toast.LENGTH_SHORT).show();
                }
                jogadorDAO.close();

                placarT1 += 1;
                txvPlacarTime1.setText(String.valueOf(placarT1));
                bolaGif.setVisibility(View.VISIBLE);

                //esconte e exibe o gif apos um tempo
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        bolaGif.setVisibility(View.INVISIBLE);
                    }
                }, 1000);


            }
        });
    }

    private void gravasGolsTime2() {
        btnGravarGolsT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                JogadorDAO jogadorDAO = new JogadorDAO(getApplicationContext());
                jogadorDAO.open();

                Jogador jog = lista2.get(spinnerJogadores2.getSelectedItemPosition());

                if(jogadorDAO.marcarGol(jog)){
                    Toast.makeText(getApplicationContext(),"Golllllllllll do "+jog.getNome()+" !",Toast.LENGTH_SHORT).show();
                }
                jogadorDAO.close();

                placarT2 += 1;
                txvPlacarTime2.setText(String.valueOf(placarT2));

                bolaGif.setVisibility(View.VISIBLE);

                //esconte e exibe o gif apos um tempo
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        bolaGif.setVisibility(View.INVISIBLE);
                    }
                }, 1000);


            }
        });
    }

    private void btnvoltar() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarConfronto();
                Contarpontos();
                finish();
            }
        });
    }

    private void playSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.golesse);
        mediaPlayer.start();
    }

    private void SalvarConfronto() {
        Confronto confronto = RetornarConfronto();

        ResultadoDAO resultadoDAO = new ResultadoDAO(getApplicationContext());
        resultadoDAO.open();

        Resultado resultado = new Resultado(0,placarT1,placarT2,confronto);

        if(resultadoDAO.gravarResultado(resultado)){
            Toast.makeText(getApplicationContext(),"O juiz ergue o braço... Fim da partida",Toast.LENGTH_SHORT).show();
        }
        resultadoDAO.close();
    }

    private void Contarpontos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuarioDAO.open();
        Confronto confronto = RetornarConfronto();

        /*pontos por vitoria*/
        if(placarT1>placarT2){
            /*int idUsuario = confronto.getUsuario1().getId();*/
            double qntPonto = 3;
            usuarioDAO.inserirPonto(confronto.getUsuario1(),qntPonto);
            /*if(usuarioDAO.inserirPonto(confronto.getUsuario1(),qntPonto)){
                Toast.makeText(getApplicationContext(),"Vitoria do "+confronto.getUsuario1().getTime().getNome(),Toast.LENGTH_SHORT).show();
            }*/

            usuarioDAO.inserirPonto(confronto.getUsuario1(),qntPonto);
            Toast.makeText(getApplicationContext(),"Vitoria do "+confronto.getUsuario1().getTime().getNome(),Toast.LENGTH_SHORT).show();
        }else if(placarT2>placarT1){
            /*int idUsuario = confronto.getUsuario2().getId();*/
            double qntPonto = 3;

           /* if (usuarioDAO.inserirPonto( confronto.getUsuario2(),qntPonto)){
                Toast.makeText(getApplicationContext(),"Vitoria do "+confronto.getUsuario2().getTime().getNome(),Toast.LENGTH_SHORT).show();
            }*/

            usuarioDAO.inserirPonto( confronto.getUsuario2(),qntPonto);
            Toast.makeText(getApplicationContext(),"Vitoria do "+confronto.getUsuario2().getTime().getNome(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Empate",Toast.LENGTH_SHORT).show();
        }

        /*pontos por gol time 1*/
        /*int idUsuario = confronto.getUsuario1().getId();*/
        double sub = placarT2 * 0.5;
        double qntPonto = placarT1 - sub;
       /* if(usuarioDAO.inserirPonto(confronto.getUsuario1(),qntPonto)){
            Toast.makeText(getApplicationContext(),"Pontos do "+confronto.getUsuario1().getTime().getNome()+" inseridos",Toast.LENGTH_SHORT).show();
        }*/

        usuarioDAO.inserirPonto(confronto.getUsuario1(),qntPonto);
        Toast.makeText(getApplicationContext(),"Pontos do "+confronto.getUsuario1().getTime().getNome()+" inseridos",Toast.LENGTH_SHORT).show();

        /*pontos por gol time 2*/
        /*int idUsuario2 = confronto.getUsuario2().getId();*/
        double sub2 = placarT1 * 0.5;
        double qntPonto2 = placarT2 - sub2;
        /*if(usuarioDAO.inserirPonto(confronto.getUsuario2(),qntPonto)){
            Toast.makeText(getApplicationContext(),"Pontos do "+confronto.getUsuario2().getTime().getNome()+" inseridos",Toast.LENGTH_SHORT).show();
        }*/

        usuarioDAO.inserirPonto(confronto.getUsuario2(),qntPonto2);
        Toast.makeText(getApplicationContext(),"Pontos do "+confronto.getUsuario2().getTime().getNome()+" inseridos",Toast.LENGTH_SHORT).show();
        usuarioDAO.close();
    }



    private void preencheSpinner1Jogador(int idTime) {

        JogadorDAO JogadorDAO = new JogadorDAO(getApplicationContext());
        JogadorDAO.open();
        lista = JogadorDAO.findAllIdTime(idTime);
        String[] times = new String[lista.size()];

        int idx = 0;


        for (Jogador j : lista) {
            times[idx++] = j.getNome();
        }
        JogadorDAO.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                times);


        spinnerJogadores1.setAdapter(adapter);

    }

    private void preencheSpinner2Jogador(int idTime2) {

        JogadorDAO JogadorDAO = new JogadorDAO(getApplicationContext());
        JogadorDAO.open();
        lista2 = JogadorDAO.findAllIdTime(idTime2);
        String[] times = new String[lista2.size()];

        int idx = 0;


        for (Jogador j : lista2) {
            times[idx++] = j.getNome();
        }
        
        JogadorDAO.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                times);


        spinnerJogadores2.setAdapter(adapter);

    }

    private Confronto RetornarConfronto(){
        Confronto confronto = (Confronto) getIntent().getExtras().getSerializable("confronto");
        return confronto;
    }



    private void binding() {
        spinnerJogadores1 = findViewById(R.id.spinnerJogadores1);
        spinnerJogadores2 = findViewById(R.id.spinnerJogadores2);
        tvTime1Gols = findViewById(R.id.tvTime1Gols);
        tvTime2Gols = findViewById(R.id.tvTime2Gols);
        btnGravarGolsT1 = findViewById(R.id.btnGravarGolsT1);
        btnGravarGolsT2 = findViewById(R.id.btnGravarGolsT2);
        txvPlacarTime1= findViewById(R.id.txvPlacarTime1);
        txvPlacarTime2= findViewById(R.id.txvPlacarTime2);
        btnVoltar = findViewById(R.id.btnVoltar);
        bolaGif = findViewById(R.id.bolaGif);
    }
}
