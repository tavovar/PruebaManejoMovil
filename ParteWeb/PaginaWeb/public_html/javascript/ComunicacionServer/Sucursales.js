/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getSucursales(pFuncion) {
    var mData = "";
    HacerGet(kNombreServidor + kNombreLugares, mData, pFuncion);
}

function AgregarSucursales() {
    var mNombre = document.getElementById("txtNombre").value;
    var mTelefono = document.getElementById("txtTelefono").value;
    var mLugar = document.getElementById("txtLugar").value;
    var mLatitud = document.getElementById("latFld").value;
    var mLongitud = document.getElementById("lngFld").value;

    var dropDownList = document.getElementById("dpPaises");
    var mIdPais = dropDownList.options[dropDownList.selectedIndex].value;

    if (mNombre.trim() === "" || mTelefono.trim() === "" || mLugar.trim() === ""
            || mLatitud.trim() === "" || mLongitud.trim() === "" || mIdPais.trim() === "") {
        alert("No deje espacios vacíos.");
        return;
    }

    alert("Enviando los datos");

    var mData = {"nombre": mNombre, "fk_pais": mIdPais, "lugar": mLugar, "latitud": mLatitud, "longitud": mLongitud, "telefono": mTelefono};
    HacerPost(kNombreServidor + kNombreLugares, mData, function (data) {
        alert("Sucursal Agregada");
        document.getElementById("txtNombre").value = "";
        document.getElementById("txtTelefono").value = "";
        document.getElementById("txtLugar").value = "";
        document.getElementById("latFld").value = "";
        document.getElementById("lngFld").value = "";
        CargarElementosSucursales();
    });
}

function EliminarSucursal() {
    
    var dropDownList = document.getElementById("dpSucursalesAgregadas");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdSucursal = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");
    
    var mData = null;
    HacerDelete(kNombreServidor + kNombreLugares + "?pk_sucursal=" + mIdSucursal, mData, function (data) {
        alert("Sucursal eliminada");
        CargarElementosSucursales();
    });
}