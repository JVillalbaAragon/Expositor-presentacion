package com.example.eztrain.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.eztrain.R;
import com.example.eztrain.models.EjercicioAvanzado;
import com.example.eztrain.models.EjercicioConvencional;
import com.example.eztrain.models.EjercicioProgresion;
import com.example.eztrain.models.Progresion;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ejerciciosDB";

    //EJERCICIOs CONVENCIONALES
    public static final String TABLE_EJERCICIOS_CONVENCIONALES = "EjerciciosConvencionales";
    public static final String COLUMN_ID_EJERCICIO_COVENCIONAL = "idEjercicio";
    public static final String COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL = "nombreEjercicio";
    public static final String COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL = "descripcionEjercicio";
    public static final String COLUMN_IMG_EJERCICIO_COVENCIONAL = "imgEjercicio";
    public static final String COLUMN_URL_VIDEO_CONVENCIONAL = "urlVideo";

    //EJERCICIOS AVANZADOS
    public static final String TABLE_EJERCICIOS_AVANZADOS = "EjerciciosAvanzados";
    public static final String COLUMN_ID_EJERCICIO_AVANZADO = "idEjercicio";
    public static final String COLUMN_NOMBRE_EJERCICIO_AVANZADO = "nombreEjercicio";
    public static final String COLUMN_DESCRIPCION_EJERCICIO_AVANZADO = "descripcionEjercicio";
    public static final String COLUMN_IMG_EJERCICIO_AVANZADO = "imgEjercicio";
    public static final String COLUMN_URL_VIDEO_AVANZADO = "urlVideoAvanzado";
    public static final String COLUMN_PROGRESO_AVANZADO = "progresoAvanzado";

    //PROGRESIONES
    public static final String TABLE_PROGRESIONES = "Progresiones";
    public static final String COLUMN_ID_PROGRESION = "idProgresion";
    public static final String COLUMN_NOMBRE_PROGRESION = "nombreProgresion";
    public static final String COLUMN_EJERCICIO_AVANZADO = "idEjercicioAvanzado";
    public static final String COLUMN_IMG_PROGRESION = "imgProgresion";
    public static final String COLUMN_COMPLETADO = "completado";


    //TABLA RELACIONAL ENTRE PROGRESIONES Y EJERCICIOS CONVENCIONALES
    public static final String TABLE_EJERCICIOS_PROGRESIONES = "EjerciciosProgresiones";
    public static final String COLUMN_ID_EJERCICIO_PROGRESION = "idEjercicioProgresion";
    public static final String COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION = "idProgresion";
    public static final String COLUMN_ID_EJERCICIO_CONVENCIONAL = "idEjercicioConvencional";

    public static final String COLUMN_SEGUNDOS_EJERCICIO = "segundos";

    public static final String COLUMN_REPETICIONES_EJERCICIO = "repeticiones";
    public static final String COLUMN_NUMERO_EJERCICIO = "numeroEjercicio";
    public static final String COLUMN_TIPO_EJERCICIO_COVENCIONAL = "tipoEjercicio";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREAR EJERCICIOS CONVENCIONALES
        String CREATE_EJERCICIOS_CONVENCIONALES_TABLE = "CREATE TABLE " + TABLE_EJERCICIOS_CONVENCIONALES + " ( " +
                COLUMN_ID_EJERCICIO_COVENCIONAL + " INTEGER PRIMARY KEY, " +
                COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL + " TEXT, " +
                COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL + " TEXT, " +
                COLUMN_URL_VIDEO_CONVENCIONAL + " TEXT, " +

                COLUMN_IMG_EJERCICIO_COVENCIONAL + " INTEGER DEFAULT " + R.drawable.ic_launcher_foreground + ")"
                ;
        db.execSQL(CREATE_EJERCICIOS_CONVENCIONALES_TABLE);

        //CREAR EJERCICIOS AVANZADOS
        String CREATE_EJERCICIOS_AVANZADOS_TABLE = "CREATE TABLE " + TABLE_EJERCICIOS_AVANZADOS + " ( " +
                COLUMN_ID_EJERCICIO_AVANZADO + " INTEGER PRIMARY KEY, " +
                COLUMN_NOMBRE_EJERCICIO_AVANZADO + " TEXT, " +
                COLUMN_DESCRIPCION_EJERCICIO_AVANZADO + " TEXT, " +
                COLUMN_URL_VIDEO_AVANZADO + " TEXT, " +
                COLUMN_PROGRESO_AVANZADO + " INTEGER, " +
                COLUMN_IMG_EJERCICIO_AVANZADO + " INTEGER DEFAULT " + R.drawable.ic_launcher_foreground + ")";
        db.execSQL(CREATE_EJERCICIOS_AVANZADOS_TABLE);

        //CREAR TABLA PROGRESIONES
        String CREATE_TABLE_PROGRESIONES = "CREATE TABLE " + TABLE_PROGRESIONES + "("
                + COLUMN_ID_PROGRESION + " INTEGER PRIMARY KEY,"
                + COLUMN_NOMBRE_PROGRESION + " TEXT, "
                + COLUMN_COMPLETADO + " INTEGER,"
                + COLUMN_EJERCICIO_AVANZADO + " TEXT,"
                + COLUMN_IMG_PROGRESION + " INTEGER DEFAULT " + R.drawable.ic_intensity_intermediate + ","
                + " FOREIGN KEY (" + COLUMN_EJERCICIO_AVANZADO + ") REFERENCES " + TABLE_EJERCICIOS_AVANZADOS + "(" + COLUMN_NOMBRE_EJERCICIO_AVANZADO + ")"
                + ")";
        db.execSQL(CREATE_TABLE_PROGRESIONES);

        //CREAR TABLA EJERCICIOS PROGRESIONES
        String CREATE_TABLE_EJERCICIOS_PROGRESIONES = "CREATE TABLE " + TABLE_EJERCICIOS_PROGRESIONES + "("
                + COLUMN_ID_EJERCICIO_PROGRESION + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION + " TEXT,"
                + COLUMN_ID_EJERCICIO_CONVENCIONAL + " INTEGER,"
                + COLUMN_REPETICIONES_EJERCICIO + " INTEGER, "
                + COLUMN_SEGUNDOS_EJERCICIO + " INTEGER, "
                + COLUMN_NUMERO_EJERCICIO + " INTEGER,"
                + COLUMN_TIPO_EJERCICIO_COVENCIONAL + " INTEGER, "

                + "FOREIGN KEY(" + COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION + ") REFERENCES " + TABLE_PROGRESIONES + "(" + COLUMN_NOMBRE_PROGRESION + "),"
                + "FOREIGN KEY(" + COLUMN_ID_EJERCICIO_CONVENCIONAL + ") REFERENCES " + TABLE_EJERCICIOS_CONVENCIONALES + "(" + COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL + ")"
                + ");";

        db.execSQL(CREATE_TABLE_EJERCICIOS_PROGRESIONES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EJERCICIOS_CONVENCIONALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EJERCICIOS_AVANZADOS);
        onCreate(db);
    }

    //APARTADO PARA EJERCICIOS AVANZADOS/PROGRESIONES
    public int obtenerCantidadProgresionesDB(String ejercicioAvanzado) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_PROGRESIONES +
                " WHERE " + COLUMN_EJERCICIO_AVANZADO + " = '" + ejercicioAvanzado + "'";
        Cursor cursor = db.rawQuery(query, null);

        int cantidadProgresiones = 0;
        if (cursor.moveToFirst()) {
            cantidadProgresiones = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return cantidadProgresiones;
    }


    public int obtenerCantidadProgresionesCompletadasDB(String ejercicioAvanzado) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_PROGRESIONES +
                " WHERE " + COLUMN_EJERCICIO_AVANZADO + " = '" + ejercicioAvanzado + "'" +
                " AND " + COLUMN_COMPLETADO + " = 1";
        Cursor cursor = db.rawQuery(query, null);

        int cantidadProgresionesCompletadas = 0;
        if (cursor.moveToFirst()) {
            cantidadProgresionesCompletadas = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return cantidadProgresionesCompletadas;
    }

    //APARTADO PARA EJERCICIOS CONVENCIONALES
    public void addEjercicioConvencional(EjercicioConvencional ejercicioConvencional) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL, ejercicioConvencional.getNombre());
        values.put(COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL, ejercicioConvencional.getDescripcion());
        values.put(COLUMN_IMG_EJERCICIO_COVENCIONAL, ejercicioConvencional.getImagen());
        values.put(COLUMN_URL_VIDEO_CONVENCIONAL, ejercicioConvencional.getUrl());
        db.insert(TABLE_EJERCICIOS_CONVENCIONALES, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public String obtenerUrlVideoEjercicio(String nombreEjercicio) {
        SQLiteDatabase db = this.getReadableDatabase();
        String urlVideo = null;

        String[] columns = { COLUMN_URL_VIDEO_CONVENCIONAL };
        String selection = COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL + " = ?";
        String[] selectionArgs = { nombreEjercicio };

        Log.d("DBHelper", "Buscando URL para el ejercicio: " + nombreEjercicio);
        Cursor cursor = db.query(
                TABLE_EJERCICIOS_CONVENCIONALES,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            urlVideo = cursor.getString(cursor.getColumnIndex(COLUMN_URL_VIDEO_CONVENCIONAL));
            Log.d("DBHelper", "URL encontrada: " + urlVideo);
        } else {
            Log.d("DBHelper", "No se encontró URL para el ejercicio: " + nombreEjercicio);
        }
        cursor.close();
        db.close();

        return urlVideo;
    }

    //APARTADO PARA EJERCICIOS AVANZADOS:

    public void addEjercicioAvanzado(EjercicioAvanzado ejercicioAvanzado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_EJERCICIO_AVANZADO, ejercicioAvanzado.getNombre());
        values.put(COLUMN_DESCRIPCION_EJERCICIO_AVANZADO, ejercicioAvanzado.getDescripcion());
        values.put(COLUMN_IMG_EJERCICIO_AVANZADO, ejercicioAvanzado.getImagen());
        values.put(COLUMN_URL_VIDEO_AVANZADO, ejercicioAvanzado.getUrl());
        values.put(COLUMN_PROGRESO_AVANZADO, ejercicioAvanzado.getProgreso());
        db.insert(TABLE_EJERCICIOS_AVANZADOS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<EjercicioConvencional> obtenerTodosLosEjerciciosConvencionales() {
        List<EjercicioConvencional> ejercicioConvencionals = new ArrayList<EjercicioConvencional>();
        String query = "SELECT * FROM " + TABLE_EJERCICIOS_CONVENCIONALES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                EjercicioConvencional ejercicioConvencional = new EjercicioConvencional();
                ejercicioConvencional.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_EJERCICIO_COVENCIONAL)));
                ejercicioConvencional.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL)));
                ejercicioConvencional.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL)));
                ejercicioConvencional.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_VIDEO_CONVENCIONAL)));
                ejercicioConvencional.setImagen(cursor.getInt(cursor.getColumnIndex(COLUMN_IMG_EJERCICIO_COVENCIONAL)));
                ejercicioConvencionals.add(ejercicioConvencional);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ejercicioConvencionals;
    }

    public List<EjercicioConvencional> obtenerEjerciciosConvencionales() {
        List<EjercicioConvencional> ejerciciosConvencionales = new ArrayList<EjercicioConvencional>();

        String query = "SELECT * FROM " + TABLE_EJERCICIOS_CONVENCIONALES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                EjercicioConvencional ejerciciosConvencional = new EjercicioConvencional();
                ejerciciosConvencional.setId(Integer.parseInt(cursor.getString(0)));
                ejerciciosConvencional.setNombre(cursor.getString(1));
                ejerciciosConvencionales.add(ejerciciosConvencional);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ejerciciosConvencionales;
    }

    @SuppressLint("Range")
    public List<EjercicioAvanzado> obtenerEjerciciosAvanzados() {
        List<EjercicioAvanzado> ejerciciosAvanzados = new ArrayList<EjercicioAvanzado>();

        String query = "SELECT * FROM " + TABLE_EJERCICIOS_AVANZADOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                EjercicioAvanzado ejercicioAvanzados = new EjercicioAvanzado();
                ejercicioAvanzados.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_EJERCICIO_AVANZADO)));
                ejercicioAvanzados.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_EJERCICIO_AVANZADO)));
                ejercicioAvanzados.setDescripcion(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_EJERCICIO_AVANZADO)));
                ejercicioAvanzados.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_VIDEO_CONVENCIONAL)));
                ejercicioAvanzados.setImagen(cursor.getInt(cursor.getColumnIndex(COLUMN_IMG_EJERCICIO_COVENCIONAL)));
                ejercicioAvanzados.setProgreso(cursor.getInt(cursor.getColumnIndex(COLUMN_PROGRESO_AVANZADO)));
                ejerciciosAvanzados.add(ejercicioAvanzados);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ejerciciosAvanzados;
    }

    //APARTADO PARA PROGRESIONES:
    public void addProgresion(String nombreProgresion, String ejercicioAvanzado) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_PROGRESION, nombreProgresion);
        values.put(COLUMN_EJERCICIO_AVANZADO, ejercicioAvanzado);

        db.insert(TABLE_PROGRESIONES, null, values);
        db.close();
    }

    public List<Progresion> getProgresiones() {
        List<Progresion> listaProgresiones = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_PROGRESIONES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Progresion progresion = new Progresion();
                progresion.setId(cursor.getInt(0));
                progresion.setNombre(cursor.getString(1));
                //CONTROL DE BOOLEANO
                int completadoInt = cursor.getInt(2);
                boolean completado = completadoInt == 1;
                progresion.setCompletado(completado);
                progresion.setEjercicioAvanzado(cursor.getString(3));
                listaProgresiones.add(progresion);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaProgresiones;
    }
    //EJERCICIOS-PROGRESIONES

    public void addEjercicioProgresion(EjercicioProgresion ejercicioProgresion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION, ejercicioProgresion.getIdProgresion());
        values.put(COLUMN_ID_EJERCICIO_CONVENCIONAL, ejercicioProgresion.getIdEjercicioConvencional());
        values.put(COLUMN_REPETICIONES_EJERCICIO, ejercicioProgresion.getRepeticiones());
        values.put(COLUMN_SEGUNDOS_EJERCICIO, ejercicioProgresion.getSegundos());
        values.put(COLUMN_NUMERO_EJERCICIO, ejercicioProgresion.getNumeroEjercicio());

        db.insert(TABLE_EJERCICIOS_PROGRESIONES, null, values);
        db.close();
    }

    public List<EjercicioProgresion> getEjerciciosProgresionByProgresion(int idProgresion) {
        List<EjercicioProgresion> listaEjerciciosProgresion = new ArrayList<EjercicioProgresion>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_EJERCICIOS_PROGRESIONES
                + " WHERE " + COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION + " = " + idProgresion;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                EjercicioProgresion ejercicioProgresion = new EjercicioProgresion();
                ejercicioProgresion.setId(cursor.getInt(0));
                ejercicioProgresion.setIdProgresion(cursor.getString(1));
                ejercicioProgresion.setIdEjercicioConvencional(cursor.getString(2));
                ejercicioProgresion.setRepeticiones(cursor.getInt(3));
                ejercicioProgresion.setSegundos(cursor.getInt(4));
                ejercicioProgresion.setNumeroEjercicio(cursor.getInt(5));

                listaEjerciciosProgresion.add(ejercicioProgresion);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaEjerciciosProgresion;
    }

    //METODO QUE SIRVE PARA COGER LOS EJERCICIOS DE UNA PROGRESION ESPECIFICA
    @SuppressLint("Range")
    public List<EjercicioProgresion> obtenerEjerciciosProgresionDesdeBD(String nombreProgresion) {
        List<EjercicioProgresion> ejercicios = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EJERCICIOS_PROGRESIONES + " WHERE " + COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION + " = '" + nombreProgresion + "' ORDER BY " + COLUMN_NUMERO_EJERCICIO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                EjercicioProgresion ejercicio = new EjercicioProgresion();
                ejercicio.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_EJERCICIO_PROGRESION)));
                ejercicio.setIdProgresion(cursor.getString(cursor.getColumnIndex(COLUMN_ID_PROGRESION_EJERCICIO_PROGRESION)));
                ejercicio.setIdEjercicioConvencional(cursor.getString(cursor.getColumnIndex(COLUMN_ID_EJERCICIO_CONVENCIONAL)));
                ejercicio.setRepeticiones(cursor.getInt(cursor.getColumnIndex(COLUMN_REPETICIONES_EJERCICIO)));
                ejercicio.setSegundos(cursor.getInt(cursor.getColumnIndex(COLUMN_SEGUNDOS_EJERCICIO)));
                ejercicio.setNumeroEjercicio(cursor.getInt(cursor.getColumnIndex(COLUMN_NUMERO_EJERCICIO)));
                ejercicio.setTipo(cursor.getInt(cursor.getColumnIndex(COLUMN_TIPO_EJERCICIO_COVENCIONAL)));
                ejercicios.add(ejercicio);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ejercicios;
    }

    //ACTUALIZAR PROGRESIÓN A COMPLETADO
    public void actualizarProgresionEnBD(Progresion progresion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPLETADO, progresion.isCompletado() ? 1 : 0);
        values.put(COLUMN_EJERCICIO_AVANZADO, progresion.getEjercicioAvanzado());
        db.update(TABLE_PROGRESIONES, values, COLUMN_NOMBRE_PROGRESION + " = ?", new String[] { progresion.getNombre() });
        db.close();
    }
    //ESTABLECER PROGRESO EJERCICIO AVANZADO
    public void actualizarProgresoEjercicioAvanzado(String nombreEjercicioAvanzado, double progreso) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROGRESO_AVANZADO, progreso);

        db.update(TABLE_EJERCICIOS_AVANZADOS, values, COLUMN_NOMBRE_EJERCICIO_AVANZADO + " = ?", new String[]{nombreEjercicioAvanzado});

        db.close();
    }


}