package com.example.rotciv.fifa_daves.model;

import java.io.Serializable;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class Gol implements Serializable{

    private int IdGol;
    private int Quantidade;
    private Jogador jogador;
    private Confronto confronto;


    public Gol() {
    }

    public Gol(int idGol, int quantidade, Jogador jogador, Confronto confronto) {
        IdGol = idGol;
        Quantidade = quantidade;
        this.jogador = jogador;
        this.confronto = confronto;
    }

    public int getIdGol() {
        return IdGol;
    }

    public void setIdGol(int idGol) {
        IdGol = idGol;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Confronto getConfronto() {
        return confronto;
    }

    public void setConfronto(Confronto confronto) {
        this.confronto = confronto;
    }
}
