package com.example.a41587805.tpfinal.model;

import android.util.Log;

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
        int cont=3;
        //Log.d("comparar palabra",palabraComparar.toString() + " vs " + palabra.toString());
        // Check for sizes and nulls
        if ((palabra.size() != palabraComparar.size()) || (palabra == null && palabraComparar!= null) ||
                (palabra != null && palabraComparar== null)){
            return false;
        }
        else
        {
            return true;
        }


        //por cada letra de la palabra, si existe en palabra momentanea las 3, gana
        /*for (int i=1; i<palabra.size()+1;i++)
        {
            if(palabra.contains(palabraComparar.get(i)))
            {
                cont++;
                if(cont==3)
                {
                    return true;
                }

            }
        }
*/

    }

    public int contieneLetra(letra l) {
        letra l1;
        Log.d("detectarColis","contiene Letra size:" +palabra.size());
        for (int i=0; i<palabra.size();i++) {
            l1 = palabra.get(i);
            Log.d("detectarColis","contiene Letra:" + l.getLetra() + " vs:" + l1.getLetra());
            if (l1.esIgual(l)) {
                Log.d("detectarColis","contiene Letra: es igual!!!");
                return i;
            }

        }
        return -1;
    }

}

//// Sort and compare the two lists
        /*letra l1,l2;
        for (int i=0; i<palabra.size();i++) {
            l1 = palabra.get(i);
            l2 = palabraComparar.get(i);
            if (!l1.esIgual(l2)) return false;
        }*/