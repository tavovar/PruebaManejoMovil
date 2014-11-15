/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sideBar= '<div class="ui vertical menu">\
                        <div class="header item">\
                            <i class="edit icon"></i>\
                            Pruebas Teoricas\
                        </div>\
                        <a class="{0} item" href="AdminPreguntas.html">\
                            Preguntas Teóricas\
                        </a>\
                        <a class="{7} item" href="AdminPreguntasDinamicas.html">\
                            Preguntas Dinámicas\
                        </a>\
                        <div class="header item">\
                            <i class="book icon"></i>\
                            Información\
                        </div>\
                        <a class="{8} item" href="AdminPaises.html">\
                            Paises\
                        </a>\
                        <a class="{1} item" href="AdminManuales.html">\
                            Manuales\
                        </a>\
                        <a class="{2} item" href="AdminSecciones.html">\
                            Secciones\
                        </a>\
                        <a class="{3} item" href="AdminSubsecciones.html">\
                            Subsecciones\
                        </a>\
                        <div class="header item">\
                            <i class="icon map marker"></i>\
                            Ubicaciones\
                        </div>\
                        <a class="{4} item" href="AdminSucursales.html">\
                            Sucursales\
                        </a>\
                        <div class="header item">\
                            <i class="comment outline icon"></i>\
                            Sugerencias\
                        </div>\
                        <a class="{6} item" href="AdminConsultas.html">\
                            Ver Consultas\
                        </a>\
                        <div class="header item">\
                            <i class="user icon"></i>\
                            Usuarios\
                        </div>\
                        <a class="{5} item" onclick="CerrarSesion();">\
                            Salir\
                        </a>\
                </div>';

function getSideBar(pIndice){
    var resultado = sideBar.replace("{"+pIndice+"}", "active teal");
    document.getElementById("sideBar").innerHTML=resultado;
}
