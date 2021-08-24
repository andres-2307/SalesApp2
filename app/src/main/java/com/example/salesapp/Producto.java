package com.example.salesapp;

public class Producto {


    private String nombre, precio, id,tipo;
    private int foto;




    public Producto(String nombre, String precio, String id, String tipo, int foto) {
        this.nombre = nombre;
        this.precio= precio;
        this.id=id;
        this.tipo=tipo;
        this.foto=foto;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
