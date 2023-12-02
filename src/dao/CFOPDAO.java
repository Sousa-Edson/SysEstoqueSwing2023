/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author edson
 */
import conexao.ConexaoPostgres; // Importe a classe de conexão apropriada
import model.CFOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Log;
import model.UsuarioLogado;
import service.LogService;

public class CFOPDAO {

    private Connection conexao;

    public CFOPDAO() throws SQLException {
        conexao = ConexaoPostgres.obterConexao(); // Inicialize a conexão apropriada
    }

    public boolean adicionarCFOP(CFOP cfop) {
        try {
            String sql = "INSERT INTO cfop (codigo, descricao, ativo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cfop.getCodigo());
            preparedStatement.setString(2, cfop.getDescricao());
            preparedStatement.setBoolean(3, cfop.isAtivo());
            preparedStatement.executeUpdate();

            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "CFOP",
                    Log.EventoLog.CRIAR,
                    false));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarCFOP(CFOP cfop) {
        try {
            String sql = "UPDATE cfop SET codigo = ?, descricao = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cfop.getCodigo());
            preparedStatement.setString(2, cfop.getDescricao());
            preparedStatement.setBoolean(3, cfop.isAtivo());
            preparedStatement.setInt(4, cfop.getId());
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "CFOP",
                    Log.EventoLog.ALTERAR,
                    false));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void excluirCFOP(int id) {
        try {
            String sql = "DELETE FROM cfop WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CFOP> listarCFOPs() {
        List<CFOP> cfops = new ArrayList<>();
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM cfop  where deletado = false ORDER BY id ASC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                CFOP cfop = new CFOP(id, codigo, descricao, ativo);
                cfops.add(cfop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cfops;
    }

    public CFOP obterCFOPPorId(int id) {
        CFOP cfop = null;
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM cfop WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                cfop = new CFOP(id, codigo, descricao, ativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cfop;
    }

    public void marcarCFOPComoInativo(int id) {
        try {
            String sql = "UPDATE cfop SET ativo = false WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "CFOP",
                    Log.EventoLog.ATIVAR_DESATIVAR,
                    false));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CFOP> listarCFOPsAtivos() {
        List<CFOP> cfops = new ArrayList<>();
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM cfop WHERE ativo = true ORDER BY id ASC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                CFOP cfop = new CFOP(id, codigo, descricao, ativo);
                cfops.add(cfop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cfops;
    }

    public void marcarCFOPComoDeletado(int id) {
        try {
            String sql = "UPDATE cfop SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "CFOP",
                    Log.EventoLog.DELETAR,
                    true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
