package com.example.salesapp;

public class Venta {

    private String nombre, nombre_cliente, id_cliente;
    private float valor_unitario, valor_total;
    private int cantidad, id;

    public Venta(int id, String nombre, String nombre_cliente, String id_cliente, float valor_unitario, float valor_total, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.nombre_cliente = nombre_cliente;
        this.id_cliente = id_cliente;
        this.valor_unitario = valor_unitario;
        this.valor_total = valor_total;
        this.cantidad = cantidad;
    }

    public Venta() {
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(float valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
