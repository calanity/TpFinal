package com.example.a41587805.tpfinal.model;

import android.util.Log;

import org.cocos2d.actions.Scheduler;
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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 41587805 on 27/9/2016.
 */
public class ClaseJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite PersonaJugador,ImagenFondo, Letra;
    String letr ;
    Label tituloJuego;
    ArrayList<Sprite>arrEnemigos;

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
            arrEnemigos= new ArrayList<>();

            TimerTask PonerUnaLetra= new TimerTask() {
                @Override
                public void run() {
                    PonerLetra();
                }
            };
            Timer Reloj= new Timer();
            Reloj.schedule(PonerUnaLetra, 0, 1000);

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
            float PosicionFinalX, PosicionFinalY;

            Letra= Sprite.sprite("letra.png");
            float alturaLetra= Letra.getHeight();
            float anchoLetra= Letra.getWidth();

            CCPoint PosicionInicial= new CCPoint();
            PosicionInicial.y= PantallaDelDispositivo.height + alturaLetra/2;
            Random generadorDeAzar=new Random();

            PosicionInicial.x= generadorDeAzar.nextInt((int)(PantallaDelDispositivo.width - anchoLetra/2));
            Letra.setPosition(PosicionInicial.x, PosicionInicial.y);

            PosicionFinalX= PosicionInicial.x -10;
            PosicionFinalY= -150f;
            Letra.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));
            arrEnemigos.add(Letra);
            addChild(Letra);

        }

    }

    boolean InterseccionEntreSprites (Sprite Sprite1, Sprite Sprite2) {

        boolean Devolver;

        Devolver=false;

        int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;

        int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;

        Sprite1Izquierda=(int) (Sprite1.getPositionX() - Sprite1.getWidth()/2);

        Sprite1Derecha=(int) (Sprite1.getPositionX() + Sprite1.getWidth()/2);

        Sprite1Abajo=(int) (Sprite1.getPositionY() - Sprite1.getHeight()/2);

        Sprite1Arriba=(int) (Sprite1.getPositionY() + Sprite1.getHeight()/2);

        Sprite2Izquierda=(int) (Sprite2.getPositionX() - Sprite2.getWidth()/2);

        Sprite2Derecha=(int) (Sprite2.getPositionX() + Sprite2.getWidth()/2);

        Sprite2Abajo=(int) (Sprite2.getPositionY() - Sprite2.getHeight()/2);

        Sprite2Arriba=(int) (Sprite2.getPositionY() + Sprite2.getHeight()/2);



//Borde izq y borde inf de Sprite 1 está dentro de Sprite 2

        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&

        EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {



            Devolver=true;

        }

//Borde izq y borde sup de Sprite 1 está dentro de Sprite 2

        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&

        EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {



            Devolver=true;

        }

//Borde der y borde sup de Sprite 1 está dentro de Sprite 2

        if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&

        EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {



            Devolver=true;

        }

//Borde der y borde inf de Sprite 1 está dentro de Sprite 2

        if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&

        EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {


            Devolver=true;

        }

//Borde izq y borde inf de Sprite 2 está dentro de Sprite 1

        if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&

        EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {


            Devolver=true;

        }

//Borde izq y borde sup de Sprite 1 está dentro de Sprite 1

        if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&

        EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {



            Devolver=true;

        }

//Borde der y borde sup de Sprite 2 está dentro de Sprite 1

        if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&

        EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {



            Devolver=true;

        }

//Borde der y borde inf de Sprite 2 está dentro de Sprite 1

        if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&

        EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {



            Devolver=true;

        }

        return Devolver;

    }


    boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor)
    {
     boolean devolver;
        if (NumeroMenor>NumeroMayor)
        {
         int aux= NumeroMayor;
            NumeroMayor= NumeroMenor;
            NumeroMenor= aux;
        }
        if(NumeroAComparar>=NumeroMenor && NumeroAComparar<=NumeroMayor)
        {
            devolver= true;
        }
        else
        {
            devolver= false;
        }
        return devolver;
    }


}
