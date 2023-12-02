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
import controller.NCMController;
import controller.UnidadeController;
import java.math.BigDecimal;
import model.NCM;
import model.Unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Log;
import model.Produto;
import model.UsuarioLogado;
import service.LogService;

public class ProdutoDAO {

    private final Connection conexao;
    UnidadeController unidadeController = new UnidadeController();
    NCMController ncmController = new NCMController();

    public ProdutoDAO() throws SQLException {
        conexao = ConexaoPostgres.obterConexao();
    }

    public boolean adicionarProduto(Produto produto) {
        try {
            String sql = "INSERT INTO produto (descricao, unidade_id, valor, ncm_id, observacao, ativo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setInt(2, produto.getUnidade().getId());
            preparedStatement.setBigDecimal(3, produto.getValor());
            preparedStatement.setInt(4, produto.getNcm().getId());
            preparedStatement.setString(5, produto.getObservacao());
            preparedStatement.setBoolean(6, produto.isAtivo());
            preparedStatement.executeUpdate();

            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Produto",
                    Log.EventoLog.CRIAR,
                    false));
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
            return false;
        }
    }

    public boolean atualizarProduto(Produto produto) {
        try {
            String sql = "UPDATE produto SET descricao = ?, unidade_id = ?, valor = ?, ncm_id = ?, observacao = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setInt(2, produto.getUnidade().getId());
            preparedStatement.setBigDecimal(3, produto.getValor());
            preparedStatement.setInt(4, produto.getNcm().getId());
            preparedStatement.setString(5, produto.getObservacao());
            preparedStatement.setBoolean(6, produto.isAtivo());
            preparedStatement.setInt(7, produto.getId());
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Produto",
                    Log.EventoLog.ALTERAR,
                    false));
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
            return false;
        }
    }

    public void excluirProduto(int id) {
        try {
            String sql = "UPDATE produto SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT id, descricao, unidade_id, valor, ncm_id, observacao, ativo FROM produto WHERE deletado = false ORDER BY id DESC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                BigDecimal valor = resultSet.getBigDecimal("valor");

                Unidade unidade = unidadeController.obterUnidadePorId(resultSet.getInt("unidade_id"));

                NCM ncm = ncmController.obterNCMPorId(resultSet.getInt("ncm_id"));

                String observacao = resultSet.getString("observacao");
                boolean ativo = resultSet.getBoolean("ativo");
                Produto produto = new Produto(id, descricao, unidade, valor, ncm, observacao, ativo);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
        }
        return produtos;
    }

    public Produto obterProdutoPorId(int id) {
        Produto produto = null;
        try {
            String sql = "SELECT descricao, unidade_id, valor, ncm_id, observacao, ativo FROM produto WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String descricao = resultSet.getString("descricao");
                BigDecimal valor = resultSet.getBigDecimal("valor");

                Unidade unidade = unidadeController.obterUnidadePorId(resultSet.getInt("unidade_id"));

                NCM ncm = ncmController.obterNCMPorId(resultSet.getInt("ncm_id"));

                String observacao = resultSet.getString("observacao");
                boolean ativo = resultSet.getBoolean("ativo");
                produto = new Produto(id, descricao, unidade, valor, ncm, observacao, ativo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
        }
        return produto;
    }

    public void ativarProduto(int id, boolean ativo) {
        try {
            String sql = "UPDATE produto SET ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, ativo);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Produto",
                    Log.EventoLog.ATIVAR_DESATIVAR,
                    false));
        } catch (SQLException e) {
            System.out.println("Erro ::: " + e);
        }
    }

    public void marcarProdutoComoDeletado(int id) {
        try {
            String sql = "UPDATE produto SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Produto",
                    Log.EventoLog.DELETAR,
                    true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
