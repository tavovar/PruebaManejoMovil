/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var adminManual = require('../Logica/AdminManual.js');


ObtenerManuales = function (req, res) {
    var admin = new adminManual.AdminManual();
    admin.getManuales(function (data) {
        res.send(data);
    });
};

exports.ObtenerManuales = ObtenerManuales;