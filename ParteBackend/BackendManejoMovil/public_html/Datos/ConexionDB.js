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
    //this.pass = "786Xenoblade786";
    this.pass = "Fantasia7";
    this.db = "mydb";

    var pool;

    this.conectar = function () {
        console.log("CREANDO LA CONEXION CON LA BASE DE DATOS");
        pool = mysql.createPool({
            connectionLimit: 1000,
            host: this.host,
            user: this.user,
            password: this.pass,
            database: this.db
        });
        //connection.connect();
        //this.connection.escape();
    };

    this.getConexion = function () {
        return this.connection;
    };

    this.getDatos = function (pQuery, callBack) {
        console.log("Realizando un request" + pQuery);
        pool.getConnection(function (err, connection) {
            if (err) {
                console.log(err);
            }
            else {
                connection.query(pQuery, function (err, rows) {
                    if (err) {
                        console.log(err);
                    } else {
                        callBack(rows);
                        connection.release();
                    }
                });
            }
        });
    };

    this.getDatosSinInyection = function (pQuery, pDato, callBack) {
        console.log("Realizando un request" + pQuery);
        pool.getConnection(function (err, connection) {
            connection.query(pQuery, pDato, function (err, rows) {
                if (err) {
                    console.log(err);
                } else {
                    callBack(rows);
                    connection.release();
                }
            });
        });
    };

    this.saveDato = function (pQuery, pObjeto, callBack) {
        pool.getConnection(function (err, connection) {
            connection.query(pQuery, pObjeto, function (err, rows) {
                if (err) {
                    console.log(err);
                } else {
                    callBack(rows);
                    connection.release();
                }
            });
        });
    };
};

exports.ConexionDB = ConexionDB;