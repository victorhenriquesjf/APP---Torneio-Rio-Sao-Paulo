package com.example.rotciv.fifa_daves.model;

import java.io.Serializable;

/**
 * Created by Rotciv on 07/04/2018.
 */

public class Confronto implements Serializable {
    private int id;
    private Usuario usuario1,usuario2;

    public Confronto(int id, Usuario usuario1, Usuario usuario2) {
        this.id = id;
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    public Confronto( Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    public Confronto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
}
