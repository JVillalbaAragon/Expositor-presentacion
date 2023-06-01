package com.example.eztrain.models;

public class Progresion {
    private int id;
    private String nombre;
    private boolean completado;
    private String ejercicioAvanzado;
    private int imgProgresion;


    public Progresion() {}

    public Progresion(String nombre, boolean completado, String ejercicioAvanzado) {
        this.nombre = nombre;
        this.completado = completado;
        this.ejercicioAvanzado = ejercicioAvanzado;
    }

    public Progresion(String nombre, boolean completado, String ejercicioAvanzado, int imagen) {
        this.nombre = nombre;
        this.completado = completado;
        this.ejercicioAvanzado = ejercicioAvanzado;
        this.imgProgresion = imagen;
    }

    public int getImgProgresion() {
        return imgProgresion;
    }

    public void setImgProgresion(int imgProgresion) {
        this.imgProgresion = imgProgresion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getEjercicioAvanzado() {
        return ejercicioAvanzado;
    }

    public void setEjercicioAvanzado(String ejercicioAvanzado) {
        this.ejercicioAvanzado = ejercicioAvanzado;
    }

    @Override
    public String toString() {
        return "Progresion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", completado=" + completado +
                ", ejercicioAvanzado=" + ejercicioAvanzado +
                '}';
    }
}

