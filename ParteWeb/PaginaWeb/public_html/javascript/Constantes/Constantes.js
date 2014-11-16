/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var kNombreServidor = "http://localhost:";
//var kNombreServidor = "http://pruebamanejomovil.com:";

var prefijoApp = "8080/app/";
var prefijoWeb = "8080/web/";

var kNombrePaises = prefijoApp + "paises";
var kNombreLugares = prefijoApp + "lugares";
var kNombreSecciones = prefijoApp + "secciones";
var kNombreSubsecciones = prefijoApp + "subsecciones";

var kNombreManuales = prefijoWeb + "manuales";
var kNombrePreguntas = prefijoWeb + "preguntas";
var kNombrePreguntasDinamicas = prefijoWeb + "preguntas_dinamicas";
var kNombreConsultas = prefijoWeb + "consultas";
var kNombreConsulta = prefijoWeb + "consulta";
var kNombreUsuarios = prefijoWeb + "usuariosweb";

var kPassIdentificacion = "a7b3e20c2c626aee9bc63cd4997f7624"

//Autentificacion de la pagina web

if (document.location.pathname.split('/').pop() !== "index.html") {
    if (localStorage.getItem("Autentificado") === null || localStorage.getItem("Autentificado") === '0') {
        document.location = "index.html";
    }
}



function CerrarSesion() {
    localStorage.setItem("Autentificado", 0);
    document.location = "index.html";
}