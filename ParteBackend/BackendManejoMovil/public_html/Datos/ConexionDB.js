/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ConexionDB = function () {
    var mysql = require('mysql');

    this.msgError = {"error": "-1"};

    this.host = "localhost";
    this.user = "root";
    this.pass = "786Xenoblade786";
    this.db = "mydb";
    
    this.connection = null;

    this.conectar = function () {
        this.connection = mysql.createConnection({
            host: this.host,
            user: this.user,
            password: this.pass,
            database: this.db
        });
        this.connection.connect();
        //this.connection.escape();
    };
    
    this.CerrarConexion = function (){
        this.connection.end();
    };
    
    this.getConexion = function(){
        return this.connection;
    };
    
    this.getDatos = function(pQuery, callBack){
        console.log("Realizando un request" + pQuery);
        this.connection.query(pQuery, function (err, rows) {
            if (err) {
                throw err;
            } else {
                //console.log(rows);
                callBack(rows);
            }
        });
        this.CerrarConexion();
    };
    
    this.saveDato = function(pQuery, pObjeto, callBack){
        this.connection.query(pQuery, pObjeto, function (err, rows) {
            if (err) {
                throw err;
            } else {
                callBack(rows);
            }
        });
        this.CerrarConexion();
    };
    
    
    
    
};

exports.ConexionDB = ConexionDB;