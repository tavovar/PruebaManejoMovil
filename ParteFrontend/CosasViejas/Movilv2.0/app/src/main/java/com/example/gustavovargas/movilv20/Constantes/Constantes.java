package com.example.gustavovargas.movilv20.Constantes;

/**
 * Created by gustavovargas on 24/10/14.
 */
public class Constantes {
    public static String pregunta = "Complete la siguiente afirmación: Las señales verticales de prevención son de color";
    public static int numMiniPregNotaAprov = 2;
    public static int maximoNumPregTestTeo = 4;
    public static int numMiniPregNotaAprovPractico = 2;
    public static int maximoNumPregTestPractico = 4;
    public static int tiempoVibracionIncorrecto = 100;
    public static long[] tiempoVibracionAprovado = { 200,200, 200, 200, 200, 200, 400};


    // CONSTANTES DE JSON PREGUNTAS Parcticas
    public static String idPreguntaPractica = "pk_pregunta_dinamica";
    public static String pathImagenPregunta = "ruta_imagen";
    public static String interacionPreguntaPractica = "accion";
    public static String velocidadPreguntaPractica = "velocidad";
    public static int velocidadMinima = 0;
    public static int velocidadMaxima = 150;

    // CONSTANTES DE JSON PREGUNTAS
    public static String idPregunta = "pk_preguntas";
    public static String encabezadoPregunta = "encabezado";
    public static String respuestaCorrecta = "correcta";
    public static String respuestaIncorrecta_1 = "incorrecta_1";
    public static String respuestaIncorrecta_2 = "incorrecta_2";
    public static String idSubseccion = "fk_subseccion";

    // CONSTANTES DE JSON RESULTADOS PRUEBAS
    public static String preguntasCorrectasResultado = "preguntas_correctas";
    public static String fecha = "fecha";
    public static String idUsuarioResultado = "fk_usuario";
    public static String tipoResultado = "tipo";
    public static int tipoTeorico = 1;
    public static int tipoPractico = 2;


    // CONSTANTES DE JSON RESULTADOS PRUEBAS
    public static String idUsuarioSugerencia = "fk_usuario";
    public static String sugerencia = "descripcion";
    public static String email = "correo";
    public static int respuestaSugerenciaSi = 1;
    public static int respuestaSugerenciaNo = 0;
    public static String respuestaSugerencia = "respuesta";

    // CONSTANTES DE URL
    public static String URLPreguntas = "http://www.pruebamanejomovil.com:8080/app/preguntas";
    public static String URLUsuarios = "http://www.pruebamanejomovil.com:8080/app/usuarios";
    public static String URLPreguntasPracticas = "http://www.pruebamanejomovil.com:8080/app/preguntas_dinamicas";
    public static String URLLugares = "http://www.pruebamanejomovil.com:8080/app/lugares";
    public static String URLResultados = "http://www.pruebamanejomovil.com:8080/app/historiales";
    public static String URLResultadosSemanas = "http://www.pruebamanejomovil.com:8080/app/historiales_semanas";
    public static String URLResultadosMeses = "http://www.pruebamanejomovil.com:8080/app/historiales_meses";
    public static String URLSugerencias = "http://www.pruebamanejomovil.com:8080/app/sugerencias";
    public static String URLImagenes = "http://www.pruebamanejomovil.com/imagenes_dinamicas/";
    public static String jsonParameters = "Json";
    public static String identificacion = "a7b3e20c2c626aee9bc63cd4997f7624";

    // CONSTANTES DEL TIMER
    public static int tiempoLimite = 5;
    public static int refrecamientoDelTiempo = 1;


    public static String semana = "WEEK(fecha)";
    public static String mes = "MONTH(fecha)";


    public static String tipoFacebook = "1";
    public static String tipoTwitter = "2";
    public static String idUsuarioLogin = "id_red_social";
    public static String tipoUsuarioLogin = "tipo_usuario";
    public static String idUsuarioBaseDatos = "pk_id";

}
