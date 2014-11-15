/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function identificacionWeb() {
    var mNombre = document.getElementById("nombre").value;
    var mContrasena = document.getElementById("contrasena").value;
    var mData = {"nombre": mNombre, "contrasena": mContrasena};
    HacerPost(kNombreServidor + kNombreUsuarios, mData, function (data) {
        if (true === data) {
            localStorage.setItem("Autentificado",1);
            document.location = "AdminPreguntas.html";
        }
        else {
            alert("Usuario o contraseña incorrecta" + data );
        }
    });

}
