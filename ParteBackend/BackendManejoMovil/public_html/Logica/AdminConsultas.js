/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var AdminConsulta = function () {

    var conexionConsulta = require('../Datos/ConexionConsultas.js');
    this.ConConsulta = new conexionConsulta.ConexionConsulta();

    this.agregarConsulta = function (pObjeto, callback) {
        if (pObjeto!== undefined && pObjeto.correo === undefined){
            pObjeto = JSON.parse(pObjeto);
        }
        
        var descripcion = pObjeto.descripcion;
        
        if (pObjeto.respuesta === "0"){
            descripcion += " \n \n No deseo recibir respuesta ";
        }
        
        if (pObjeto.respuesta === "0"){
            descripcion += " \n \n No deseo recibir respuesta ";
        }
        else{
            descripcion += " \n \n Deseo recibir respuesta ";
        }
        
        var mObjeto = {correo: pObjeto.correo, asunto: "Consulta", descripcion:descripcion, resuelto:0, fk_usuario:pObjeto.fk_usuario};
        this.ConConsulta.agregarConsulta(mObjeto, callback);
    };

    this.getTodasConsultas = function (callback) {
        this.ConConsulta.getTodasConsultas(callback);
    };

    this.getInfoConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.getInfoConsulta(pIdConsulta, callback);
    };
    this.resolverConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.resolverConsulta(pIdConsulta, callback);
    };
    
    this.borrarConsulta = function (pIdConsulta, callback) {
        this.ConConsulta.borrarConsulta(parseInt(pIdConsulta), callback);
    };
};

exports.AdminConsulta = AdminConsulta;