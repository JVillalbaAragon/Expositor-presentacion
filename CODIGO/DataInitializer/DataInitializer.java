package com.example.eztrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eztrain.db.DBHelper;
import com.example.eztrain.models.EjercicioAvanzado;
import com.example.eztrain.models.EjercicioConvencional;
import com.example.eztrain.models.EjercicioProgresion;
import com.example.eztrain.models.Progresion;


public class DataInitializer {

    private final DBHelper dbHelper;


    public DataInitializer(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void initializeData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Comprueba si la tabla EjerciciosConvencionales está vacía
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_EJERCICIOS_CONVENCIONALES, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            // Añadir los ejercicios si no existen registros en la BD. (Lo creará en un móvil recien instalado)

            addEjercicioConvencional(new EjercicioConvencional("Push up",
                    "Para comenzar con las flexiones debemos colocarnos en el suelo, en decúbito ventral o boca abajo." +
                            " Apoyamos las manos en el suelo, justo por debajo de los hombros y separadas de este ancho." +
                            " Con los pies levemente separados o juntos, despegamos el torso del suelo impulsándonos con los brazos y el pecho.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=0pkjOk0EiAk" ));
            addEjercicioConvencional(new EjercicioConvencional("Air Squat",
                    "La sentadilla o squat es un movimiento que se inicia de pie, mirando al frente y con la espalda recta, mientras los pies se separan del ancho de los hombros," +
                            " siempre mirando al frente y sin curvar la espalda, debemos descender los glúteos flexionando la rodilla y la cadera,  y cuidando que la rodilla no pase de la punta del pie ni sobrepase los 90 grados de flexión. Descendemos hasta que los muslos quedan paralelos al suelo y desde allí debemos elevarnos lentamente.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=rMvwVtlqjTE"));

            addEjercicioConvencional(new EjercicioConvencional("Rows", "Coloca tu cuerpo en bloque, paralelo al suelo. Mantén una postura firme en todo momento." +
                    "Lleva tus codos hacia dentro, favoreciendo la rotación externa de tus hombros." +
                    "Estira completamente tus brazos para trabajar con todo el recorrido posible." +
                    "Trata de tocar la barra o anillas con el pecho al subir en cada repetición." +
                    "Mantén tu cabeza alineada con el resto de tu columna para evitar forzar la zona cervical.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=Zu3h6Ai9O0Q"));

            addEjercicioConvencional(new EjercicioConvencional("Straight Bar Dip", "El ejercicio de Straight Bar Dip es uno de los más difíciles, pero también de los más efectivos para la parte superior del cuerpo. En este ejercicio es necesario que mantengas el peso del cuerpo sobre la barra durante todo el tiempo utilizando solo la parte superior del cuerpo para levantar y bajar el pecho de la barra.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=gTyaLS1Xwh4"));

            addEjercicioConvencional(new EjercicioConvencional("Crunch", "ejercicio para el abdomen en el que te recuestas el suelo con las piernas flexionadas para levantar brevemente tu espalda de manera que ejerzas toda la presión en tu abdomen. A diferencia de las abdominales tradicionales, esta práctica se caracteriza por su movimiento controlado para aislar el trabajo abdominal y que de este modo sea mucho más eficiente para tonificar.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=Xyd_fa5zoEU"));

            addEjercicioConvencional(new EjercicioConvencional("Pull up", "Colócate debajo de la barra de dominadas. Salta y agárrala por encima colocando las manos un poco más abiertas que la anchura de los hombros. Para empezar, cuélgate con brazos y piernas extendidas. Coloca los omóplatos hacia abajo y hacia atrás al tiempo que activas la espalda, el torso y los glúteos. Elévate hasta llevar la barbilla por encima de la barra. Durante el movimiento, piensa en llevar la barra hacia el pecho. Mantén los codos cerca del torso y no arquees las lumbares más de lo natural.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=HRV5YKKaeVw"));
            addEjercicioConvencional(new EjercicioConvencional("Plank", "Para realizar este ejercicio debes apoyar tus antebrazos (o tus brazos) sobre el suelo, de tal manera que tu cuerpo forme una línea recta de la cabeza a los pies",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=pSHjTRCQxIw"));
            addEjercicioConvencional(new EjercicioConvencional("Dip", "Para comenzar con la realización del movimiento se requiere de una máquina de paralelas en la cual debemos apoyar las manos en sus apoya brazos y elevar el cuerpo de manera de despegar los pies del suelo. Con las piernas en el aire, el tronco ligeramente inclinado hacia adelante y los brazos soportando el peso del cuerpo, iniciamos el ejercicio." +
                    "Inspiramos y flexionamos los codos de manera de que el pecho descienda hasta el nivel de las barras, siempre conservando las piernas y el torso inmóvil. Sólo debe moverse el cuerpo hacia abajo y arriba por la flexión de brazos.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=c3ZGl4pAwZ4"));
            addEjercicioConvencional(new EjercicioConvencional("Negative Muscle up", "Este ejercicio se empieza en la posición del Muscle Up y seguimos el recorrido inverso para trabajar la musculatura implicada en la realización de el Muscle Up",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=WydJPNETzVk"));
            addEjercicioConvencional(new EjercicioConvencional("Pseudo Push up", "Para realizar este ejercicio tienes que ponerte en una posición parecida a las flexiones o push ups, en esta posición llevamos nuestras manos a la altura de las caderas y se realiza" +
                    "el el recorrido de la flexión con las escápulas contraidas y el core activado.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=VJd_q5w-VeY"));
            addEjercicioConvencional(new EjercicioConvencional("Superman", "Empieza en el suelo tumbado sobre tu abdomen con las extremidades estiradas para quedar lo más recto posible, en esta posición levante tus brazos y piernas, separándolas del suelo para realizar el ejercicio.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=z6PJMT2y8GQ"));
            addEjercicioConvencional(new EjercicioConvencional("Leg Raise", "Siéntate en el suelo con el core activado y las palmas de las manos en el suelo, en esta posición sin dejar de apretar el core levanta únicamente las piernas hasta donde puedas y bájalas lentamente.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=-1k2b8dG16g"));
            addEjercicioConvencional(new EjercicioConvencional("Tuck Planche", "Empieza con posición de plancha y acomodate poco a poco de forma en la que te sostengas solo con tus brazos con las piernas contraidas hacía tu pecho.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=YHkHgJOAe9A"));
            addEjercicioConvencional(new EjercicioConvencional("Frog Stand", "Empieza en posición de plancha y acomódate de forma que te sostengas únicamente con tus brazos en paralelo al suelo, las piernas tienen que quedar contraidas con las rodillas en el exterior de tus codos.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=Xlv-19qFBsc"));
            addEjercicioConvencional(new EjercicioConvencional("Straddle Planche", "Con ayuda de unas paralelas ponte en posición de plancha y eleva tus piernas en forma de 'V' mantén el core activado y no arquees la espalda.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=FmhmHAQ7qTQ&t=144s"));
            addEjercicioConvencional(new EjercicioConvencional("Diamond Push up", "Empieza con la misma posición del push up tradicional, luego junta tus manos para formar un 'rombo' en el suelo, con esta flexión tan cerrada lograrás tener un triceps de acero.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=jaxbEHLC4qU"));
            addEjercicioConvencional(new EjercicioConvencional("Lever Push up", "Comienza con la posición de push up tradicional, lleva uno de tus brazos estirados hacia uno de tus lados de forma controlada, en esta posición realiza una flexión con el brazo que queda pegada a tu cuerpo, de esta forma se aisla la carga en un solo brazo.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=CLclje2rMKs"));
            addEjercicioConvencional(new EjercicioConvencional("Planche Straddle Pike", "Consiste en manos a la anchura de hombros, escápulas en protracción y piernas abiertas y extendidas. Todo el cuerpo debe mantenerse en bloque y paralelo al suelo.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=ZnFI6jHPUmA"));
            addEjercicioConvencional(new EjercicioConvencional("Half-lay Planche", "Esta progresión es casi una plancha completa con la misma alineación corporal. La única diferencia son las rodillas flexionadas que disminuyen la carga. Una vez que extiendas las rodillas, estarás en el último paso.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=YCaPhASFNgE"));
            addEjercicioConvencional(new EjercicioConvencional("Declined Push up", "Este ejercicio se realiza excactamente igual que el Push up pero con mayor carga, tienes que situar los pies en un lugar elevado y las manos estarán al nivel del suelo, con esta inclinación se consigue concentrar una mayor cantidad de peso por lo que lo convierte en un ejercicio más exigente",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=SKPab2YC8BE"));
            addEjercicioConvencional(new EjercicioConvencional("Single-Leg Isometric Hold", "Ejercicio en el que tienes que aislar el peso del cuerpo en una pierna, ya sea tirando una de las piernas hacía delante o detrás de ti, dobla la rodilla para bajar y mantente en esta posición cuando notes tensión muscular.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=6UbvxN7AetU"));
            addEjercicioConvencional(new EjercicioConvencional("Assisted Pistol Squat", "Idéntico al Pistol Squat pero con asistencia para conseguirlo, eleva una de tus piernas estirada, lleva tus manos hacía delante para aguantar el equilibrio y realiza el movimiento de sentadilla con un objeto detrás que pueda servirte de apoyo.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=6UbvxN7AetU"));
            addEjercicioConvencional(new EjercicioConvencional("Rolling Pistol Squat", "Párate en un pie y baja en una sentadilla. Continúa hasta llegar completamente abajo en la sentadilla (cuando tu trasero toque o casi toque tu talón), de forma lenta y controlada, luego rueda suavemente hacia atrás. Rueda hacia adelante y aprovecha tu impulso para levantarte sobre el pie y ponerte de pie para una repetición. Completa todas las repeticiones en una pierna y luego cambia a la otra.",
                    R.drawable.ic_ejercicios,
                    "https://www.youtube.com/watch?v=qucrbuBOhK0"));

        }
        //EJERCICIOS AVANZADOS

        // Comprueba si la tabla EjerciciosAvanzados está vacía
        Cursor cursor2 = db.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_EJERCICIOS_AVANZADOS, null);
        cursor2.moveToFirst();
        int count2 = cursor2.getInt(0);
        cursor2.close();

        if (count2 == 0) {
            // añadir los ejercicios si no existen registros en la BD.
            addEjercicioAvanzado(new EjercicioAvanzado("Muscle Up",
                    "Empieza como una dominada pero con mayor implicación muscular, la técnica varía en que levantas todo el cuerpo hasta dejar la barra a la altura de tus caderas",
                    R.drawable.ic_muestra_com,
                    0,
                    "https://www.youtube.com/watch?v=0pkjOk0EiAk" ));
            addEjercicioAvanzado(new EjercicioAvanzado("One-arm Push up",
                    "Ejercicio mucho más demandante que los tradicionales Push up al implicar únicamente un brazo, trabajará de forma excelente tanto la fuerza de tus brazos como la de tu core",
                    R.drawable.ic_muestra_com,
                    0,
                    "https://www.youtube.com/watch?v=0pkjOk0EiAk" ));
            addEjercicioAvanzado(new EjercicioAvanzado("Full Planche",
                    "Evolución de la plancha, esta variante tiene una posición parecida con la diferencia de que la única parte ",
                    R.drawable.ic_muestra_com,
                    0,
                    "https://www.youtube.com/watch?v=0pkjOk0EiAk" ));

            addEjercicioAvanzado(new EjercicioAvanzado("Pistol Squat",
                    "Prueba 1",
                    R.drawable.ic_muestra_com,
                    0,
                    "https://www.youtube.com/watch?v=0pkjOk0EiAk" ));
        }


        //PROGRESION
        Cursor cursor3 = db.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_PROGRESIONES, null);
        cursor3.moveToFirst();
        int count3 = cursor3.getInt(0);
        cursor3.close();
        //PROGRESION
        if (count3 == 0) {
            // añadir las progresiones si no existen registros en la BD.
            //Progresiones Muscle Up
            addProgresion(new Progresion("Muscle Up principiante", false, "Muscle Up", R.drawable.ic_intensity_beginner));
            addProgresion(new Progresion("Muscle Up intermedio", false, "Muscle Up", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Muscle Up avanzado", false, "Muscle Up", R.drawable.ic_intensity_advanced));
            //Progresiones One-Arm Push Up
            addProgresion(new Progresion("One-arm Push Up principiante", false, "One-arm Push up", R.drawable.ic_intensity_beginner));
            addProgresion(new Progresion("One-arm Push Up intermedio", false, "One-arm Push up", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("One-arm Push Up avanzado", false, "One-arm Push up", R.drawable.ic_intensity_advanced));
            //Progresiones Full Planche
            addProgresion(new Progresion("Full Planche principiante", false, "Full Planche", R.drawable.ic_intensity_beginner));
            addProgresion(new Progresion("Full Planche intermedio", false, "Full Planche", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Full Planche avanzado", false, "Full Planche", R.drawable.ic_intensity_advanced));
            addProgresion(new Progresion("Full Planche maestro", false, "Full Planche,", R.drawable.ic_intensity_advanced));
            //Progresiones Pistol Squat
            addProgresion(new Progresion("Pistol Squat principiante", false, "Pistol Squat", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Pistol Squat intermedio", false, "Pistol Squat", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Pistol Squat avanzado", false, "Pistol Squat", R.drawable.ic_intensity_intermediate));
            //Progresiones handstand push up
            addProgresion(new Progresion("Handstand Push Up principiante" , false, "Handstand Push Up", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Handstand Push Up intermedio" , false, "Handstand Push Up", R.drawable.ic_intensity_intermediate));
            addProgresion(new Progresion("Handstand Push Up avanzado" , false, "Handstand Push Up", R.drawable.ic_intensity_intermediate));
        }

        //EJERCICIOS PROGRESION
        Cursor cursor4 = db.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_EJERCICIOS_PROGRESIONES, null);
        cursor4.moveToFirst();
        int count4 = cursor4.getInt(0);
        cursor4.close();

        if (count4 == 0) {
            // Añadir ejercicios a las progresiones si no existen registros en la BD
            //MUSCLE UP principinte
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Push up", 10, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Rows", 10, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Pull up", 10, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Dip", 15, 0, 4, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Push up", 10, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up principiante", "Pull up", 8, 0, 6, 1));

            //MUSCLE UP intermedio
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Push up", 12, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Negative Muscle up", 3, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Pull up", 15, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Rows", 15, 0, 4, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Dip", 20, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Pull up", 10, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Rows", 10, 0, 7, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up intermedio", "Dip", 12, 0, 8, 1));

            //MUSCLE UP AVANZADO
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Push up", 20, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Negative Muscle up", 8, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Pull up", 20, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Rows", 25, 0, 4, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Dip", 30, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Pull up", 15, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Rows", 15, 0, 7, 1));
            addEjercicioProgresion(new EjercicioProgresion("Muscle Up avanzado", "Dip", 20, 0, 8, 1));

            //ONE-ARM PUSH UP PRINCIPIANTE
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Pseudo Push up", 5, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Push up", 12, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Lever Push up", 8, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Plank", 0, 30, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Push up", 12, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up principiante", "Diamond Push up", 5, 0, 6, 1));

            //ONEARM PUSH UP INTERMEDIO
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Pseudo Push up", 10, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Push up", 12, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Lever Push up", 8, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Plank", 0, 60, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Push up", 12, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Diamond Push up", 10, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up intermedio", "Leg Raise", 10, 0, 7, 1));

            //ONE-ARM PUSH UP AVANZADO
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Pseudo Push up", 15, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Declined Push up", 10, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Lever Push up", 15, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Plank", 0, 90, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Push up", 20, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Diamond Push up", 15, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("One-arm Push Up avanzado", "Leg Raise", 15, 0, 7, 1));

            //FULL PLANCHE PRINCIPIANTE
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Plank", 0, 30, 1, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Pseudo Push up", 10, 0, 2, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Tuck Planche", 0, 30, 3, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Leg Raise", 15, 0, 4, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Crunch", 10, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche principiante", "Plank", 0, 20, 6, 2));

            //FULL PLANCHE INTERMEDIO
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Plank", 0, 60, 1, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Frog Stand", 0, 45, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Planche Straddle Pike", 0, 15, 3, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Pseudo Push up", 15, 0, 4, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Tuck Planche", 0, 60, 5, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Leg Raise", 25, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Crunch", 20, 0, 7, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche intermedio", "Plank", 0, 40, 8, 2));

            //FULL PLANCHE AVANZADO
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Leg Raise", 25, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Plank", 0, 120, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Frog Stand", 0, 60, 3, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Planche Straddle Pike", 0, 30, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Pseudo Push up", 25, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Tuck Planche", 0, 90, 6, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Leg Raise", 25, 0, 7, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Crunch", 30, 0, 8, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Plank", 0, 90, 9, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche avanzado", "Half-lay Planche", 0, 20, 10, 2));


            //FULL PLANCHE MAESTRO
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Leg Raise", 40, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Plank", 0, 240, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Frog Stand", 0, 120, 3, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Planche Straddle Pike", 0, 60, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Pseudo Push up", 35, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Tuck Planche", 0, 90, 6, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Leg Raise", 30, 0, 7, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Crunch", 35, 0, 8, 1));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Plank", 0, 120, 9, 2));
            addEjercicioProgresion(new EjercicioProgresion("Full Planche maestro", "Half-lay Planche", 0, 45, 10, 2));

            //PISTOL SQUAT principiante
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat principiante", "Air Squat", 12, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat principiante", "Single-Leg Isometric Hold", 10, 20, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat principiante", "Assisted Pistol Squat", 10, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat principiante", "Plank", 0, 30, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat principiante", "Rolling Pistol Squat", 10, 0, 5, 1));

            //PISTOL SQUAT intermedio
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Air Squat", 20, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Single-Leg Isometric Hold", 0, 40, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Assisted Pistol Squat", 15, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Plank", 0, 60, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Rolling Pistol Squat", 20, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Single-Leg Isometric Hold", 0, 40, 6, 2));


            //PISTOL SQUAT Avanzado
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Air Squat", 30, 0, 1, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Single-Leg Isometric Hold", 0, 60, 2, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Assisted Pistol Squat", 25, 0, 3, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Plank", 0, 90, 4, 2));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Rolling Pistol Squat", 25, 0, 5, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Assisted Pistol ", 30, 0, 6, 1));
            addEjercicioProgresion(new EjercicioProgresion("Pistol Squat intermedio", "Air Squat", 25, 0, 1, 1));



        }
    }

    private void addEjercicioConvencional(EjercicioConvencional ejercicioConvencional) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOMBRE_EJERCICIO_CONVENCIONAL, ejercicioConvencional.getNombre());
        values.put(DBHelper.COLUMN_DESCRIPCION_EJERCICIO_CONVENCIONAL, ejercicioConvencional.getDescripcion());
        values.put(DBHelper.COLUMN_IMG_EJERCICIO_COVENCIONAL, ejercicioConvencional.getImagen());
        values.put(DBHelper.COLUMN_URL_VIDEO_CONVENCIONAL, ejercicioConvencional.getUrl());
        db.insert(DBHelper.TABLE_EJERCICIOS_CONVENCIONALES, null, values);

    }

    private void addEjercicioAvanzado(EjercicioAvanzado ejercicioAvanzado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOMBRE_EJERCICIO_AVANZADO, ejercicioAvanzado.getNombre());
        values.put(DBHelper.COLUMN_DESCRIPCION_EJERCICIO_AVANZADO, ejercicioAvanzado.getDescripcion());
        values.put(DBHelper.COLUMN_IMG_EJERCICIO_AVANZADO, ejercicioAvanzado.getImagen());
        values.put(DBHelper.COLUMN_PROGRESO_AVANZADO, ejercicioAvanzado.getProgreso());
        values.put(DBHelper.COLUMN_URL_VIDEO_AVANZADO, ejercicioAvanzado.getUrl());
        db.insert(DBHelper.TABLE_EJERCICIOS_AVANZADOS, null, values);
    }

    private void addProgresion(Progresion progresion) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOMBRE_PROGRESION, progresion.getNombre());
        values.put(DBHelper.COLUMN_COMPLETADO, progresion.isCompletado());
        values.put(DBHelper.COLUMN_EJERCICIO_AVANZADO, progresion.getEjercicioAvanzado());
        db.insert(DBHelper.TABLE_PROGRESIONES, null, values);

    }

    private void addEjercicioProgresion(EjercicioProgresion ejercicioProgresion) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID_PROGRESION, ejercicioProgresion.getIdProgresion());
        values.put(DBHelper.COLUMN_ID_EJERCICIO_CONVENCIONAL, ejercicioProgresion.getIdEjercicioConvencional());
        values.put(DBHelper.COLUMN_NUMERO_EJERCICIO, ejercicioProgresion.getNumeroEjercicio());
        values.put(DBHelper.COLUMN_SEGUNDOS_EJERCICIO, ejercicioProgresion.getSegundos());
        values.put(DBHelper.COLUMN_REPETICIONES_EJERCICIO, ejercicioProgresion.getRepeticiones());
        values.put(DBHelper.COLUMN_TIPO_EJERCICIO_COVENCIONAL, ejercicioProgresion.getTipo());
        db.insert(DBHelper.TABLE_EJERCICIOS_PROGRESIONES, null, values);
    }


}





