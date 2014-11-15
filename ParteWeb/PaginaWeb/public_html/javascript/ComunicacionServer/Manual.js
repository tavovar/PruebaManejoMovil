/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getManuales(pFuncion) {
    var mData = "";
    HacerGet(kNombreServidor + kNombreManuales, mData, pFuncion);
}

function getManuales(pFuncion) {
    var mData = "";
    HacerGet(kNombreServidor + kNombreManuales, mData, pFuncion);
}


function addManuales() {
    var mNombre = document.getElementById("txtNom").value;
    var dropDownList = document.getElementById("idDropPaises");
    var mIdPais = dropDownList.options[dropDownList.selectedIndex].value;

    if (mNombre.trim() === "") {
        alert("No ha introducido ningun nombre");
        return;
    }

    if (document.getElementById("dpManualesAgregador").length !== 0) {
        alert("Ya hay un manual asociado a este país");
        return;
    }
    
    alert("Enviando la solicitud");

    var mData = {"nombre": mNombre, "fk_pais": mIdPais};
    HacerPost(kNombreServidor + kNombreManuales, mData, function (data) {
        alert("Manual agregado");
        document.getElementById("txtNom").value = "";
        CargarElementosManuales();
    });
}

function getSecciones(pFuncion) {
    var dropDownList = document.getElementById("dpManualesAgregados");
    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;
    getSeccionesAux(mIdManual, pFuncion);
}

function getSeccionesAux(mIdManual, pFuncion) {
    HacerGet(kNombreServidor + kNombreSecciones + "?fk_manual=" + mIdManual, "", pFuncion);
}

function getSubseccionesAux(mIdSeccion, pFuncion) {
    HacerGet(kNombreServidor + kNombreSubsecciones + "?fk_seccion=" + mIdSeccion, "", pFuncion);
}

function addSeccion() {
   
    var mNombre = document.getElementById("txtNom").value;
    var dropDownList = document.getElementById("dpManualesAgregados");
    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;

    if (mNombre.trim() === "") {
        alert("No ha introducido ningun nombre");
        return;
    }
    
     alert("Enviando la solicitud");

    var mData = {"nombre": mNombre, "fk_manual": mIdManual};
    HacerPost(kNombreServidor + kNombreSecciones, mData, function (data) {
        alert("Sección agregada");
        document.getElementById("txtNom").value = "";
        CargarElementosSecciones();
    });
}

function addSubSeccion() {
    
    var mNombre = document.getElementById("txtNombre").value;
    var mDescripcion = document.getElementById("txtDescripcion").value;

    var dropDownList = document.getElementById("dpSeccionesAgregados");
    var mIdSeccion = dropDownList.options[dropDownList.selectedIndex].value;

    if (mNombre.trim() === "" || mDescripcion.trim() === "") {
        alert("No deje ningún espacio vacío");
        return;
    }
    
    alert("Enviando la solicitud");

    var mData = {"nombre": mNombre, "fk_seccion": mIdSeccion, "descripcion": mDescripcion};
    HacerPost(kNombreServidor + kNombreSubsecciones, mData, function (data) {
        alert("Subsección agregada");
        document.getElementById("txtNombre").value = "";
        document.getElementById("txtDescripcion").value = "";
        CargarElementosSubsecciones();
    });
}

function borrarManual() {

    var dropDownList = document.getElementById("dpManualesAgregador");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdManual = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");

    var mData = null;
    HacerDelete(kNombreServidor + kNombreManuales + "?pk_manual=" + mIdManual, mData, function (data) {
        alert("Manual eliminado");
        CargarElementosManuales();
    });
}

function borrarSeccion() {

    var dropDownList = document.getElementById("dpSeccionesAgregadosEliminar");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdSeccion = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");

    var mData = null;
    HacerDelete(kNombreServidor + kNombreSecciones + "?pk_seccion=" + mIdSeccion, mData, function (data) {
        alert("Seccion eliminada");
        CargarElementosSecciones();
    });
}

function borrarSubseccion() {


    var dropDownList = document.getElementById("dpSubseccionesAgregados");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdSubseccion = dropDownList.options[dropDownList.selectedIndex].value;
    
    alert("Enviando la solicitud");
    var mData = null;
    HacerDelete(kNombreServidor + kNombreSubsecciones + "?pk_subseccion=" + mIdSubseccion, mData, function (data) {
        alert("Subseccion eliminada");
        CargarElementosSubsecciones();
    });
}