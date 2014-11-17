package com.example.gustavovargas.movilv20.Modelos;

import com.example.gustavovargas.movilv20.Constantes.Constantes;

import java.util.Date;

/**
 * Created by gustavovargas on 15/11/14.
 */
public class Resultado {

    public String tipoTest;
    public String fecha;
    public String correctas;
    public String incorrectas;
    public String nota;

    public Resultado(int correctas, String fecha, int tipo) {
        if (tipo == 1 || tipo == 2) {
            this.correctas = correctas + "";
            this.fecha = fecha;
            this.incorrectas = (Constantes.maximoNumPregTestPractico-correctas)+"";
            float resultado;
            if (tipo == Constantes.tipoPractico) {
                this.tipoTest = "Práctico";
                resultado = (correctas * 1.0f) / (Constantes.maximoNumPregTestPractico * 1.0f) * 100;
            } else {
                this.tipoTest = "Teórico";
                resultado = (correctas * 1.0f) / (Constantes.maximoNumPregTestTeo * 1.0f) * 100;
            }
            this.nota = resultado + "";
        }else if (tipo == 3){
            this.correctas = correctas + "";
            this.fecha = "-----------------";
            this.tipoTest = "Semana";
            this.nota = "-----";
            this.incorrectas = "";
        }else{
            this.correctas = correctas + "";
            this.fecha = "-----------------";
            this.tipoTest = "Mes";
            this.nota = "-----";
            this.incorrectas = "";
        }
    }



}
