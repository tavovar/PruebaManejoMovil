/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imMuestra').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

$("#ipImagen").change(function () {
    readURL(this);
});

$('#eventForm').submit(function (e) {
    e.preventDefault();

    var mAccion = document.getElementById("correcta").value;
    var mArchivo = document.getElementById("ipImagen").value;

    if (mAccion === "" || mArchivo === "") {
        alert("No deje ningún espacio vacío.");
    }

    alert("Enviando solicitud");

    var fd = new FormData($(this)[0]);
    
    fd["identificacion"] = kPassIdentificacion;
   
    $.ajax({
        url: kNombreServidor + kNombrePreguntasDinamicas,
        data: fd,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function (data) {
            alert("Pregunta Dinámica Agregada");
            document.getElementById("correcta").value = "";
            document.getElementById("ipImagen").value = "";
            $('#imMuestra').attr('src', "");
            CargarPreguntasDinamicas();
        }
    });
});

function getPreguntasDinamicas(pFuncion) {
    var mData = null;
    HacerGet(kNombreServidor + kNombrePreguntasDinamicas, mData, pFuncion);
}

function EliminarPreguntaDinamica() {

    var dropDownList = document.getElementById("dpPreguntasDinamicas");
    if (dropDownList.length === 0) {
        return;
    }
    var mIdPreguntaDinamica = dropDownList.options[dropDownList.selectedIndex].value;
    alert("Enviando la solicitud");

    var mData = null;
    HacerDelete(kNombreServidor + kNombrePreguntasDinamicas + "?pk_pregunta_dinamica=" + mIdPreguntaDinamica, mData, function (data) {
        alert("Pregunta dinámica eliminada");
        CargarPreguntasDinamicas();
    });
}