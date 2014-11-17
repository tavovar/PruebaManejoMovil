package com.example.neyrojas.pmm.Modelos;

public class SubseccionesManual {
    private String nombre;
    private String texto;
    private int pk_subseccion;
    private int fk_seccion;

    public SubseccionesManual() {
        // TODO Auto-generated constructor stub
    }

    public SubseccionesManual(String nombre, String texto, int pk_subseccion, int fk_seccion) {
        super();
        this.nombre = nombre;
        this.texto = texto;
        this.fk_seccion = pk_subseccion;
        this.fk_seccion = fk_seccion;
    }

    //Set y get para Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Set y get para Pk_subseccion
    public int getSubseccion() {
        return pk_subseccion;
    }
    public void setSubseccion(int pk_subseccion) {
        this.pk_subseccion = pk_subseccion;
    }

    //Set y get para Fk_seccion
    public int getSeccion() {
        return fk_seccion;
    }
    public void setSeccion(int fk_seccion) {
        this.fk_seccion = fk_seccion;
    }

    //Set y get para Texto
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}

