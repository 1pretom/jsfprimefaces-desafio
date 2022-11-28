package com.desafioPrimefaces.dao;

import com.desafioPrimefaces.entidade.Carro;
import com.desafioPrimefaces.util.FabricaConexao;
import com.desafioPrimefaces.util.exception.ErroSistema;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO implements CrudDAO<Carro>{
    @Override
    public void salvar(@NotNull Carro carro) throws ErroSistema{
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
            throw new ErroSistema("Erro ao tentar salvar", e);
        }


    }
    @Override
    public void deletar(Carro carro) throws ErroSistema{
        try {Connection connection = FabricaConexao.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from carro where id = ?");
        preparedStatement.setInt(1, carro.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ErroSistema("Erro ao deletar o carro", e);
        }
    }
    @Override
    public List<Carro> buscar() throws ErroSistema {
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
            FabricaConexao.fecharConexao();
            return carros;

        }catch (SQLException e){
            throw new ErroSistema("Erro ao buscar os carros", e);
        }

    }


}
