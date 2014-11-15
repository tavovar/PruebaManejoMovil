/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addPregunta() {
    var mDescripcion = document.getElementById("txtDescripcion").value;
    var correcta = document.getElementById("correcta").value;
    var incorrecta1 = document.getElementById("incorrecta1").value;
    var incorrecta2 = document.getElementById("incorrecta2").value;


    var dropDownList = document.getElementById("dpSubseccionesAgregados");
    var mIdSubseccion = dropDownList.options[dropDownList.selectedIndex].value;

    if (incorrecta2.trim() === "" || incorrecta1.trim() === "" || correcta.trim() === "" || mDescripcion.trim() === "") {
        alert("No deje ningún espacio vacío");
        return;
    }
    
    alert("Enviando la solicitud");

    var mData = {"encabezado": mDescripcion, "fk_subseccion": mIdSubseccion,
        "correcta": correcta, "incorrecta_1": incorrecta1, "incorrecta_2": incorrecta2};

    HacerPost(kNombreServidor + kNombrePreguntas, mData, function (data) {
        alert("Pregunta agregada");
        document.getElementById("txtDescripcion").value = "";
        document.getElementById("correcta").value = "";
        document.getElementById("incorrecta1").value = "";
        document.getElementById("incorrecta2").value = "";
        CargarPreguntasTeoricas();
    });
}

function getPreguntas(fk_subseccion, pFuncion) {
    var mData = null;
    HacerGet(kNombreServidor + kNombrePreguntas + "?fk_subseccion=" + fk_subseccion, mData, pFuncion);
}

function EliminarPregunta() {

    var dropDownList = document.getElementById("dpPreguntas");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdPregunta = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");

    var mData = null;
    HacerDelete(kNombreServidor + kNombrePreguntas + "?pk_pregunta=" + mIdPregunta, mData, function (data) {
        alert("Pregunta eliminada");
        CargarPreguntasTeoricas();
    });
}