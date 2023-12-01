/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author edson
 */
import conexao.ConexaoPostgres;
import model.Unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Log;
import model.UsuarioLogado;
import service.LogService;

public class UnidadeDAO {

    private Connection conexao;

    public UnidadeDAO() throws SQLException {
        conexao = ConexaoPostgres.obterConexao();
    }

    public boolean adicionarUnidade(Unidade unidade) {
        try {
            String sql = "INSERT INTO unidade (sigla, descricao, ativo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, unidade.getSigla());
            preparedStatement.setString(2, unidade.getDescricao());
            preparedStatement.setBoolean(3, unidade.isAtivo());
            preparedStatement.executeUpdate();
             //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Unidade",
                        Log.EventoLog.CRIAR,
                        false));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }

    }

    public boolean atualizarUnidade(Unidade unidade) {
        try {
            String sql = "UPDATE unidade SET sigla = ?, descricao = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, unidade.getSigla());
            preparedStatement.setString(2, unidade.getDescricao());
            preparedStatement.setBoolean(3, unidade.isAtivo());
            preparedStatement.setInt(4, unidade.getId());
            preparedStatement.executeUpdate();
            //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Unidade",
                        Log.EventoLog.ALTERAR,
                        false));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void excluirUnidade(int id) {
        try {
            String sql = "DELETE FROM unidade WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Unidade> listarUnidades() {
        List<Unidade> unidades = new ArrayList<>();
        try {
            String sql = "SELECT id, sigla, descricao, ativo FROM unidade  WHERE deletado=false ORDER BY id asc ";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String sigla = resultSet.getString("sigla");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                Unidade unidade = new Unidade(id, sigla, descricao, ativo);
                unidades.add(unidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unidades;
    }

    public Unidade obterUnidadePorId(int id) {
        Unidade unidade = null;
        try {
            String sql = "SELECT id, sigla, descricao, ativo FROM unidade WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String sigla = resultSet.getString("sigla");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                unidade = new Unidade(id, sigla, descricao, ativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unidade;
    }

    public void marcarUnidadeComoDeletada(int id) {
        try {
            String sql = "UPDATE unidade SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Unidade",
                        Log.EventoLog.DELETAR,
                        true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Unidade> listarUnidadesAtivas() {
//        List<Unidade> unidades = new ArrayList<>();
//        try {
//            String sql = "SELECT id, sigla, descricao, ativo FROM unidade WHERE deletado = false AND ativo = true ORDER BY id ASC";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String sigla = resultSet.getString("sigla");
//                String descricao = resultSet.getString("descricao");
//                boolean ativo = resultSet.getBoolean("ativo");
//                Unidade unidade = new Unidade(id, sigla, descricao, ativo);
//                unidades.add(unidade);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return unidades;
//    }

}
