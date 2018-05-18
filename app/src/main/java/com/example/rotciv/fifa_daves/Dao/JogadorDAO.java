package com.example.rotciv.fifa_daves.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Jogador;
import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.model.Usuario;
import com.example.rotciv.fifa_daves.util.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class JogadorDAO {
    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public JogadorDAO(Context context){
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

    public Boolean gravarJogador(Jogador j){
        try {
            String gravarJogador = "INSERT INTO "+DataBase.TABELA_JOGADOR+" ("+DataBase.TABELA_JOGADOR_NOME+","+
                    DataBase.TABELA_JOGADOR_TIME+") VALUES("+j.getNome()+","+j.getTime().getId()+")";
            conexao.execSQL(gravarJogador);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    public  int count() {
        String sql= "select count(*) from "+DataBase.TABELA_JOGADOR+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }
    public List<Jogador> findAll(){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_JOGADOR+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Jogador> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_ID)));
            j.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_NOME)));
            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_TIME)));
            lista.add(j);
        }
        return lista;
    }


    public List<Jogador> findAllIdTime(int id){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_JOGADOR+" where "+DataBase.TABELA_JOGADOR_TIME +" = "+id+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Jogador> lista = new ArrayList<>();

        TimeDAO timeDAO = new TimeDAO(context);
        timeDAO.open();
        while(cursor.moveToNext())
        {
            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_ID)));
            j.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_NOME)));

            Time t = timeDAO.buscarPorId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_TIME)));
            j.setTime(t);

            j.setGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_GOL)));

            lista.add(j);
        }
        timeDAO.close();
        return lista;
    }

    public  int maximoGols() {

        String sql = "SELECT MAX("+DataBase.TABELA_JOGADOR_GOL+") FROM "+DataBase.TABELA_JOGADOR;

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }


    public List<Jogador> findArtilheiro(){
        int gol = maximoGols();
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_JOGADOR+" WHERE "+DataBase.TABELA_JOGADOR_GOL +" BETWEEN "+gol+" AND "+gol;
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Jogador> lista = new ArrayList<>();

        TimeDAO timeDAO = new TimeDAO(context);
        timeDAO.open();
        while(cursor.moveToNext())
        {
            Jogador j = new Jogador();
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_ID)));
            j.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_NOME)));

            Time t = timeDAO.buscarPorId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_TIME)));
            j.setTime(t);

            j.setGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_GOL)));

            lista.add(j);
        }
        timeDAO.close();
        return lista;
    }

    public  int countId(int id) {

        String sql = "SELECT count(*) FROM "+DataBase.TABELA_JOGADOR+" where id = "+id+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public boolean marcarGol (Jogador jogador){
        jogador.setGol( jogador.getGol() + 1);
        try {
            String[] values = {String.valueOf(jogador.getId())};
            ContentValues insertValues = new ContentValues();
            insertValues.put(DataBase.TABELA_JOGADOR_GOL, jogador.getGol());
            banco.getWritableDatabase().update(DataBase.TABELA_JOGADOR, insertValues, DataBase.TABELA_JOGADOR_ID+" = ?", values);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Jogador buscarJogador(int idJogador) {
        String sql= "SELECT * FROM "+DataBase.TABELA_JOGADOR+" WHERE "+DataBase.TABELA_JOGADOR_ID+" = "+idJogador;
        Cursor cursor = conexao.rawQuery(sql,null);
        TimeDAO timeDAO = new TimeDAO(context);
        timeDAO.open();

        Jogador j = new Jogador();

        while(cursor.moveToNext()){
            j.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_ID)));
            j.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_NOME)));

            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_TIME)));
            j.setTime(timeDAO.buscarPorId(t.getId()));

            j.setGol(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_JOGADOR_GOL)));
        }

        return j;
    }

}
