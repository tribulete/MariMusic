package com.tribulete.www.marimusic;

import android.media.MediaPlayer;
import java.io.IOException;

/**
 * Created by luis on 14/09/2017.
 */

public class InternetRadioPlayer implements MediaPlayer.OnPreparedListener {

    //mp.release(); NO OLVIDAR LIBERAR OBJETO

    private MediaPlayer mp1 = new MediaPlayer();
    private MediaPlayer mp2 = new MediaPlayer();


    private String linkRadio; //Link emisora de radio
    private int playerPlaying;//reproductor en uso
    private int stopDelay; // tiempo de retraso en apagar la emisora en funcionamiento

    //Constructores
    public InternetRadioPlayer() {
        this.playerPlaying = 2;
        this.stopDelay = 3000;
    }

    //Setters
    public void setStopDelay(int delay) {
        this.stopDelay = delay;
    }

    public void setLinkRadio(String linkRadio) {
        this.linkRadio = linkRadio;
    }



    //Metodos
    public void ReproduceRadio() {

        MediaPlayer mp = new MediaPlayer();

        //mp.reset();
        mp.setOnPreparedListener(this);

        //Lanzamos la emisora
        try {

            mp.setDataSource(this.linkRadio);
            mp.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Actualizamos el reproductor en uso
        UpdPlayer(mp);
        //Paramos el otro reproductor que estaba en ejecucion pasado un tiempo
        StopOtherPlayerDelay();


    }




    //Actualiza el reproductor que esta en ejecucion
    private void UpdPlayer(MediaPlayer mp) {

        if ( playerPlaying == 1 ){
            mp1 = mp;
            playerPlaying = 2;
        } else {
            mp2 = mp;
            playerPlaying = 1;
        }
    }

    //Para  el otro reproductor que estaba en ejecucion pasado un tiempo
    private void StopOtherPlayerDelay() {

        //Paramos la emisora que estaba reproduciendose
        if ( playerPlaying == 1 ){
            DelayStopMP (mp2);
            mp2.release();


        } else {
            DelayStopMP (mp1);
            mp1.release();

        }
    }

    //Para el reproductor pasados un determinado numer0 de milisegundos
    private void DelayStopMP(MediaPlayer mp) {


        try
        {
            Thread.sleep (stopDelay);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //paramos
        mp.stop();
        mp.release();


    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }




}
