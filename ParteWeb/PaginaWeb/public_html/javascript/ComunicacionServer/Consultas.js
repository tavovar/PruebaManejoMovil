/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getConsultas(pFuncion) {
    var mData = "";
    HacerGet(kNombreServidor + kNombreConsultas, mData, pFuncion);
}

function getConsulta(pIdConsultas, pFunction) {
    var mData = "";
    HacerGet(kNombreServidor + kNombreConsulta + "?pk_consultas=" + pIdConsultas, mData, pFunction);
}

function ResolverConsulta(pIdConsultas, pFunction) {
    var mData = {"pk_consultas": pIdConsultas};
    HacerPost(kNombreServidor + kNombreConsulta, mData, pFuncion);
}

function EliminarConsulta() {
    var mIdConsulta = document.getElementById("idConsulta").value;
    if (mIdConsulta === "" || mIdConsulta ===null) {
        return;
    }
    alert("Enviando la solicitud" + mIdConsulta);

    var mData = null;
    HacerDelete(kNombreServidor + kNombreConsulta + "?pk_consultas=" + mIdConsulta, mData, function (data) {
        alert("Consulta eliminada");
        document.getElementById("idConsulta").value = "";
        CargarElementosConsultas();
    });
}