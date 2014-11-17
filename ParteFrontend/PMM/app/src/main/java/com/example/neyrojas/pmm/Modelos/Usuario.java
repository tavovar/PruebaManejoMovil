package com.example.neyrojas.pmm.Modelos;

/**
 * Created by Ney Rojas on 16/11/2014.
 */
public class Usuario {
    public String nombre;
    public int id;
    public long idF;
    public String correo;
    public int pais;

    private static Usuario instance = null;

    public static Usuario getInstance() {
        if(instance == null) {
            instance = new Usuario();
            instance.pais=1;
            instance.id=1;
        }
        return instance;
    }

    protected Usuario(){
    }
}
