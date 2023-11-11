/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.ProdutoDAO;
import java.math.BigDecimal;
import model.Produto;
import model.NCM;
import model.Unidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;
    private final List<Produto> produtos = new ArrayList<>();

    public ProdutoController() throws SQLException {
        produtoDAO = new ProdutoDAO();
    }

    public boolean salvarProduto(int id, String descricao, Unidade unidade, BigDecimal valor, NCM ncm, String observacao, boolean ativo) {
        Produto produto = new Produto(id, descricao, unidade, valor, ncm, observacao, ativo);
        if (produto.getDescricao().trim().isEmpty()) {
            return false;
        }
        if (id == 0) {
            return produtoDAO.adicionarProduto(produto);
        } else {
            return produtoDAO.atualizarProduto(produto);
        }
    }

    public void excluirProduto(int id) {
        produtoDAO.excluirProduto(id);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public Produto obterProdutoPorId(int id) {
        return produtoDAO.obterProdutoPorId(id);
    }

    public boolean descricaoVazia(String descricao) {
        return descricao.trim().isEmpty();
    }

    public boolean descricaoObservacaoVazios(String descricao, String observacao) {
        return descricao.trim().isEmpty() || observacao.trim().isEmpty();
    }

    public boolean isNumeroValido(String input) {
        return input.matches("-?\\d+(,\\d+)?(\\.\\d+)?");
    }

    public void popularProdutos() {
        produtos.clear();
        produtoDAO.listarProdutos().forEach((produto) -> {
            produtos.add(produto);
        });
    }

    public List<Produto> filtrarProdutos(String termoPesquisa) {
        List<Produto> produtosFiltrados = new ArrayList<>();
        produtos.stream().filter((produto) -> (produto.getDescricao().toUpperCase().contains(termoPesquisa.toUpperCase())
                || produto.getObservacao().toUpperCase().contains(termoPesquisa.toUpperCase())))
                .forEachOrdered((produto) -> {
            produtosFiltrados.add(produto);
        });
        return produtosFiltrados;
    }
    
    public List<Produto> filtrarProdutosAtivos(String termoPesquisa) {
        List<Produto> produtosFiltrados = new ArrayList<>();
        produtos.stream().filter((produto) -> (produto.getDescricao().toUpperCase().contains(termoPesquisa.toUpperCase())
                || produto.getObservacao().toUpperCase().contains(termoPesquisa.toUpperCase())))
                 .filter(Produto::isAtivo) // Adiciona o filtro para produtos ativos
                .forEachOrdered((produto) -> {
            produtosFiltrados.add(produto);
        });
        return produtosFiltrados;
    }

    public void ativaProduto(int id, boolean ativo) {
        produtoDAO.ativarProduto(id, ativo);
    }

    public void marcarProdutoComoDeletado(int id) {
        produtoDAO.marcarProdutoComoDeletado(id); // Marca o PRODUTO como deletado
    }

}
