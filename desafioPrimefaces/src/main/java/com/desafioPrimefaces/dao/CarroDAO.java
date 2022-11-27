package com.desafioPrimefaces.dao;

import com.desafioPrimefaces.entity.Carro;
import com.desafioPrimefaces.util.FabricaConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroDAO {
    public void salvar(Carro carro) throws ClassNotFoundException{
        try (Connection connection = FabricaConexao.getConnection()) {
            PreparedStatement preparedStatement = null;
            if (carro.getId() == null){
                 connection.prepareCall("INSERT INTO 'sistema-carros'.'carro' ('modelo',fabricante','cor','ano') " +
                        "VALUES (?,?,?,?)");
            }else {
                preparedStatement = connection.prepareStatement("update carro set model=?, fabricante=?, cor=?, ano=? where id=?");
                preparedStatement.setInt(5,carro.getId());
            }
            preparedStatement.setString(1, carro.getModelo());
            preparedStatement.setString(2, carro.getFabricante());
            preparedStatement.setString(3, carro.getCor());
            preparedStatement.setDate(4, new Date(carro.getAno().getTime()));
            preparedStatement.execute();
            FabricaConexao.fecharConexao();

        }catch (SQLException e){
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    public List<Carro> buscar() throws SQLException, ClassNotFoundException {
        try{ Connection connection = FabricaConexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from carro");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Carro> carros = new ArrayList<>();
            while(resultSet.next()){
                Carro carro = new Carro();
                carro.setId(resultSet.getInt("id"));
                carro.setModelo(resultSet.getString("modelo"));
                carro.setFabricante(resultSet.getString("fabricante"));
                carro.setCor(resultSet.getString("cor"));
                carro.setAno(resultSet.getDate("ano"));
                carros.add(carro);
            }

        }catch (SQLException e){
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, e);

        }return null;

    }


}
