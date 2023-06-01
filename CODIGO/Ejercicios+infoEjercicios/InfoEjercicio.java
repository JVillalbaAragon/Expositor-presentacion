package com.example.eztrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoEjercicio extends AppCompatActivity {

    private TextView infoNombre, infoDescripcion;

    private YouTubePlayerView youTubePlayerView;
    private ImageView infoImg;
    //COMPONENTE ID para video de youtube
    private String youtubeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ejercicio);

        //Recuperar extras del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        int imagen = intent.getIntExtra("imagen", R.drawable.ic_launcher_foreground);
        String urlVideo = intent.getStringExtra("url");
        youtubeID=sacarID(urlVideo);
        // Obtener las referencias de los TextViews e ImageView
        infoNombre = findViewById(R.id.infoNombre);
        infoDescripcion = findViewById(R.id.infoDescripción);
        //VIDEO
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(youtubeID, 0);
            }
        });
        // Establecer los valores de los TextViews e ImageView
        infoNombre.setText(nombre);
        infoDescripcion.setText(descripcion);
        infoDescripcion.setMovementMethod(new ScrollingMovementMethod());

    }

    private String sacarID(String url) {
        String videoId = null;

        // Patrón para extraer la ID del video
        Pattern pattern = Pattern.compile("(?<=v=)[\\w-]+");
        Matcher matcher = pattern.matcher(url);

        // Si hay una coincidencia, obtener la ID del video
        if (matcher.find()) {
            videoId = matcher.group();
        }

        return videoId;
    }
}