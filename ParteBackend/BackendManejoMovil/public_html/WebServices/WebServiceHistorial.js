/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminHistorial = require('../Logica/AdminHistorial.js');


ObtenerHistorial = function (req, res) {
    var admin = new adminHistorial.AdminHistorial();
    admin.getTodoHistorial(function (data) {
        res.send(data);
    });
};

exports.ObtenerHistorial = ObtenerHistorial;