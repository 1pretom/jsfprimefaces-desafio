package com.desafioPrimefaces.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static Connection connection;
    private static final String URL_CONNECTION ="jdbc:mysql://localhost/sistema-carros";
    private static final String USUARIO ="sa";
    private static final String SENHA = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null){
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL_CONNECTION,USUARIO,SENHA);
        }
     return connection   ;
    }
    public static void fecharConexao() throws SQLException {
        if (connection != null){
            connection.close();
            connection = null;
        }
    }

}
