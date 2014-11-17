package com.example.neyrojas.pmm.Modelos;

public class SeccionesManual {
    private String nombre;
    private int fk_manual;
    private int pk_seccion;
    private int indice;

    public SeccionesManual() {
        // TODO Auto-generated constructor stub
    }

    public SeccionesManual(int indice, String nombre, int fk_manual, int pk_seccion) {
        super();
        this.nombre = nombre;
        this.fk_manual = fk_manual;
        this.indice = indice;
        this.pk_seccion = pk_seccion;
    }

    //Set y get para Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Set y get para Fk_Manual
    public int getManual() {
        return fk_manual;
    }
    public void setManual(int fk_manual) {
        this.fk_manual = fk_manual;
    }

    //Set y get para Pk_Seccion
    public int getSeccion() {
        return pk_seccion;
    }
    public void setSeccion(int pk_seccion) {
        this.pk_seccion = pk_seccion;
    }

    //Set y get para Indice
    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }

}
