/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function CargarDropDownList(pIdDropDown, pKeyNom, pKeyValue, pJson) {
    var mLargo = pJson.length;
    var mResultado = "";
    for (var i = 0; i < mLargo; i++) {
        mResultado += '<option value="' + pJson[i][pKeyValue] + '">' + pJson[i][pKeyNom] + '</option>';
    }
    document.getElementById(pIdDropDown).innerHTML = mResultado;
}

function CargarElementosManuales() {
    getPaises(function (data) {
        CargarDropDownList("idDropPaises", "nombre", "pk_pais", data);
    });

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
            });
        });
    });
}