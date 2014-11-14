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

    connection = null;

    this.conectar = function () {
        connection = mysql.createPool({
            connectionLimit: 1000,
            host: this.host,
            user: this.user,
            password: this.pass,
            database: this.db
        });
        //connection.connect();
        //this.connection.escape();
    };

    this.CerrarConexion = function () {
        connection.end();

    };

    this.getConexion = function () {
        return connection;
    };

    this.getDatos = function (pQuery, callBack) {
        console.log("Realizando un request" + pQuery);
        connection.getConnection(function (err, connection) {
            connection.query(pQuery, function (err, rows) {
                connection.release();
                if (err) {
                    throw err;
                } else {
                    callBack(rows);
                }
            });
        });
        this.CerrarConexion();
    };
    
    this.getDatosSinInyection = function (pQuery, pDato, callBack) {
        console.log("Realizando un request" + pQuery);
        connection.getConnection(function (err, connection) {
            connection.query(pQuery, [pDato], function (err, rows) {
                connection.release();
                if (err) {
                    throw err;
                } else {
                    callBack(rows);
                }
            });
        });
        this.CerrarConexion();
    };

    this.saveDato = function (pQuery, pObjeto, callBack) {
        connection.getConnection(function (err, connection) {
            connection.query(pQuery, pObjeto, function (err, rows) {
                connection.release();
                if (err) {
                    throw err;
                } else {
                    callBack(rows);
                }
            });
        });
    };
};

exports.ConexionDB = ConexionDB;