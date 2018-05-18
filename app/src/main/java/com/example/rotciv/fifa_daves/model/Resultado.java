package com.example.rotciv.fifa_daves.model;

import java.io.Serializable;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class Resultado implements Serializable {
        private int id,PlacarTime1,PlacarTime2;
        private Confronto confronto;


    public Resultado(int id, int PlacarTime1, int PlacarTime2, Confronto confronto) {
        this.id = id;
        this.PlacarTime1 = PlacarTime1;
        this.PlacarTime2 = PlacarTime2;
        this.confronto = confronto;
    }

    public Resultado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlacarTime1() {
        return PlacarTime1;
    }

    public void setPlacarTime1(int PlacarTime1) {
        this.PlacarTime1 = PlacarTime1;
    }

    public int getPlacarTime2() {
        return PlacarTime2;
    }

    public void setPlacarTime2(int PlacarTime2) {
        this.PlacarTime2 = PlacarTime2;
    }

    public Confronto getConfronto() {
        return confronto;
    }

    public void setConfronto(Confronto confronto) {
        this.confronto = confronto;
    }


}
