package com.desafioPrimefaces.dao;

import com.desafioPrimefaces.entidade.Ocorrencia;
import com.desafioPrimefaces.util.FabricaConexao;
import com.desafioPrimefaces.util.exception.ErroSistema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDAO implements CrudDAO<Ocorrencia>{
    @Override
    public void salvar(Ocorrencia entidade) throws ErroSistema {
        try (Connection connection = FabricaConexao.getConnection()) {
            PreparedStatement preparedStatement = null;
            if (entidade.getId() == null){
                connection.prepareCall("INSERT INTO ocorrencias (nome_vitima,descricao) " +
                        "VALUES (?,?)");
            }else {
                preparedStatement = connection.prepareStatement("update ocorrencias set nome_vitima=?, descricao=? where id=?");
                preparedStatement.setInt(3,entidade.getId());
            }
            preparedStatement.setString(1, entidade.getNomeVitima());
            preparedStatement.setString(2, entidade.getDescricao());

            preparedStatement.execute();
            FabricaConexao.fecharConexao();

        }catch (SQLException e){
            throw new ErroSistema("Erro ao tentar salvar", e);
        }
    }

    @Override
    public void deletar(Ocorrencia entidade) throws ErroSistema {
        try {Connection connection = FabricaConexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from ocorrencia where id = ?");
            preparedStatement.setInt(1, entidade.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ErroSistema("Erro ao deletar o ocorrencia", e);
        }
    }

    @Override
    public List<Ocorrencia> buscar() throws ErroSistema {
        try{ Connection connection = FabricaConexao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ocorrencias");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ocorrencia> ocorrencias = new ArrayList<>();
            while(resultSet.next()){
                Ocorrencia ocorrencia = new Ocorrencia();
                ocorrencia.setId(resultSet.getInt("id"));
                ocorrencia.setNomeVitima(resultSet.getString("nome_vitima"));
                ocorrencia.setDescricao(resultSet.getString("descricao"));
                ocorrencias.add(ocorrencia);
            }
            FabricaConexao.fecharConexao();
            return ocorrencias;

        }catch (SQLException e){
            throw new ErroSistema("Erro ao buscar as ocorrencias", e);
        }

    }
}
