package com.example.a41587805.tpfinal.model;

import android.util.Log;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

/**
 * Created by 41587805 on 27/9/2016.
 */
public class ClaseJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite PersonaJugador,ImagenFondo, Letra;
    String letr ;
    Label tituloJuego;
        public ClaseJuego(CCGLSurfaceView VistaDelJuego)
        {
            Log.d("bob" , "comienza el juego");
            _VistaDelJuego =VistaDelJuego;
        }
    public void ComenzarJuego()
    {
        letr= "א";
        Log.d("comienza el juego" , "comenzar");
        Director.sharedDirector().attachInView(_VistaDelJuego);
        PantallaDelDispositivo= Director.sharedDirector().displaySize();

        Director.sharedDirector().runWithScene(Inicio());

    }

    private Scene Inicio()
    {
        Scene escenaDevolver= Scene.node();
        CapaFondo capaFondo= new CapaFondo();
        CapaJuego capaJuego= new CapaJuego();
        escenaDevolver.addChild(capaFondo, -10);
        escenaDevolver.addChild(capaJuego, 10);
        return escenaDevolver;
    }

    class CapaFondo extends Layer
    {
        public CapaFondo()
        {
           PonerImagenFondo();

        }


        private void PonerImagenFondo()
    {
        ImagenFondo= Sprite.sprite("kotel.jpg");
        ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);
        ImagenFondo.runAction(ScaleBy.action(0.01f, 2.7f, 2.8f));
        super.addChild(ImagenFondo);
    }



    }
    class CapaJuego extends Layer
    {
        public CapaJuego()
        {
            //jhkikhkhjh
            PonerPersonaPosicionInicial();
            PonerLetra();
        }
        private void  PonerPersonaPosicionInicial()
        {
            PersonaJugador= Sprite.sprite("persona.png");
            float PosicionInicialX, PosicionInicialY;
            PosicionInicialX= PantallaDelDispositivo.width/2;
            PosicionInicialY=PersonaJugador.getHeight()/2;
            PersonaJugador.setPosition(PosicionInicialX, PosicionInicialY);
            super.addChild(PersonaJugador);
        }

        private void PonerTituloJuego()
        {
            tituloJuego=Label.label("Bienvenido" , "Verdana" , 30);
            float AltoDelTitulo= tituloJuego.getHeight();
            tituloJuego.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height-AltoDelTitulo/2);
            super.addChild(tituloJuego);
        }

       public void PonerLetra()
        {
            Letra= Sprite.sprite("letra.png");
            CCPoint PosicionInicial= new CCPoint();
            PosicionInicial.y= PantallaDelDispositivo.height;
            PosicionInicial.x= PantallaDelDispositivo.width/2;
            Letra.setPosition(PosicionInicial.x, PosicionInicial.y);

            float PosicionFinalX, PosicionFinalY;
            PosicionFinalX= PosicionInicial.x -10;
            PosicionFinalY= -150f;
            Letra.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));

            addChild(Letra);

        }

    }


}
