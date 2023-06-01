package com.example.eztrain.ui.dashboard;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eztrain.R;
import com.example.eztrain.adapters.AdapterExercises;
import com.example.eztrain.db.DBHelper;
import com.example.eztrain.models.EjercicioConvencional;
import com.example.eztrain.InfoEjercicio;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerExercise;
    ArrayList<EjercicioConvencional> regularList;

    public DashboardFragment() {
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
        View vista = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Inicializar la lista de ejercicios regulares con los datos de la base de datos
        regularList = obtenerEjerciciosConvencionalesDesdeBD();

        recyclerExercise = vista.findViewById(R.id.exerciseList);

        // Hacer algo cuando una card es clickada
        AdapterExercises.OnExercisesClickListener onExercisesClickListener = new AdapterExercises.OnExercisesClickListener() {
            @Override
            public void onExercisesClick(int position) {
                // Hacer algo cuando se clickea algún elemento del cardView
                EjercicioConvencional ejercicioClickeado = regularList.get(position);

                // Crear el Intent y pasar los datos como extras
                Intent intent = new Intent(getContext(), InfoEjercicio.class);
                intent.putExtra("nombre", ejercicioClickeado.getNombre());
                intent.putExtra("descripcion", ejercicioClickeado.getDescripcion());
                intent.putExtra("imagen", ejercicioClickeado.getImagen());
                intent.putExtra("url", ejercicioClickeado.getUrl());
                startActivity(intent);
            }
        };

        AdapterExercises adapter = new AdapterExercises(regularList, onExercisesClickListener);
        recyclerExercise.setAdapter(adapter);
        recyclerExercise.setLayoutManager(new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false));

        return vista;
    }

    // Método para obtener los datos de los ejercicios desde la base de datos
    private ArrayList<EjercicioConvencional> obtenerEjerciciosConvencionalesDesdeBD() {
        ArrayList<EjercicioConvencional> ejerciciosConvencionales = new ArrayList<>();

        // Obtener una instancia de SQLiteDatabase para leer los datos de la base de datos
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Especificar las columnas que deseamos obtener de la tabla "EjerciciosConvencionales"
        String[] columnas = {DBHelper.COLUMN_ID_EJERCICIO_COVENCIONAL, DBHelper.COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL,DBHelper.COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL, DBHelper.COLUMN_IMG_EJERCICIO_COVENCIONAL, DBHelper.COLUMN_URL_VIDEO_CONVENCIONAL};

        // Ejecutar la consulta SELECT en la tabla "EjerciciosConvencionales"
        Cursor cursor = db.query(DBHelper.TABLE_EJERCICIOS_CONVENCIONALES, columnas, null, null, null, null, null);

        // Recorrer el resultado y crear objetos EjercicioConvencional con los datos obtenidos de la base de datos
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID_EJERCICIO_COVENCIONAL));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL));
            @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL));
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_URL_VIDEO_CONVENCIONAL));
            @SuppressLint("Range") int imagen = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IMG_EJERCICIO_COVENCIONAL));

            EjercicioConvencional ejercicioConvencional = new EjercicioConvencional(nombre, descripcion, imagen, url);
            ejercicioConvencional.setId(id);
            ejercicioConvencional.setNombre(nombre);
            ejercicioConvencional.setDescripcion(descripcion);
            ejercicioConvencional.setId(imagen);
            ejercicioConvencional.setUrl(url);
            ejerciciosConvencionales.add(ejercicioConvencional);
        }

        // Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();

        return ejerciciosConvencionales;
    }


}