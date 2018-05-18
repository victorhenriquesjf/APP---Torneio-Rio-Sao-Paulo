package com.example.rotciv.fifa_daves.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class DataBase extends SQLiteOpenHelper{
    public static final String dataBaseName = "fifa.db";
    public static final int dataBaseVersion= 1;
    public static final String TABELA_USUARIO = "usuario";
    public static final String TABELA_TIME = "time";
    public static final String TABELA_CONFRONTO = "confronto";
    public static final String TABELA_RESULTADO = "resultado";
    public static final String TABELA_JOGADOR = "jogador";
    public static final String TABELA_GOL = "gol";
    public static final String TABELA_USUARIO_ID = "id";
    public static final String TABELA_USUARIO_NOME = "nome";
    public static final String TABELA_USUARIO_TIME = "idTime";
    public static final String TABELA_USUARIO_PONTO = "ponto";
    public static final String TABELA_TIME_ID = "id";
    public static final String TABELA_TIME_NOME = "nome";
    public static final String TABELA_CONFRONTO_ID = "id";
    public static final String TABELA_CONFRONTO_USUARIO1 = "idUsuarioUm";
    public static final String TABELA_CONFRONTO_USUARIO2 = "idUsuarioDois";
    public static final String TABELA_RESULTADO_ID = "id";
    public static final String TABELA_RESULTADO_GOL1 = "golum";
    public static final String TABELA_RESULTADO_GOL2 = "goldois";
    public static final String TABELA_RESULTADO_CONFRONTO = "idConfronto";
    public static final String TABELA_JOGADOR_ID = "id";
    public static final String TABELA_JOGADOR_NOME = "nome";
    public static final String TABELA_JOGADOR_TIME = "idTime";
    public static final String TABELA_JOGADOR_GOL = "gol";
    public static final String TABELA_GOL_ID = "id";
    public static final String TABELA_GOL_QUANTIDADE = "quantidade";
    public static final String TABELA_GOL_JOGADOR = "jogador";
    public static final String TABELA_GOL_CONFRONTO = "confronto";



    public DataBase(Context context) {
        super(context, dataBaseName, null, dataBaseVersion);
    }

    String sqlCreateUsuario = "CREATE TABLE "+TABELA_USUARIO+" ("+
            ""+TABELA_USUARIO_ID+ " integer primary key autoincrement, "+
            TABELA_USUARIO_NOME+ " text not null, "+
            TABELA_USUARIO_TIME+" integer not null, "+
            TABELA_USUARIO_PONTO+" real);";

    String sqlCreateTime = "CREATE TABLE "+TABELA_TIME+""+
            "("+TABELA_TIME_ID+ " integer primary key, "+
            TABELA_TIME_NOME+ " text not null"+
            ");";

    String sqlCreateConfronto = "CREATE TABLE "+TABELA_CONFRONTO+""+
            "("+TABELA_CONFRONTO_ID+ " integer primary key , "+
            TABELA_CONFRONTO_USUARIO1+ " integer not null, "+
            TABELA_CONFRONTO_USUARIO2+ " integer not null"+
            ");";

    String sqlCreateResultado = "CREATE TABLE "+TABELA_RESULTADO+""+
            "("+TABELA_RESULTADO_ID+ " integer primary key autoincrement, "+
            TABELA_RESULTADO_GOL1+ " integer not null, "+
            TABELA_RESULTADO_GOL2+ " integer not null, "+
            TABELA_RESULTADO_CONFRONTO+ " integer not null"+
            ");";

    String sqlCreateJogador = "CREATE TABLE "+TABELA_JOGADOR+""+
            "("+TABELA_JOGADOR_ID+ " integer primary key, "+
            TABELA_JOGADOR_NOME+ " integer not null, "+
            TABELA_JOGADOR_TIME+ " integer not null, "+
            TABELA_JOGADOR_GOL+ " integer not null"+
            ");";

    String sqlCreateGol = "CREATE TABLE "+TABELA_GOL+""+
            "("+TABELA_GOL_ID+ " integer primary key autoincrement, "+
            TABELA_GOL_QUANTIDADE+ " integer not null, "+
            TABELA_GOL_JOGADOR+ " integer not null, "+
            TABELA_GOL_CONFRONTO+ " integer not null"+
            ");";

    String inserirTime = "INSERT INTO "+TABELA_TIME+"( "+TABELA_TIME_ID+" , "+TABELA_TIME_NOME+" ) VALUES (1,'Santos'),(2,'Flamengo'),(3,'Botafogo'),(4,'São Paulo');";

    String inserirJogador = "INSERT INTO "+TABELA_JOGADOR+"( "+TABELA_JOGADOR_ID+" , "+TABELA_JOGADOR_NOME+" , "+TABELA_JOGADOR_TIME+" , "+TABELA_JOGADOR_GOL+" ) VALUES (1,'Pelé',1,0),(2,'Zico',2,0),(3,'Garrincha',3,0),(4,'Rogério Ceni',4,0);";
    String inserirJogador2 = "INSERT INTO "+TABELA_JOGADOR+"( "+TABELA_JOGADOR_ID+" , "+TABELA_JOGADOR_NOME+" , "+TABELA_JOGADOR_TIME+" , "+TABELA_JOGADOR_GOL+" ) VALUES (5,'Coutinho',1,0),(6,'Adilio',2,0),(7,'Nilton Santos',3,0),(8,'Rai',4,0);";
    String inserirJogador3 = "INSERT INTO "+TABELA_JOGADOR+"( "+TABELA_JOGADOR_ID+" , "+TABELA_JOGADOR_NOME+" , "+TABELA_JOGADOR_TIME+" , "+TABELA_JOGADOR_GOL+" ) VALUES (9,'Pepe',1,0),(10,'Andrade',2,0),(11,'Tulio Maravilha',3,0),(12,'Luis Fabiano',4,0);";
    String inserirJogador4 = "INSERT INTO "+TABELA_JOGADOR+"( "+TABELA_JOGADOR_ID+" , "+TABELA_JOGADOR_NOME+" , "+TABELA_JOGADOR_TIME+" , "+TABELA_JOGADOR_GOL+" ) VALUES (13,'Abel',1,0),(14,'Junior',2,0),(15,'Heleno de Freitas',3,0),(16,'Careca',4,0);";
    String inserirJogador5 = "INSERT INTO "+TABELA_JOGADOR+"( "+TABELA_JOGADOR_ID+" , "+TABELA_JOGADOR_NOME+" , "+TABELA_JOGADOR_TIME+" , "+TABELA_JOGADOR_GOL+" ) VALUES (17,'Ailton',1,0),(18,'Leonardo',2,0),(19,'Didi',3,0),(20,'Toninho Cerezo',4,0);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateTime);
        db.execSQL(sqlCreateConfronto);
        db.execSQL(sqlCreateResultado);
        db.execSQL(sqlCreateJogador);
        db.execSQL(inserirTime);

        db.execSQL(inserirJogador);
        db.execSQL(inserirJogador2);
        db.execSQL(inserirJogador3);
        db.execSQL(inserirJogador4);
        db.execSQL(inserirJogador5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onCreate(db);
    }

}
