/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function HacerPost(pUrl, pData, pFuncion) {
    SolicitudAjax("POST", pUrl, pData, pFuncion);
}

function HacerGet(pUrl, pData, pFuncion) {
    SolicitudAjax("GET", pUrl, pData, pFuncion);
}

function HacerDelete(pUrl, pData, pFuncion) {
    SolicitudAjax("DELETE", pUrl, pData, pFuncion);
//    $.ajax({
//        url: pUrl,
//        type: 'DELETE',
//        success: pFuncion
//    });
}

function SolicitudAjax(pTipoMetodo, pUrl, pData, pFuncion) {
    if (pData === null || pData === undefined || pData === ""){
        pData = {};
    }
    pData["identificacion"] = Constantes.kPassIdentificacion;
    $.ajax({
        type: pTipoMetodo,
        url: pUrl,
        data: pData,
        success: pFuncion,
        dataType: "json"
    });
}