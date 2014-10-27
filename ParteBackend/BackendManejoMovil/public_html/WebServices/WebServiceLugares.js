/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var AdminLugares = require('../Logica/AdminLugares.js');


ObtenerLugaresSucursales = function (req, res) {
    var x = new AdminLugares.AdminLugares();

    x.getLugares(1, function (data) {
        res.send(data);
    });
};

exports.ObtenerLugaresSucursales = ObtenerLugaresSucursales;