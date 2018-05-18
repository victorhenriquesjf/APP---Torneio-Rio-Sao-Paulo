package com.example.rotciv.fifa_daves.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.model.Usuario;
import com.example.rotciv.fifa_daves.util.DataBase;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class ConfrontoDAO {
    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public ConfrontoDAO(Context context){
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

    public Boolean gravarConfronto(Confronto c){
        try {
            String gravarConfronto = "INSERT INTO "+DataBase.TABELA_CONFRONTO+" ("+DataBase.TABELA_CONFRONTO_USUARIO1+","+
                    DataBase.TABELA_CONFRONTO_USUARIO2+") VALUES("+c.getUsuario1().getId()+","+c.getUsuario2().getId()+")";
            conexao.execSQL(gravarConfronto);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Confronto buscarConfID(int id){
        String buscarEspecifico = "SELECT * FROM "+DataBase.TABELA_CONFRONTO+" where id ="+id+";";
        Cursor cursor = conexao.rawQuery(buscarEspecifico,null);
        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        usuarioDAO.open();
        Confronto confronto = new Confronto();

        while(cursor.moveToNext()){
            confronto.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_CONFRONTO_ID)));

            Usuario u1 = new Usuario();
            u1.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_CONFRONTO_USUARIO1)));
            confronto.setUsuario1(usuarioDAO.buscarUserID(u1.getId()));

            Usuario u2 = new Usuario();
            u2.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_CONFRONTO_USUARIO2)));
            confronto.setUsuario2(usuarioDAO.buscarUserID(u2.getId()));
        }
        return confronto;
    }
}
