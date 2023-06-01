package com.example.eztrain.ui.progresiones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eztrain.MainActivity;
import com.example.eztrain.R;
import com.example.eztrain.db.DBHelper;
import com.example.eztrain.models.EjercicioProgresion;
import com.example.eztrain.models.Progresion;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Circuito extends AppCompatActivity {

    private Progresion progresion;
    private List<EjercicioProgresion> ejercicios;
    private int ejercicioActual = 0;
    private YouTubePlayerView youTubePlayerView;
    private String youtubeID;
    //Booleano para controlar el cronometro
    private boolean cronometroEnProgreso = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuito);
        //Obtener el intent pasado desde Progresiones
        Intent intent = getIntent();

        progresion = new Progresion(
                intent.getStringExtra("nombre"),
                intent.getBooleanExtra("completado", false),
                intent.getStringExtra("ejercicioAvanzado")
        );
        //Sacar URL del ejercicio convencional.
        // Obtener la lista de ejercicios de la progresión desde la base de datos
        DBHelper db = new DBHelper(this);
        ejercicios = db.obtenerEjerciciosProgresionDesdeBD(progresion.getNombre());

        // Mostrar el primer ejercicio en la pantalla
        mostrarEjercicioActual();

        //Configurar el botón "Siguiente"
        Button siguiente = findViewById(R.id.btnNext);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cronometroEnProgreso) {
                    // El cronómetro está en progreso, no permitir continuar
                    Toast.makeText(Circuito.this, "¡No seas tramposo! El ejercicio no ha terminado", Toast.LENGTH_SHORT).show();
                } else {
                    // Pasar al siguiente ejercicio
                    ejercicioActual++;
                    if (ejercicioActual < ejercicios.size()) {
                        mostrarEjercicioActual();
                    } else {
                        mostrarDialogoConfirmacion();
                    }
                }
            }
        });
    }

    private void mostrarEjercicioActual() {
        Log.d("test", "CIRCUITO FAIL " +ejercicios.get(ejercicioActual));
        EjercicioProgresion ejercicio = ejercicios.get(ejercicioActual);
        TextView nombreEjercicioTv = findViewById(R.id.txtNomEx);
        TextView numMutable = findViewById(R.id.textMostrar);
        TextView numRepeticiones= findViewById(R.id.textRepeticiones);
        ProgressBar progressBarCircuito = findViewById(R.id.progressBarSecs);
        TextView pistas = findViewById(R.id.textHints);
        nombreEjercicioTv.setText(ejercicio.getIdEjercicioConvencional());

        //Preparar video de Youtube
        youtubeID=sacarID(getURL(ejercicio.getIdEjercicioConvencional()));
        youTubePlayerView = findViewById(R.id.youtube_player_circuito);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener(){
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(youtubeID, 0);}
            });

        // Si el ejercicio es por repeticiones
        if (ejercicio.getTipo() == 1) {
            numRepeticiones.setVisibility(View.VISIBLE);
            numMutable.setVisibility(View.INVISIBLE);
            numRepeticiones.setText("Realiza " + String.valueOf(ejercicio.getRepeticiones() + " repeticiones"));
            progressBarCircuito.setVisibility(View.INVISIBLE);
            pistas.setText("¡Haz las repeticiones indicadas!");
        }
        // Si el ejercicio es por tiempo
        else if (ejercicio.getTipo() == 2) {
            numRepeticiones.setVisibility(View.INVISIBLE);
            numMutable.setVisibility(View.VISIBLE);
            progressBarCircuito.setVisibility(View.VISIBLE);
            numMutable.setText(String.valueOf(ejercicio.getSegundos()));
            progressBarCircuito.setMax(ejercicio.getSegundos());
            pistas.setText("¡No te detengas hasta que acabe el tiempo!");

            // Iniciar cronómetro
            cronometroEnProgreso = true;

            new CountDownTimer(ejercicio.getSegundos() * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    int secondsLeft = (int) millisUntilFinished / 1000;
                    numMutable.setText(String.valueOf(secondsLeft));
                    progressBarCircuito.setProgress(secondsLeft);
                    progressBarCircuito.setMax(ejercicio.getSegundos());
                    progressBarCircuito.setProgress(ejercicio.getSegundos() - secondsLeft);
                }

                public void onFinish() {
                    numMutable.setText("¡Conseguido!");
                    progressBarCircuito.setProgress(0);
                    cronometroEnProgreso = false;

                }
            }.start();
        }
    }
    private void terminarProgresion() {
        progresion.setCompletado(true);
        // Actualizar la progresión en la base de datos
        DBHelper db = new DBHelper(this);
        db.actualizarProgresionEnBD(progresion);
        // Actualizar progreso ejercicio avanzado
        double progresoEjercicio = calcularPorcentajeProgreso();
        // Obtener el nombre del ejercicio avanzado
        Intent intent = getIntent();
        String nombreEjercicioAvanzado = intent.getStringExtra("ejercicioAvanzado");
        db.actualizarProgresoEjercicioAvanzado(nombreEjercicioAvanzado, progresoEjercicio);

        //Refrescar recyclerView:
        finish();}
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
    private String getURL(String nom){
        DBHelper db = new DBHelper(this);
        return db.obtenerUrlVideoEjercicio(nom);

    }
    private double calcularPorcentajeProgreso() {
        DBHelper db = new DBHelper(this);

        // Obtener el nombre del ejercicio avanzado
        Intent intent = getIntent();
        String nombreEjercicioAvanzado = intent.getStringExtra("ejercicioAvanzado");
        // Obtener la cantidad total de progresiones del ejercicio avanzado por su nombre
        int cantidadTotalProgresiones = db.obtenerCantidadProgresionesDB(nombreEjercicioAvanzado);
        // Obtener la cantidad de progresiones completadas en la base de datos relacionadas con el ejercicio avanzado
        int cantidadProgresionesCompletadas = db.obtenerCantidadProgresionesCompletadasDB(nombreEjercicioAvanzado) + 1;
        // Calcular el porcentaje de progreso
        double porcentajeProgreso = (double) cantidadProgresionesCompletadas / cantidadTotalProgresiones * 100;
        return porcentajeProgreso;
    }
    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Has podido terminar el circuito satisfactoriamente?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Actualizar el campo "completado" a true en la base de datos
                progresion.setCompletado(true);
                DBHelper db = new DBHelper(Circuito.this);
                double progresoEjercicio = calcularPorcentajeProgreso();
                db.actualizarProgresionEnBD(progresion);
                Intent intent = getIntent();
                String nombreEjercicioAvanzado = intent.getStringExtra("ejercicioAvanzado");
                db.actualizarProgresoEjercicioAvanzado(nombreEjercicioAvanzado, progresoEjercicio);

                // Redirigir a MainActivity
                Intent refreshMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(refreshMain);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Actualizar el campo "completado" a false en la base de datos
                progresion.setCompletado(false);
                DBHelper db = new DBHelper(Circuito.this);
                db.actualizarProgresionEnBD(progresion);

                // Redirigir a MainActivity
                Intent refreshMain = new Intent(Circuito.this, MainActivity.class);
                startActivity(refreshMain);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
