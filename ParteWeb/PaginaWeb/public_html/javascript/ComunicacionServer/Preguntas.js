/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addPregunta(){
    alert("Enviando la solicitud");
    var mDescripcion = document.getElementById("txtDescripcion").value;
    var correcta = document.getElementById("correcta").value;
    var incorrecta1 = document.getElementById("incorrecta1").value;
    var incorrecta2 = document.getElementById("incorrecta2").value;
    
    
    var dropDownList = document.getElementById("dpSubseccionesAgregados");
    var mIdSubseccion = dropDownList.options[dropDownList.selectedIndex].value;
    
    if (incorrecta2.trim() === "" || incorrecta1.trim()==="" || correcta.trim()==="" || mDescripcion.trim()==="") {
        alert("No deje ningún espacio vacío");
        return;
    }

    var mData={"encabezado": mDescripcion, "fk_subseccion":mIdSubseccion,
        "correcta":incorrecta1, "incorrecta_1":incorrecta1, "incorrecta_2":incorrecta2};

    HacerPost( kNombreServidor + kNombrePreguntas, mData, function(data){
        alert("Subsección agregada");
    });
}