package com.tribulete.www.marimusic;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  implements MediaPlayer.OnPreparedListener{

    Toast t1;
    private InternetRadioPlayer irp = new InternetRadioPlayer();

    MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
/*
    public void Radio_RockFM(View v) {
        Toast t = Toast.makeText(this,
                "Espere un momento mientras se carga la emisora",
                Toast.LENGTH_SHORT);
        t.show();

        irp.setLinkRadio("http://195.55.74.212/cope/rockfm.mp3");
        irp.ReproduceRadio();


    }

    public void Radio_KissFM(View v) {

        Toast t = Toast.makeText(this,
                "Espere un momento mientras se carga la emisora",
                Toast.LENGTH_SHORT);
        t.show();

        irp.setLinkRadio("http://hitfm.es.audio1.glb.ipercast.net:8000/kissfm.es/mp3");
        irp.ReproduceRadio();

    }
*/
    public void Radio_KissFM(View v) {


        //mp.reset();
        mp.stop();
        mp.reset();
        mp.setOnPreparedListener(this);

        //Lanzamos la emisora
        try {

            mp.setDataSource("http://hitfm.es.audio1.glb.ipercast.net:8000/kissfm.es/mp3");
            mp.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Radio_RockFM(View v) {


        //mp.reset();
        mp.stop();
        mp.reset();
        mp.setOnPreparedListener(this);

        //Lanzamos la emisora
        try {

            mp.setDataSource("http://antpol-01.cdn.eurozet.pl:8012/;channel.mp3");
            mp.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }



}
