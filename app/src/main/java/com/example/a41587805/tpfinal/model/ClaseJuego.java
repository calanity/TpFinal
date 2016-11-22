package com.example.a41587805.tpfinal.model;

import android.graphics.Rect;
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
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
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
    Label tituloJuego0,tituloJuego1, tituloJuego2, puntosJuego;
    ArrayList<letra>arrEnemigos;
    ArrayList<Palabra>palabras;
    ArrayList<letra> letras;
    Palabra PalabraElegida;
    ArrayList<letra>PalabraMomentanea;
    Rect colision;
    int contador;
    letra l;

    int pos;
    public ClaseJuego(CCGLSurfaceView VistaDelJuego)
    {
            Log.d("bob" , "comienza el juego");
            _VistaDelJuego =VistaDelJuego;
            colision= new Rect();
            letras = new ArrayList<letra>();
            for(letra let : letras){
                let.setLetra("letra"+ String.valueOf(let)+".png");
            }
    }

    public void ComenzarJuego()
    {
        letr= "א";
        Log.d("comienza el juego" , "comenzar");
        Director.sharedDirector().attachInView(_VistaDelJuego);
        PantallaDelDispositivo= Director.sharedDirector().displaySize();

        Director.sharedDirector().runWithScene(Inicio());

    }

    public void CargoLetras()
    {
        //0
        letra alef= new letra("letra1.png","א");
        //alef.setId(1);
        letras.add(alef);

        //1
        letra bet= new letra("letra2.png","ב");
        //bet.setId(2);
        letras.add(bet);

        //2
        letra guimel= new letra("letra3.png","ג");
        //guimel.setId(3);
        letras.add(guimel);

        //3
        letra dalet= new letra("letra4.png","ד" );
        //dalet.setId(4);
        letras.add(dalet);

        //4
        letra hei= new letra("letra5.png","ה" );
        hei.setId(5);
        letras.add(hei);

        //5
        letra vav= new letra("letra6.png","ו");
        vav.setId(6);
        letras.add(vav);

        //6
        letra sain = new letra("letra7.png","ז" );
        sain.setId(7);
        letras.add(sain);

        //7
        letra jet= new letra("letra8.png","ח" );
        jet.setId(8);
        letras.add(jet);

        //8
        letra tet = new letra("letra9.png","ט");
        tet.setId(9);
        letras.add(tet);

        //9
        letra iod = new letra("letra10.png","י");
        iod.setId(10);
        letras.add(iod);
        //10
        letra jaf = new letra("letra11.png","כ");
        iod.setId(11);
        letras.add(jaf);

        //11
        letra lamed= new letra("letra12.png", "ל");
        lamed.setId(12);
        letras.add(jaf);


        //12
        letra mem= new letra("letra13.png", "מ");
        mem.setId(13);
        letras.add(mem);

        //13
        letra nun= new letra("letra14.png","נ" );
        nun.setId(14);
        letras.add(nun);

        //14
        letra samaj= new letra("letra15.png","ס");
        samaj.setId(15);
        letras.add(samaj);

        //15
        letra ayin = new letra("letra16.png","ע");
        ayin.setId(16);
        letras.add(ayin);

        //16
        letra pei = new letra("letra17.png", "פ");
        pei.setId(17);
        letras.add(pei);

        //17
        letra tzadik = new letra("letra18.png", "צ");
        tzadik.setId(18);
        letras.add(tzadik);

        //18
        letra kuf = new letra("letra19.png","ק" );
        tzadik.setId(19);
        letras.add(kuf);

        //19
        letra resh = new letra("letra20.png", "ר");
        resh.setId(20);
        letras.add(resh);

        //20
        letra shin = new letra("letra21.png", "ש");
        shin.setId(21);
        letras.add(shin);

        //21
        letra taf= new letra("letra22.png", "ת");
        shin.setId(22);
        letras.add(taf);


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


        float PosicionInicialX, PosicionInicialY;

        public CapaJuego() {
            //jhkikhkhjh
            PonerPersonaPosicionInicial();
            palabras= new ArrayList<>();
            CargoLetras();
            CargarPalabras();
            PonerTituloJuego();

            Palabra primera= new Palabra();
            palabras.add(primera);


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
                    //colision.set(Math.round(Letra.getPositionX()), Math.round(Letra.getPositionY(), Letra.getPositionX()+Letra.getWidth(), Letra.getPositionY()+Letra.getHeight());
                }
            };
            Timer RelojVerificarImpactos = new Timer();
            RelojVerificarImpactos.schedule(VerificarImpactos, 0, 200);
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

        public  void CargarPalabras()
        {

            Palabra pal= new Palabra();
            pal.setId(3);
            pal.setPal("בית");
            pal.addLetra(letras.get(1));
            pal.addLetra(letras.get(9));
            pal.addLetra(letras.get(21));
            palabras.add(pal);

            Palabra ieled= new Palabra();
            ieled.setId(1);
            ieled.setPal("ילד");
            ieled.addLetra(letras.get(9));
            ieled.addLetra(letras.get(11));
            ieled.addLetra(letras.get(3));
            palabras.add(ieled);


            Palabra sefer= new Palabra();
            sefer.setId(2);
            sefer.setPal("ספר");
            sefer.addLetra(letras.get(14));
            sefer.addLetra(letras.get(16));
            sefer.addLetra(letras.get(19));
            palabras.add(sefer);

           /* Palabra kalmar= new Palabra();
            kalmar.setId(0);
            kalmar.setPal("למר");
            kalmar.addLetra(letras.get(19));
            kalmar.addLetra(letras.get(12));
            kalmar.addLetra(letras.get(13));
            kalmar.addLetra(letras.get(20));
            palabras.add(kalmar);*/


        }


        public void PonerTituloJuego() {
            Palabra palabra= new Palabra();
            Random random= new Random();
            int let = random.nextInt(3)+1 ;
            //String mostrar= palabras.get(1).getPal();
            PalabraElegida= palabras.get(2);
            String let0= String.valueOf(PalabraElegida.getPalabra().get(2).getLetra());
            String let1= PalabraElegida.getPalabra().get(1).getLetra().toString();
            String let2= PalabraElegida.getPalabra().get(0).getLetra().toString();

            Log.d("let0",let0);
            Log.d("let1",let1);
            Log.d("let2",let2);

            tituloJuego0 = Label.label(let0, "Verdana", 100);
            tituloJuego1 = Label.label(let1, "Verdana", 100);
            tituloJuego2 = Label.label(let2, "Verdana", 100);
            float AltoDelTitulo = tituloJuego0.getHeight()+tituloJuego1.getHeight()+tituloJuego2.getHeight();
            tituloJuego0.setPosition(PantallaDelDispositivo.width/2 +100, 1100);
            tituloJuego1.setPosition(PantallaDelDispositivo.width / 2 +160, 1100);
            tituloJuego2.setPosition(PantallaDelDispositivo.width / 2+ 220, 1100);

            super.addChild(tituloJuego0);
            super.addChild(tituloJuego1);
            super.addChild(tituloJuego2);

        }

        public void PonerPuntaje() {
            super.removeChild(puntosJuego, true);
            //contador++;
            puntosJuego = Label.label("Letras agarradas " + contador, "Verdana", 50);
            float AltoDelTitulo = puntosJuego.getHeight();
            puntosJuego.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height - AltoDelTitulo / 2);
            super.addChild(puntosJuego);

        }

        public void PonerLetra() {

            float PosicionFinalX, PosicionFinalY;
            //armar array de letras y que segun el caso, segun la que elije
            Random random= new Random();
            int let = random.nextInt(21);
           /* for(letra letr : letras){
                Letra = Sprite.sprite("letra"+ String.valueOf(let)+".png");
                letras.add(new letra(Letra, ));
            }*/
            let++;
            l = letras.get(let);
            float alturaLetra = l.getSprite().getHeight();
            float anchoLetra =l.getSprite().getWidth();

            CCPoint PosicionInicial = new CCPoint();
            PosicionInicial.y = PantallaDelDispositivo.height + alturaLetra / 2;
            Random generadorDeAzar = new Random();

            //PosicionInicial.x = generadorDeAzar.nextInt((int) (PantallaDelDispositivo.width - anchoLetra / 2));
            PosicionInicial.x = generadorDeAzar.nextInt((int) (PantallaDelDispositivo.width - anchoLetra));

            l.getSprite().setPosition(PosicionInicial.x , PosicionInicial.y);

            PosicionFinalX = PosicionInicial.x - 10;
            PosicionFinalY = -150f;
            l.getSprite().runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));
            arrEnemigos.add(l);

            addChild(l.sprite);

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
            CCColor3B colorPuntaje= new CCColor3B(255,0,0);

            int idLetra= l.getId();
            String path=("letra"+idLetra+".png");
            pos =-1;
            boolean HuboColision = false;
           //letra letraColisionada = new letra(path,"f");
            for (letra LetraVerif : arrEnemigos) {
                if (InterseccionEntreSprites(PersonaJugador, LetraVerif.getSprite())) {
                    HuboColision = true;
                    //letraColisionada = LetraVerif;
                    //preugnto por la palabra elegida,si existe en que pos
                    //agrgo a palabra momento  y comparo contra lograt

                    pos= PalabraElegida.contieneLetra(LetraVerif);

                    //hacer un swith por cada ves que encuentra letra
                    // para cambiae una letra que no jay .set, y cambio el lbl
                    //cuando la creo .add
                    if(PalabraMomentanea!=null && pos>-1) {

                        PalabraMomentanea.add(LetraVerif);

                        boolean comparar = PalabraElegida.comparar(PalabraMomentanea);
                        if (comparar)
                        {
                            //tituloJuego = Label.label("Gano", "Verdana",50);
                            tituloJuego0.setColor(colorPuntaje);
                        }
                    }
                }
            }

            if (HuboColision == true) {
                Log.d("Detectar colision", "hubo colision");
               //PonerPuntaje();
               /* if (letraColisionada != null) {
                    arrEnemigos.remove(letraColisionada);
                }*/
            } else {
                Log.d("Detectar colision", " No hubo colision");
            }

        }

    }
}
