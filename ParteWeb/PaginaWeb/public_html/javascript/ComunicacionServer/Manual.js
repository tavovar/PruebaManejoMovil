/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getManuales(pFuncion) {
    var mData="";
    HacerGet( kNombreServidor + kNombreManuales, mData, pFuncion);
}

function getManuales(pFuncion) {
    var mData="";
    HacerGet( kNombreServidor + kNombreManuales, mData, pFuncion);
}


function addManuales(){
    var mNombre = document.getElementById("txtNom").value;
    var dropDownList = document.getElementById("idDropPaises");
    var mIdPais = dropDownList.options[dropDownList.selectedIndex].value;
    
    if (mNombre.trim()==="") {
        alert("No ha introducido ningun nombre");
        return;
    }

    var mData={"nombre":mNombre, "fk_pais":mIdPais};
    HacerPost( kNombreServidor + kNombreManuales, mData, function(data){
        alert("Manual agregado");
    });
}

function getSecciones(pFuncion) {
    var dropDownList = document.getElementById("dpManualesAgregados");
    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;
    getSeccionesAux(mIdManual,pFuncion);
}

function getSeccionesAux(mIdManual, pFuncion){
    HacerGet( kNombreServidor + kNombreSecciones + "?fk_manual="+mIdManual, "", pFuncion);
}

function getSubseccionesAux(mIdSeccion, pFuncion){
    HacerGet( kNombreServidor + kNombreSubsecciones + "?fk_seccion="+mIdSeccion, "", pFuncion);
}

function addSeccion(){
    alert("Enviando la solicitud0");
    var mNombre = document.getElementById("txtNom").value;
    var dropDownList = document.getElementById("dpManualesAgregados");
    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;
    
    if (mNombre.trim()==="") {
        alert("No ha introducido ningun nombre");
        return;
    }

    var mData={"nombre":mNombre, "fk_manual":mIdManual};
    HacerPost( kNombreServidor + kNombreSecciones, mData, function(data){
        alert("Sección agregada");
    });
}

function addSubSeccion(){
    alert("Enviando la solicitud");
    var mNombre = document.getElementById("txtNombre").value;
    var mDescripcion = document.getElementById("txtDescripcion").value;
    
    var dropDownList = document.getElementById("dpSeccionesAgregados");
    var mIdSeccion = dropDownList.options[dropDownList.selectedIndex].value;
    
    if (mNombre.trim()==="" || mDescripcion.trim()==="") {
        alert("No deje ningún espacio vacío");
        return;
    }

    var mData={"nombre":mNombre, "fk_seccion":mIdSeccion, "descripcion": mDescripcion};
    HacerPost( kNombreServidor + kNombreSubsecciones, mData, function(data){
        alert("Subsección agregada");
    });
}