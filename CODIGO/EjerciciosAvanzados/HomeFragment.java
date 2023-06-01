package com.example.eztrain.ui.home;

import static com.example.eztrain.db.DBHelper.COLUMN_PROGRESO_AVANZADO;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eztrain.R;
import com.example.eztrain.adapters.AdapterHome;
import com.example.eztrain.db.DBHelper;
import com.example.eztrain.models.EjercicioAvanzado;
import com.example.eztrain.models.Progresion;
import com.example.eztrain.ui.progresiones.Progresiones;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerHome;
    ArrayList<EjercicioAvanzado> exerciseList;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializar la lista de ejercicios regulares con los datos de la base de datos
        exerciseList = obtenerEjerciciosAvanzadosDesdeBD();

        recyclerHome=vista.findViewById(R.id.progresionesList);

        //Hacer algo cuando una card es clickeada
        AdapterHome.OnExercisesClickListener onExercisesClickListener = new AdapterHome.OnExercisesClickListener(){
            @Override
            public void onExercisesClick(int position) {
                // Hacer algo cuando se clickea algún elemento del cardView
                EjercicioAvanzado ejercicioClickeado = exerciseList.get(position);
                // Crear el Intent y pasar los datos como extras
                Intent intent = new Intent(getContext(), Progresiones.class);
                intent.putExtra("nombre", ejercicioClickeado.getNombre());
                intent.putExtra("descripcion", ejercicioClickeado.getDescripcion());
                intent.putExtra("imagen", ejercicioClickeado.getImagen());
                intent.putExtra("progreso", ejercicioClickeado.getProgreso());
                intent.putExtra("url", ejercicioClickeado.getUrl());
                startActivity(intent);
            }
        };
        AdapterHome adapter = new AdapterHome(exerciseList, onExercisesClickListener);
        recyclerHome.setAdapter(adapter);
        recyclerHome.setLayoutManager(new LinearLayoutManager(getContext()));
        return vista;
    }

    // Método para obtener los datos de los ejercicios desde la base de datos
    private ArrayList<EjercicioAvanzado> obtenerEjerciciosAvanzadosDesdeBD() {
        ArrayList<EjercicioAvanzado> ejerciciosAvanzados = new ArrayList<>();

        // Obtener una instancia de SQLiteDatabase para leer los datos de la base de datos
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Especificar las columnas que deseamos obtener de la tabla "EjerciciosAvanzados"
        String[] columnas = {DBHelper.COLUMN_ID_EJERCICIO_AVANZADO, DBHelper.COLUMN_NOMBRE_EJERCICIO_AVANZADO,DBHelper.COLUMN_DESCRIPCION_EJERCICIO_AVANZADO, DBHelper.COLUMN_PROGRESO_AVANZADO, DBHelper.COLUMN_IMG_EJERCICIO_AVANZADO, DBHelper.COLUMN_URL_VIDEO_AVANZADO};

        // Ejecutar la consulta SELECT en la tabla "EjerciciosConvencionales"
        Cursor cursor = db.query(DBHelper.TABLE_EJERCICIOS_AVANZADOS, columnas, null, null, null, null, null);

        // Recorrer el resultado y crear objetos EjercicioConvencional con los datos obtenidos de la base de datos
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID_EJERCICIO_AVANZADO));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_EJERCICIO_AVANZADO));
            @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPCION_EJERCICIO_AVANZADO));
            @SuppressLint("Range") int progreso = cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESO_AVANZADO));
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_URL_VIDEO_AVANZADO));
            @SuppressLint("Range") int imagen = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IMG_EJERCICIO_AVANZADO));

            EjercicioAvanzado ejercicioAvanzado = new EjercicioAvanzado(id ,nombre, descripcion , progreso, imagen, url);
            ejercicioAvanzado.setId(id);
            ejercicioAvanzado.setNombre(nombre);
            ejercicioAvanzado.setDescripcion(descripcion);
            ejercicioAvanzado.setProgreso(progreso);
            ejercicioAvanzado.setImagen(imagen);
            ejercicioAvanzado.setUrl(url);
            ejerciciosAvanzados.add(ejercicioAvanzado);
        }

        // Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        for (EjercicioAvanzado ejercicio : ejerciciosAvanzados) {
            Log.d("Ejercicio Avanzado", "ID: " + ejercicio.getId() + ", Nombre: " + ejercicio.getNombre() + ", Descripción: " + ejercicio.getDescripcion() + ", Progreso: " + ejercicio.getProgreso() + ", Imagen: " + ejercicio.getImagen() + ", URL: " + ejercicio.getUrl());
        }

        return ejerciciosAvanzados;
    }



}