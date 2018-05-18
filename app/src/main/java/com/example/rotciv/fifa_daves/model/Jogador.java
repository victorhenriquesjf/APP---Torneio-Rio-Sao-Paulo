package com.example.rotciv.fifa_daves.model;

import java.io.Serializable;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class Jogador implements Serializable {
    private int id;
    private String nome;
    private Time time;
    private int gol;

    public Jogador(int id, String nome, Time time, int gol) {
        this.id = id;
        this.nome = nome;
        this.time = time;
        this.gol = gol;
    }

    public Jogador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }
}
