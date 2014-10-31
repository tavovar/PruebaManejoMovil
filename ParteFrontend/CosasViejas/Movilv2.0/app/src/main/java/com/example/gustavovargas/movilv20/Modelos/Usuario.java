package com.example.gustavovargas.movilv20.Modelos;

/**
 * Created by gustavovargas on 31/10/14.
 */
public class Usuario {

    public String nombre;
    public double id;
    public String correo;

    private static Usuario instance = null;

    public static Usuario getInstance() {
        if(instance == null) {
            instance = new Usuario();
        }
        return instance;
    }

    protected Usuario(){
    }
}
