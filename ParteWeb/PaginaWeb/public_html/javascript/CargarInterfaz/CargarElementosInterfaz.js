/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function CargarDropDownList(pIdDropDown, pKeyNom, pKeyValue, pJson) {
    var mLargo = pJson.length;
    var mResultado = "";
    for (var i = 0; i < mLargo; i++) {
        mResultado += '<option value="' + pJson[i][pKeyValue] + '">' + (pJson[i][pKeyNom]).substring(0, 25) + '</option>';
    }
    document.getElementById(pIdDropDown).innerHTML = mResultado;
}

function CargarPaises() {
    getPaises(function (data) {
        CargarDropDownList("idDropPaises", "nombre", "pk_pais", data);
    });
}

function CargarElementosManuales() {
    CargarPaises();

    getManuales(function (data) {
        CargarDropDownList("dpManualesAgregador", "nombre", "pk_manual", data);
    });
}
function CargarElementosSecciones() {
    getManuales(function (data) {
        CargarDropDownList("dpManualesAgregados", "nombre", "pk_manual", data);
        if (data[0] === undefined || data[0] === null)
            return;
        getSeccionesAux(data[0].pk_manual, function (data) {
            CargarDropDownList("dpSeccionesAgregadosEliminar", "nombre", "pk_seccion", data);
        });
    });
}

function CargarElementosSubsecciones() {
    getManuales(function (data) {
        CargarDropDownList("dpManualesAgregados", "nombre", "pk_manual", data);
        if (data[0] === undefined || data[0] === null)
            return;
        getSeccionesAux(data[0].pk_manual, function (data) {
            CargarDropDownList("dpSeccionesAgregados", "nombre", "pk_seccion", data);
            if (data[0] === undefined || data[0] === null)
                return;
            getSubseccionesAux(data[0].pk_seccion, function (data) {
                CargarDropDownList("dpSubseccionesAgregados", "nombre", "pk_subseccion", data);
                CargarPreguntasTeoricas();
            });
        });
    });
}

//Funciones utilizadas para cargar datos de la seccion de consultas

function CargarTodaLaConsulta(pJson) {
    document.getElementById("txtAsunto").value = pJson[0]["asunto"];
    document.getElementById("txtCorreo").value = pJson[0]["correo"];
    document.getElementById("txtDescripcion").value = pJson[0]["descripcion"];
}

function CargarConsulta(pIdConsulta) {
    getConsulta(pIdConsulta, function (data) {
        if (data[0] === undefined || data[0] === null)
            return;
        CargarTodaLaConsulta(data);
    });
    document.getElementById("idConsulta").value = pIdConsulta;
}

function CargarListaConsultas(pIdDivConsultas, pJson) {
    var contenido = "";
    var mResultadoParcial;
    var mLargo = pJson.length;
    //alert(pJson[0]["descripcion"]);
    var cascaronObjeto = ' <div class = "item" onclick="{1}"> ' +
            '<img class = "ui avatar image" src = "images/mensaje-logo.png" > ' +
            '<div class = "content" > ' +
            '<div class = "header" > {2} </div> ' +
            '{3}' +
            '</div> ' +
            '</div> ';

    for (var i = 0; i < mLargo; i++) {
        mResultadoParcial = cascaronObjeto.replace("{1}", "CargarConsulta(" + pJson[i]["pk_consultas"] + ");");
        mResultadoParcial = mResultadoParcial.replace("{2}", (pJson[i]["asunto"]).substring(0, 20));
        mResultadoParcial = mResultadoParcial.replace("{3}", (pJson[i]["descripcion"]).substring(0, 25) + "...");

        contenido += mResultadoParcial;
    }

    document.getElementById(pIdDivConsultas).innerHTML = contenido;
}

function CargarElementosConsultas() {
    getConsultas(function (data) {
        if (data[0] === undefined || data[0] === null){
            document.getElementById("listaConsultas").innerHTML = "";
            return;
        }
        CargarListaConsultas("listaConsultas", data);
        CargarConsulta(data[0]["pk_consultas"]);
    });
}

//Funciones utilizadas para cargar las partes de sucursales 

function CargarElementosSucursales() {
    getPaises(function (data) {
        CargarDropDownList("dpPaises", "nombre", "pk_pais", data);
    });

    getSucursales(function (data) {
        CargarDropDownList("dpSucursalesAgregadas", "nombre", "pk_sucursal", data);
    });
}

// Funcioens utilizadas para cargar informacion de la interfaz de preguntas

function CargarManualesInterfaz() {
    getManuales(function (data) {
        CargarDropDownList("dpManualesAgregados", "nombre", "pk_manual", data);
        CargarSeccionesInterfaz();
    });
}
function CargarSeccionesInterfaz() {
    var dropDownList = document.getElementById("dpManualesAgregados");
    if (dropDownList.options[dropDownList.selectedIndex] === undefined || dropDownList === undefined ) {
        document.getElementById("dpManualesAgregados").innerHTML = "";
        document.getElementById("dpSeccionesAgregados").innerHTML = "";
        document.getElementById("dpSubseccionesAgregados").innerHTML = "";
        document.getElementById("dpPreguntas").innerHTML = "";
        return;
    }

    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;
    getSeccionesAux(mIdManual, function (data) {
        CargarDropDownList("dpSeccionesAgregados", "nombre", "pk_seccion", data);
        CargarSubSeccionesInterfaz();
    });
}

function CargarSubSeccionesInterfaz() {
    var dropDownList = document.getElementById("dpSeccionesAgregados");

    if (dropDownList.options[dropDownList.selectedIndex] === undefined || dropDownList === undefined ) {
        document.getElementById("dpSeccionesAgregados").innerHTML = "";
        document.getElementById("dpSubseccionesAgregados").innerHTML = "";
        document.getElementById("dpPreguntas").innerHTML = "";
        return;
    }
    var mIdSseccion = dropDownList.options[dropDownList.selectedIndex].value;
    getSubseccionesAux(mIdSseccion, function (data) {
        CargarDropDownList("dpSubseccionesAgregados", "nombre", "pk_subseccion", data);
        CargarPreguntasTeoricas();
    });
}

function CargarPreguntasTeoricas() {
    var dropDownList = document.getElementById("dpSubseccionesAgregados");

    if (dropDownList.options[dropDownList.selectedIndex] === undefined || dropDownList === undefined ) {
        document.getElementById("dpSubseccionesAgregados").innerHTML = "";
        document.getElementById("dpPreguntas").innerHTML = "";
        return;
    }
    var mIdSubseccion = dropDownList.options[dropDownList.selectedIndex].value;
    getPreguntas(mIdSubseccion, function (data) {
        CargarDropDownList("dpPreguntas", "encabezado", "pk_preguntas", data);
    });
}

function CargarPreguntasDinamicas() {
    getPreguntasDinamicas(function (data) {
        CargarDropDownList("dpPreguntasDinamicas", "ruta_imagen", "pk_pregunta_dinamica", data);
    });
}