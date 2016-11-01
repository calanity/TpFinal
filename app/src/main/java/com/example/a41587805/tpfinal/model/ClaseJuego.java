package com.example.a41587805.tpfinal.model;

import android.util.Log;
import android.view.MotionEvent;

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
import java.util.List;
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
    Label tituloJuego, puntosJuego;
    ArrayList<Sprite>arrEnemigos;
    int contador;

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
    class CapaJuego extends Layer {
        float PosicionInicialX, PosicionInicialY;

        public CapaJuego() {
            //jhkikhkhjh
            PonerPersonaPosicionInicial();
            arrEnemigos = new ArrayList<>();

            TimerTask PonerUnaLetra = new TimerTask() {
                @Override
                public void run() {
                    PonerLetra();
                }
            };
            Timer Reloj = new Timer();
            Reloj.schedule(PonerUnaLetra, 0, 1000);

            TimerTask VerificarImpactos = new TimerTask() {
                @Override
                public void run() {
                    detectarColisiones();
                }
            };
            Timer RelojVerificarImpactos = new Timer();
            RelojVerificarImpactos.schedule(VerificarImpactos, 0, 1000);
            this.setIsTouchEnabled(true);

        }

        public boolean ccTouchesBegan(MotionEvent event) {
            Log.d("tocuhes comienza:", "x:" + event.getX() + "Y:" + event.getY());
            return true;
        }

        public boolean ccTouchesMoved(MotionEvent event) {
            Log.d("tocuhes mueve:", "x:" + event.getX() + "Y:" + event.getY());
            MoverPersona(event.getX(), PantallaDelDispositivo.getHeight() - event.getY());
            return true;
        }

        public boolean ccTouchesEnded(MotionEvent event) {
            Log.d("tocuhes termina:", "x:" + event.getX() + "Y:" + event.getY());
            MoverPersona(event.getX(), PantallaDelDispositivo.getHeight() - event.getY());
            return true;
        }


        void MoverPersona(float destinoX, float destinoY) {
            float vertical, horizontal, suavizadorMovimiento;
            horizontal = destinoX - PantallaDelDispositivo.getWidth() / 2;
            vertical = destinoY - PantallaDelDispositivo.getHeight() / 2;
            suavizadorMovimiento = 20;
            horizontal = horizontal / suavizadorMovimiento;
            vertical = vertical / suavizadorMovimiento;
            //PersonaJugador.setPosition(PersonaJugador.getPositionX() + horizontal, PersonaJugador.getPositionY()+ vertical);
            float posicionFinalX, posicionFinalY;
            posicionFinalX = destinoX - PersonaJugador.getWidth() / 2;
            posicionFinalY = PersonaJugador.getPositionY() + vertical;

            if (posicionFinalX < PersonaJugador.getWidth() / 2) {
                //posicionFinalX= PersonaJugador.getWidth()/2;
                posicionFinalX = PersonaJugador.getWidth() / 2;
                Log.d("desplazamiento", "se fue por la izquierda");
            }
            if (posicionFinalX > PantallaDelDispositivo.getWidth() - PersonaJugador.getWidth() / 2) {
                posicionFinalX = PantallaDelDispositivo.getWidth() - PersonaJugador.getWidth() / 2;
            }
            if (posicionFinalY < PersonaJugador.getHeight() / 2) {
                posicionFinalY = PersonaJugador.getHeight() / 2;
            }
            if (posicionFinalY > PantallaDelDispositivo.getHeight() - PersonaJugador.getHeight() / 2) {
                posicionFinalY = PantallaDelDispositivo.getHeight() - PersonaJugador.getHeight() / 2;
            }
            //destinoX-PersonaJugador.getWidth()/2
            PersonaJugador.setPosition(posicionFinalX, PosicionInicialY);
        }


        private void PonerPersonaPosicionInicial() {
            PersonaJugador = Sprite.sprite("persona.png");

            PosicionInicialX = PantallaDelDispositivo.width / 2;

            PosicionInicialY = PersonaJugador.getHeight() / 2;
            PersonaJugador.setPosition(PosicionInicialX, PosicionInicialY);
            super.addChild(PersonaJugador);
        }

        public void PonerTituloJuego() {
            tituloJuego = Label.label("Bienvenido", "Verdana", 30);
            float AltoDelTitulo = tituloJuego.getHeight();
            tituloJuego.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height - AltoDelTitulo / 2);
            super.addChild(tituloJuego);
        }

        public void PonerPuntaje() {
            contador++;
            puntosJuego = Label.label("Letras agarradas" + contador, "Verdana", 50);
            float AltoDelTitulo = puntosJuego.getHeight();
            super.removeChild(puntosJuego, true);
            puntosJuego.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height - AltoDelTitulo / 2);
            super.addChild(puntosJuego);

        }

        public void PonerLetra() {
            float PosicionFinalX, PosicionFinalY;

            Letra = Sprite.sprite("letra.png");
            float alturaLetra = Letra.getHeight();
            float anchoLetra = Letra.getWidth();

            CCPoint PosicionInicial = new CCPoint();
            PosicionInicial.y = PantallaDelDispositivo.height + alturaLetra / 2;
            Random generadorDeAzar = new Random();

            PosicionInicial.x = generadorDeAzar.nextInt((int) (PantallaDelDispositivo.width - anchoLetra / 2));
            Letra.setPosition(PosicionInicial.x, PosicionInicial.y);

            PosicionFinalX = PosicionInicial.x - 10;
            PosicionFinalY = -150f;
            Letra.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));
            arrEnemigos.add(Letra);
            addChild(Letra);

        }


        boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {

            boolean Devolver;

            Devolver = false;

            int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;

            int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;

            Sprite1Izquierda = (int) (Sprite1.getPositionX() - Sprite1.getWidth() / 2);

            Sprite1Derecha = (int) (Sprite1.getPositionX() + Sprite1.getWidth() / 2);

            Sprite1Abajo = (int) (Sprite1.getPositionY() - Sprite1.getHeight() / 2);

            Sprite1Arriba = (int) (Sprite1.getPositionY() + Sprite1.getHeight() / 2);

            Sprite2Izquierda = (int) (Sprite2.getPositionX() - Sprite2.getWidth() / 2);

            Sprite2Derecha = (int) (Sprite2.getPositionX() + Sprite2.getWidth() / 2);

            Sprite2Abajo = (int) (Sprite2.getPositionY() - Sprite2.getHeight() / 2);

            Sprite2Arriba = (int) (Sprite2.getPositionY() + Sprite2.getHeight() / 2);


//Borde izq y borde inf de Sprite 1 está dentro de Sprite 2

            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&

                    EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {

                Devolver = true;

            }

//Borde izq y borde sup de Sprite 1 está dentro de Sprite 2

            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&

                    EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                Devolver = true;

            }

//Borde der y borde sup de Sprite 1 está dentro de Sprite 2

            if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&

                    EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {

                Devolver = true;

            }

//Borde der y borde inf de Sprite 1 está dentro de Sprite 2

            if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&

                    EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {


                Devolver = true;

            }

//Borde izq y borde inf de Sprite 2 está dentro de Sprite 1

            if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&

                    EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {

                Devolver = true;

            }

//Borde izq y borde sup de Sprite 1 está dentro de Sprite 1

            if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&

                    EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {

                Devolver = true;

            }

//Borde der y borde sup de Sprite 2 está dentro de Sprite 1

            if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&

                    EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {


                Devolver = true;

            }

//Borde der y borde inf de Sprite 2 está dentro de Sprite 1

            if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&

                    EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {


                Devolver = true;

            }

            return Devolver;

        }


        boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor) {
            boolean devolver;
            if (NumeroMenor > NumeroMayor) {
                int aux = NumeroMayor;
                NumeroMayor = NumeroMenor;
                NumeroMenor = aux;
            }
            if (NumeroAComparar >= NumeroMenor && NumeroAComparar <= NumeroMayor) {
                devolver = true;
            } else {
                devolver = false;
            }
            return devolver;
        }

        void detectarColisiones() {
            boolean HuboColision = false;
            for (Sprite LetraVerif : arrEnemigos) {
                if (InterseccionEntreSprites(PersonaJugador, LetraVerif)) {
                    HuboColision = true;
                }
            }
            if (HuboColision == true) {
                Log.d("Detectar colision", "hubo colision");
                PonerPuntaje();
            } else {
                Log.d("Detectar colision", " No hubo colision");
            }

        }

    }
}
