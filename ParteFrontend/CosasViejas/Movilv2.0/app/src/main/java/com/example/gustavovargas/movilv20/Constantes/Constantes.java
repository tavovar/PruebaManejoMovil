package com.example.gustavovargas.movilv20.Constantes;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class Constantes {
    public static String pregunta = "Complete la siguiente afirmación: Las señales verticales de prevención son de color";
    public static int numMiniPregNotaAprov = 30;
    public static int maximoNumPregTestTeo = 40;
    public static int colorBotonCorrecto = 746376764;
    public static int colorBotonIncorrecto = 489349237;
    public static int colorBotonSeleccion = 999999999;
    public static int tiempoVibracionCorrecto = 100;
    public static int tiempoVibracionIncorrecto = 100;
    public static long[] tiempoVibracionAprovado = { 200,200, 200, 200, 200, 200, 400};




    // CONSTANTES DE JSON PREGUNTAS
    public static String idPregunta = "pk_preguntas";
    public static String encabezadoPregunta = "encabezado";
    public static String respuestaCorrecta = "correcta";
    public static String respuestaIncorrecta_1 = "incorrecta_1";
    public static String respuestaIncorrecta_2 = "incorrecta_2";
    public static String idSubseccion = "fk_subseccion";

    // CONSTANTES DE JSON RESULTADOS PRUEBAS
    public static String preguntasCorrectasResultado = "preguntas_correcta";
    public static String fecha = "fecha";
    public static String idUsuarioResultado = "fk_usuario";
    public static String tipoResultado = "tipo";
    public static int tipoTeorico = 1;
    public static int tipoPractico = 2;


    // CONSTANTES DE JSON RESULTADOS PRUEBAS
    public static String idUsuarioSugerencia = "fk_usuario";
    public static String sugerencia = "sugerencia";
    public static String email = "email";
    public static int respuestaSugerenciaSi = 1;
    public static int respuestaSugerenciaNo = 0;
    public static String respuestaSugerencia = "respuesta";

    // CONSTANTES DE URL
    public static String URLPreguntas = "http://www.pruebamanejomovil.com/app/preguntas";
    public static String URLLugares = "http://www.pruebamanejomovil.com/app/lugares";
    public static String URLResultados = "http://www.pruebamanejomovil.com/app/historiales";
    public static String URLSugerencias = "http://www.pruebamanejomovil.com/app/sugerencias";
    public static String jsonParameters = "Json";
    public static String jsonid = "39383939393";

}
