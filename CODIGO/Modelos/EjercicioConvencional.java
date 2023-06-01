package com.example.eztrain.models;


public class EjercicioConvencional {
    private int id;
    private String nombre;

    private String descripcion;
    private int imagen;

    private String url;

    public EjercicioConvencional() {}

    public EjercicioConvencional(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;

    }

    public EjercicioConvencional(String nombre, String descripcion , int imagen, String url) {
        this.descripcion = descripcion;
        this.nombre = nombre;

        this.imagen = imagen;
        this.url = url;
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


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
