package com.example.rotciv.fifa_daves.model;

import java.io.Serializable;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class Usuario implements Serializable {
    private int id;
    private Time time;
    private String nome;
    private double ponto;


    public Usuario(int id, Time time, String nome) {
        this.id = id;
        this.time = time;
        this.nome = nome;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPonto() {
        return ponto;
    }

    public void setPonto(double ponto) {
        this.ponto = ponto;
    }
}
