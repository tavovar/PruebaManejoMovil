package com.example.neyrojas.pmm.Modelos;

public class Lugares {
    private String nombre;
    private double latitud;
    private double longitud;
    private String telefono;
    private String lugar;
    private int pk_sucursal;
    private int fk_pais;


    public Lugares() {
        // TODO Auto-generated constructor stub
    }

    public Lugares(String nombre, double latitud, double longitud,
                   String telefono, String lugar, int pk_sucursal, int fk_pais) {
        super();
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
        this.lugar = lugar;
        this.pk_sucursal = pk_sucursal;
        this.fk_pais = fk_pais;
    }

    //Set y get para Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Set y get para Latitud
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    //Set y get para Longitud
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    //Set y get para Telefono
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Set y get para Lugar
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    //Set y get para Pk_sucursal
    public int getSucursal() {
        return pk_sucursal;
    }
    public void setSucursal(int pk_sucursal) {
        this.pk_sucursal = pk_sucursal;
    }

    //Set y get para Fk_pais
    public int getPais() {
        return fk_pais;
    }
    public void setPais(int fk_pais) {
        this.fk_pais = fk_pais;
    }
}

