package com.example.eztrain.ui.progresiones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.eztrain.R;
import com.example.eztrain.adapters.AdapterProgresion;
import com.example.eztrain.db.DBHelper;
import com.example.eztrain.models.Progresion;

import java.util.ArrayList;

public class Progresiones extends AppCompatActivity {

    RecyclerView recyclerProgresion;
    ArrayList<Progresion> listaProgresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresiones);

        //Inicialización componentes
        recyclerProgresion = findViewById(R.id.progresionesList);
        listaProgresion = obtenerProgresionesDesdeBD();

        //Hacer algo cuando una card es clickeada
            AdapterProgresion.OnProgresionClickListener onProgresionClickListener = new AdapterProgresion.OnProgresionClickListener(){
            @Override
            public void onProgresionClick(int position) {
                // Hacer algo cuando se clickea algún elemento del cardView
                Progresion ejercicioClickeado = listaProgresion.get(position);
                // Crear el Intent y pasar los datos como extras
                Intent intent = new Intent(getApplicationContext(), Circuito.class);
                intent.putExtra("nombre", ejercicioClickeado.getNombre());
                intent.putExtra("completado", ejercicioClickeado.isCompletado());
                intent.putExtra("ejercicioAvanzado",ejercicioClickeado.getEjercicioAvanzado());
                startActivity(intent);
            }
        };

            AdapterProgresion adapter = new AdapterProgresion(listaProgresion, onProgresionClickListener);
            recyclerProgresion.setAdapter(adapter);
            recyclerProgresion.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<Progresion> obtenerProgresionesDesdeBD() {
        ArrayList<Progresion> progresiones = new ArrayList<>();

        // Obtener el nombre del ejercicio pasado como extra en el Intent
        String nombreEjercicio = getIntent().getStringExtra("nombre");

        // Obtener una instancia de SQLiteDatabase para leer los datos de la base de datos
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Especificar las columnas que deseamos obtener de la tabla "Progresiones"
        String[] columnas = {DBHelper.COLUMN_ID_PROGRESION, DBHelper.COLUMN_NOMBRE_PROGRESION, DBHelper.COLUMN_EJERCICIO_AVANZADO ,DBHelper.COLUMN_IMG_PROGRESION, DBHelper.COLUMN_COMPLETADO};

        // Especificar la cláusula WHERE para filtrar las progresiones según el nombre del ejercicio
        String whereClause = DBHelper.COLUMN_EJERCICIO_AVANZADO + " = ?";
        String[] whereArgs = {nombreEjercicio};

        // Ejecutar la consulta SELECT en la tabla "Progresiones"
        Cursor cursor = db.query(DBHelper.TABLE_PROGRESIONES, columnas, whereClause, whereArgs, null, null, null);

        // Recorrer el resultado y crear objetos Progresion con los datos obtenidos de la base de datos
        while (cursor.moveToNext()) {

            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID_PROGRESION));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_PROGRESION));
            @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EJERCICIO_AVANZADO));
            @SuppressLint("Range") int imagen = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IMG_PROGRESION));
            @SuppressLint("Range") int completado = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_COMPLETADO));
            boolean completo;
            if (completado==1){
                completo=true;
            }else completo=false;

            Progresion progresion = new Progresion(nombre, completo, ejercicio, imagen);
            progresion.setId(id);
            progresion.setNombre(nombre);
            progresion.setCompletado(completo);
            progresion.setImgProgresion(imagen);
            progresiones.add(progresion);
        }

        // Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();

        for (Progresion progresion : progresiones) {
            Log.d("Progresion", "ID: " + progresion.getId() + ", Nombre: " + progresion.getNombre() + ", Completado: " + progresion.isCompletado() + "IMAGEN: " +progresion.getImgProgresion());
        }

        return progresiones;
    }

}