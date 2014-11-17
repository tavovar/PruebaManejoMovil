package com.example.neyrojas.pmm.Modelos;

public class Manual {
    private String nombre;
    private int pk_manual;
    private int fk_pais;

    public Manual() {
        // TODO Auto-generated constructor stub
    }

    public Manual(String nombre, int pk_manual, int fk_pais) {
        super();
        this.nombre = nombre;
        this.pk_manual = pk_manual;
        this.fk_pais = fk_pais;
    }

    //Set y get para Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Set y get para Pk_Manual
    public int getManual() {
        return pk_manual;
    }
    public void setManual(int pk_manual) {
        this.pk_manual = pk_manual;
    }

    //Set y get para Fk_pais
    public int getPais() {
        return fk_pais;
    }
    public void setPais(int fk_pais) {
        this.fk_pais = fk_pais;
    }
}
