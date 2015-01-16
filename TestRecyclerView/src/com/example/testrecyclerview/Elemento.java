package com.example.testrecyclerview;

import com.example.testrecyclerview.soporte.Identificable;

/**
 * Created by Amura on 23/12/2014.
 */


public class Elemento implements Identificable {

    private String texto;
    private int id;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Elemento(int id, String texto) {

        this.id = id;
        this.texto = texto;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
