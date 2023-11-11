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
import model.NCM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NCMDAO {

    private Connection conexao;

    public NCMDAO() throws SQLException {
        conexao = ConexaoPostgres.obterConexao();
    }

    public boolean adicionarNCM(NCM ncm) {
        try {
            String sql = "INSERT INTO ncm (codigo, descricao, ativo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, ncm.getCodigo());
            preparedStatement.setString(2, ncm.getDescricao());
            preparedStatement.setBoolean(3, ncm.isAtivo());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarNCM(NCM ncm) {
        try {
            String sql = "UPDATE ncm SET codigo = ?, descricao = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, ncm.getCodigo());
            preparedStatement.setString(2, ncm.getDescricao());
            preparedStatement.setBoolean(3, ncm.isAtivo());
            preparedStatement.setInt(4, ncm.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void excluirNCM(int id) {
        try {
            String sql = "DELETE FROM ncm WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NCM> listarNCMs() {
        List<NCM> ncms = new ArrayList<>();
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM ncm WHERE deletado = false ORDER BY id ASC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                NCM ncm = new NCM(id, codigo, descricao, ativo);
                ncms.add(ncm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ncms;
    }

    public NCM obterNCMPorId(int id) {
        NCM ncm = null;
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM ncm WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                ncm = new NCM(id, codigo, descricao, ativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ncm;
    }

    public void marcarNCMComoDeletado(int id) {
        try {
            String sql = "UPDATE ncm SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NCM> listarNCMsAtivos() {
        List<NCM> ncms = new ArrayList<>();
        try {
            String sql = "SELECT id, codigo, descricao, ativo FROM ncm WHERE deletado = false AND ativo = true ORDER BY id ASC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigo = resultSet.getString("codigo");
                String descricao = resultSet.getString("descricao");
                boolean ativo = resultSet.getBoolean("ativo");
                NCM ncm = new NCM(id, codigo, descricao, ativo);
                ncms.add(ncm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ncms;
    }
}
