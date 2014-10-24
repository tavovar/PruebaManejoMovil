/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mongoose = require('mongoose'),
        Schema = mongoose.Schema;

var tvshowSchema = new Schema({
    nombre: {type: String},
    latitud: {type: String},
    longitud: {type: String},
    telefono: {type: String},
    pais: {type: String}
});

module.exports = mongoose.model('SUCURSALES', tvshowSchema);