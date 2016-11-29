package com.example.a41587805.tpfinal.model;

import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.Texture2D;

/**
 * Created by 41587805 on 8/11/2016.
 */
public class letra
{
    Sprite sprite;
    String letra;
    int id;

    public letra(String imagenPath, String letra) {

        this.sprite = Sprite.sprite(imagenPath);
        this.letra = letra;
    }

    public Sprite getSprite() {
        return sprite;
    }


    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean esIgual(letra l2) {

        return letra.equals(l2.getLetra());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
