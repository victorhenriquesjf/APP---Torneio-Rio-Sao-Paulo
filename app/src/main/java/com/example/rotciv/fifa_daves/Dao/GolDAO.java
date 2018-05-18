package com.example.rotciv.fifa_daves.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Gol;
import com.example.rotciv.fifa_daves.model.Jogador;
import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.util.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class GolDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public GolDAO(Context context){
        banco = new DataBase(context);
        this.context=context;
    }
    public void open()
    {
        conexao = banco.getWritableDatabase();
    }
    public void close()
    {
        conexao.close();
    }


    public Boolean gravarGol(Gol g){
        try {
            String gravarJogador = "INSERT INTO "+DataBase.TABELA_GOL+" ( "+
                    DataBase.TABELA_GOL_QUANTIDADE+" , "+
                    DataBase.TABELA_GOL_JOGADOR+" , "+
                    DataBase.TABELA_GOL_CONFRONTO+" ) VALUES ( "+
                    g.getQuantidade()+" , "+
                    g.getJogador().getId()+" , "+
                    g.getConfronto().getId()+" )";
            conexao.execSQL(gravarJogador);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public  int count() {

        String sql= "select count(*) from "+DataBase.TABELA_GOL+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public List<Gol> findAll(){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_GOL;
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Gol> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Gol g = new Gol();
            g.setIdGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_ID)));
            g.setQuantidade(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_QUANTIDADE)));

            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_JOGADOR)));

            Confronto c = new Confronto();
            c.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_CONFRONTO)));

            lista.add(g);
        }
        return lista;
    }

    public List<Gol> findAllId(int id){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_GOL+" where id = "+id+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Gol> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Gol g = new Gol();
            g.setIdGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_ID)));
            g.setQuantidade(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_QUANTIDADE)));

            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_JOGADOR)));

            Confronto c = new Confronto();
            c.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_CONFRONTO)));

            lista.add(g);
        }
        return lista;
    }

    public  int countId(int id) {

        String sql = "SELECT count(*) FROM "+DataBase.TABELA_GOL+" where id = "+id+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public List<Gol> BuscarGolsdoConfronto(int id){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_GOL+" where confronto = "+id+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Gol> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Gol g = new Gol();
            g.setIdGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_ID)));
            g.setQuantidade(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_QUANTIDADE)));

            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_JOGADOR)));

            Confronto c = new Confronto();
            c.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_GOL_CONFRONTO)));

            lista.add(g);
        }
        return lista;
    }


}
