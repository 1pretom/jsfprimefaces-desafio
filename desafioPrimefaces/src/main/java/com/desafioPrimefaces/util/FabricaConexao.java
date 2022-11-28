package com.desafioPrimefaces.util;

import com.desafioPrimefaces.util.exception.ErroSistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FabricaConexao {
//    private String jdbcDriver, dbURL, usuario, senha;
//
//    public FabricaConexao(String jdbcDriver, String dbURL, String usuario, String senha) {
//        this.jdbcDriver = jdbcDriver;
//        this.dbURL = dbURL;
//        this.usuario = usuario;
//        this.senha = senha;
//    }
//
//    public Connection getConnection() throws SQLException, ClassNotFoundException {
//        Class.forName(jdbcDriver);
//        return DriverManager.getConnection(dbURL,usuario,senha);
//    }


        private static Connection connection;
    private static final String URL_CONNECTION ="jdbc:h2:~/gerenciar-ocorrencias;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String USUARIO ="sa";
    private static final String SENHA = null;

    public static Connection getConnection() throws ErroSistema {
        if (connection == null){
            try {
                Class.forName("com.h2.Driver");
                connection = DriverManager.getConnection(URL_CONNECTION,USUARIO,SENHA);
            } catch (SQLException e) {
               throw new ErroSistema("Não foi possível conectar ao banco de dados", e);
            } catch (ClassNotFoundException e) {
                throw new ErroSistema("O driver do banco de dados não foi encontrado", e);
            }

        }
     return connection   ;
    }
    public static void fecharConexao() throws ErroSistema{
        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new ErroSistema("Erro ao fechar conexão com o banco de dados",e);
            }

        }
    }

}
