package com.example.rotciv.fifa_daves.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.util.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class TimeDAO {
    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public TimeDAO(Context context)
    {
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

    public List<Time> findAll(){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_TIME+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Time> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_TIME_ID)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_TIME_NOME)));
            lista.add(t);
        }
        return lista;
    }


    public List<Time> findAllnaoSelecionado(){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_TIME+" where "+ DataBase.TABELA_TIME_ID + " not in (select "+DataBase.TABELA_USUARIO_TIME+" FROM "+ DataBase.TABELA_USUARIO + ")";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Time> lista = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_TIME_ID)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_TIME_NOME)));
            lista.add(t);
        }
        return lista;
    }



    public  int count() {

        String sql= "select count(*) from "+DataBase.TABELA_TIME+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public Time buscarPorId(int id) {
        String sql= "select * from "+DataBase.TABELA_TIME+" where id = "+id+";";
        Cursor cursor = conexao.rawQuery(sql,null);
        Time t = new Time();
        while(cursor.moveToNext())
        {
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_TIME_ID)));
            t.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_TIME_NOME)));
        }
        return t;
    }

}
