package com.example.eztrain.models;

public class EjercicioAvanzado {

    private int id;
    private String nombre;

    private String descripcion;
    private int imagen;
    private int progreso;

    private String url;

    public EjercicioAvanzado() {

    }
    //MOSTRAR EN RECYCLER
    public EjercicioAvanzado(int id, String nombre, String descripcion, int imagen, int progreso , String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.progreso = progreso;
        this.url = url;
    }
    //USO GENERAL
    public EjercicioAvanzado(String nombre, String descripcion, int imagen, int progreso , String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.progreso = progreso;
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
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

    //METODO COMPLEMENTARIO:

}
