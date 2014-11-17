package com.example.neyrojas.pmm.Modelos;

public class Pais {
    private String nombre;
    private int pk_pais;

    public Pais() {
        // TODO Auto-generated constructor stub
    }

    public Pais(String nombre, int pk_pais) {
        super();
        this.nombre = nombre;
        this.pk_pais = pk_pais;
    }

    //Set y get para Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Set y get para Pk_pais
    public int getPais() {
        return pk_pais;
    }
    public void setPais(int pk_pais) {
        this.pk_pais = pk_pais;
    }
}
