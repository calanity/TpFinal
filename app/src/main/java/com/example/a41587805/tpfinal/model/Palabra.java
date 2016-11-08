package com.example.a41587805.tpfinal.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 41587805 on 8/11/2016.
 */
public class Palabra implements Serializable {
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    String palabra;
    int Id;

}
