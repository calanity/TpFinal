package com.example.a41587805.tpfinal.model;

import org.cocos2d.nodes.Sprite;

/**
 * Created by 41587805 on 8/11/2016.
 */
public class letra  {
    public Sprite getTextura() {
        return textura;
    }

    public void setTextura(Sprite textura) {
        this.textura = textura;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    Sprite textura;
    String letra;


}
