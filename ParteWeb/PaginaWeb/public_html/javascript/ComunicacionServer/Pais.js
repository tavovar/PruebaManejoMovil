/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getPaises(pFuncion) {
    var mData = null;
    HacerGet(kNombreServidor + kNombrePaises, mData, pFuncion);
}

function addPaises(pFuncion) {
    var mNombre = document.getElementById("txtNom").value;

    if (mNombre.trim() === "") {
        alert("No ha introducido ningun nombre");
        return;
    }

    var mData = {"nombre": mNombre};
    HacerPost(kNombreServidor + kNombrePaises, mData, function (data) {
        alert("País Agregado");
        CargarPaises();
    });
}

function borrarPais() {

    var dropDownList = document.getElementById("idDropPaises");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdPais = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");
    
    var mData = null;
    HacerDelete(kNombreServidor + kNombrePaises + "?pk_pais=" + mIdPais, mData, function (data) {
        alert("País eliminado");
        CargarPaises();
    });
}
