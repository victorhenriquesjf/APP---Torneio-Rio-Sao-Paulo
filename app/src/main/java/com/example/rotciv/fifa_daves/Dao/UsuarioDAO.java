package com.example.rotciv.fifa_daves.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Time;
import com.example.rotciv.fifa_daves.model.Usuario;
import com.example.rotciv.fifa_daves.util.DataBase;

import java.util.ArrayList;
import java.util.List;



public class UsuarioDAO {
    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public UsuarioDAO(Context context){
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

    public Boolean gravarUsuario(Usuario u){
        try {
            String gravarUsuario = "INSERT INTO "+DataBase.TABELA_USUARIO+" ("+DataBase.TABELA_USUARIO_NOME+","+
                    DataBase.TABELA_USUARIO_TIME+") VALUES('"+u.getNome()+"',"+u.getTime().getId()+")";
                    conexao.execSQL(gravarUsuario);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public  int count() {

        String sql= "select count(*) from "+DataBase.TABELA_USUARIO+";";

        Cursor cursor =  conexao.rawQuery(sql,null);

        int valor = 0;

        if(cursor.moveToFirst())
        {
            valor = cursor.getInt(0);
        }

        return valor;
    }

    public List<Usuario> findAll(){
        String buscarTodos = "SELECT * FROM "+DataBase.TABELA_USUARIO+";";
        Cursor cursor = conexao.rawQuery(buscarTodos,null);
        List<Usuario> lista = new ArrayList<>();

        TimeDAO td = new TimeDAO(context);
        td.open();
        while(cursor.moveToNext())
        {
            Usuario u = new Usuario();
            u.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_ID)));
            u.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_USUARIO_NOME)));
            Time t = td.buscarPorId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_TIME)));
            u.setTime(t);
            lista.add(u);
        }
        td.close();
        return lista;
    }

    public Usuario buscarUserID(int id){
        String buscarEspecifico = "SELECT * FROM "+DataBase.TABELA_USUARIO+" where id = "+id+";";
        Cursor cursor = conexao.rawQuery(buscarEspecifico,null);
        TimeDAO timeDAO = new TimeDAO(context);
        timeDAO.open();

        Usuario u = new Usuario();

        while(cursor.moveToNext()){
            u.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_ID)));
            u.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_USUARIO_NOME)));
            u.setPonto(cursor.getDouble(cursor.getColumnIndex(DataBase.TABELA_USUARIO_PONTO)));
            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_TIME)));
            u.setTime(timeDAO.buscarPorId(t.getId()));
        }
        return u;
    }

    public Usuario buscarUsuarioPorTime(int ID_USUARIO_TIME ){
        String sql= "SELECT * FROM "+DataBase.TABELA_USUARIO+" WHERE "+DataBase.TABELA_USUARIO_TIME+" = "+ID_USUARIO_TIME;
        Cursor cursor = conexao.rawQuery(sql,null);
        TimeDAO timeDAO = new TimeDAO(context);
        timeDAO.open();

        Usuario u = new Usuario();

        while(cursor.moveToNext()){
            u.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_ID)));
            u.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_USUARIO_NOME)));
            u.setPonto(cursor.getDouble(cursor.getColumnIndex(DataBase.TABELA_USUARIO_PONTO)));
            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_TIME)));
            u.setTime(timeDAO.buscarPorId(t.getId()));
        }
        return u;
    }


    public Usuario inserirPonto(Usuario usuario, double ponto){
        usuario.setPonto( usuario.getPonto() + ponto);
        try{
            String[] values = {String.valueOf(usuario.getId())};
            ContentValues insertValues = new ContentValues();

            insertValues.put(DataBase.TABELA_USUARIO_PONTO,usuario.getPonto());

            banco.getWritableDatabase().update(DataBase.TABELA_USUARIO, insertValues, DataBase.TABELA_USUARIO_ID+" = ?", values);
            return usuario;
        }catch (Exception ex) {
            return null;
        }
    }

    public List<Usuario> Ranquear(){
        String sql = "SELECT * FROM "+DataBase.TABELA_USUARIO+ " ORDER BY "+DataBase.TABELA_USUARIO_PONTO+" DESC;";
        Cursor cursor = conexao.rawQuery(sql,null);
        List<Usuario> lista = new ArrayList<>();

        TimeDAO td = new TimeDAO(context);
        td.open();

        while(cursor.moveToNext())
        {
            Usuario u = new Usuario();
            u.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_ID)));
            u.setNome(cursor.getString(cursor.getColumnIndex(DataBase.TABELA_USUARIO_NOME)));

            Time t = new Time();
            t.setId(cursor.getInt(cursor.getColumnIndex(DataBase.TABELA_USUARIO_TIME)));
            u.setTime(td.buscarPorId(t.getId()));
            lista.add(u);

            u.setPonto(cursor.getDouble(cursor.getColumnIndex(DataBase.TABELA_USUARIO_PONTO)));
        }
        td.close();
        return lista;
    }

}
