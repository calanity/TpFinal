package com.example.a41587805.tpfinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by 41587805 on 8/11/2016.
 */
public class Palabra implements Serializable {
    public Palabra() {
        palabra = new ArrayList<>();
    }

    public Palabra(String pal) {
        this.pal = pal;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }


    public ArrayList<letra> getPalabra() {
        return palabra;
    }

    public void setPalabra(ArrayList<letra> palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    ArrayList<letra> palabra;
    String pal;
    int Id;


    public void addLetra(letra l) {
        palabra.add(l);

    }

    public  boolean comparar(ArrayList<letra> palabraComparar){
        // Check for sizes and nulls
        if ((palabra.size() != palabraComparar.size()) || (palabra == null && palabraComparar!= null) ||
                (palabra != null && palabraComparar== null)){
            return false;
        }

        // Sort and compare the two lists
        letra l1,l2;
        for (int i=0; i<palabra.size();i++) {
            l1 = palabra.get(i);
            l2 = palabraComparar.get(i);
            if (!l1.esIgual(l2)) return false;
        }

        return true;
    }

    public int contieneLetra(letra l) {
        letra l1;
        int posicion=-1;
        for (int i=0; i<palabra.size();i++) {
            l1 = palabra.get(i);
            if (!l1.esIgual(l))
                return posicion;
            else
                posicion=i;
        }
        return posicion;
    }

}
