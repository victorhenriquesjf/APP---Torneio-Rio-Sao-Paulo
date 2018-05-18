package com.example.rotciv.fifa_daves.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Resultado;
import com.example.rotciv.fifa_daves.util.DataBase;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class ResultadoDAO {
    private SQLiteDatabase conexao;
    private DataBase banco;
    private Context context;

    public ResultadoDAO(Context context)
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

    public Boolean gravarResultado(Resultado r){
        try {
            String gravarResultado = "INSERT INTO "+DataBase.TABELA_RESULTADO+" ("+DataBase.TABELA_RESULTADO_GOL1+","+
                    DataBase.TABELA_RESULTADO_GOL2+","+DataBase.TABELA_RESULTADO_CONFRONTO+") VALUES("+r.getPlacarTime1()+","+r.getPlacarTime2()+","+r.getConfronto().getId()+")";
            conexao.execSQL(gravarResultado);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
