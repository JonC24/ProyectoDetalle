/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.detalle.pagina.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cjon4
 */
class DBConnection {

    private final static String database = "db_detalles_especiales"; // Sin espacio adicional
    private final static String user = "root";
    private final static int port = 3306;
    private final static String host = "localhost";
    private final static String pass = "";
    private final static String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Se estableció correctamente la conexión");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
    
    
}
