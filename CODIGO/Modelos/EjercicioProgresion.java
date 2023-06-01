package com.example.eztrain.models;

public class EjercicioProgresion {
    private int id;
    private String idProgresion;
    private String idEjercicioConvencional;
    private int repeticiones;
    private int segundos;
    private int numeroEjercicio;

    private int tipo;

    // constructores

    public EjercicioProgresion() {}

    //SIN ID, USO GENERAL
    public EjercicioProgresion(String idProgresion, String idEjercicioConvencional, int repeticiones, int segundos, int numeroEjercicio, int tipo) {
        this.idProgresion = idProgresion;
        this.idEjercicioConvencional = idEjercicioConvencional;
        this.repeticiones = repeticiones;
        this.segundos = segundos;
        this.numeroEjercicio = numeroEjercicio;
        this.tipo = tipo;
    }

    public EjercicioProgresion(int id, String idProgresion, String idEjercicioConvencional, int repeticiones, int segundos, int numeroEjercicio, int tipo) {
        this.id = id;
        this.idProgresion = idProgresion;
        this.idEjercicioConvencional = idEjercicioConvencional;
        this.repeticiones = repeticiones;
        this.segundos = segundos;
        this.numeroEjercicio = numeroEjercicio;
        this.tipo = tipo;
    }
    //GETTERS
    public int getId() {
        return id;
    }

    public String getIdProgresion() {
        return idProgresion;
    }

    public String getIdEjercicioConvencional() {
        return idEjercicioConvencional;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getNumeroEjercicio() {
        return numeroEjercicio;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setIdProgresion(String idProgresion) {
        this.idProgresion = idProgresion;
    }

    public void setIdEjercicioConvencional(String idEjercicioConvencional) {
        this.idEjercicioConvencional = idEjercicioConvencional;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setNumeroEjercicio(int numeroEjercicio) {
        this.numeroEjercicio = numeroEjercicio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
