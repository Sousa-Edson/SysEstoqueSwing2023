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
import repository.BancoVirtual;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;
//    private List<Produto> produtos = new ArrayList<>();

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

//    public List<Produto> listarProdutos() {
//        return produtoDAO.listarProdutos();
//    }
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

    public void carregaProdutosSeVazio() {

        BancoVirtual.produtos.clear();
        BancoVirtual.produtos.addAll(produtoDAO.listarProdutos());

        System.out.println("### carregaProdutosSeVazio \ntamanho: " + BancoVirtual.produtos.size() + " registros");

    }

    public List<Produto> filtrarProdutos(String termoPesquisa) { // corrigir maneira que carrega produto
        List<Produto> produtosFiltrados = new ArrayList<>();
        if (!termoPesquisa.trim().isEmpty()) {
            String termoPesquisaUpper = termoPesquisa.toUpperCase();
            for (Produto produto : BancoVirtual.produtos) {
                if (String.valueOf(produto.getId()).equals(termoPesquisa)
                        || produto.getDescricao().toUpperCase().contains(termoPesquisaUpper)
                        || produto.getObservacao().toUpperCase().contains(termoPesquisaUpper)) {
                    produtosFiltrados.add(produto);
                }
            }
        } else {
            produtosFiltrados.addAll(BancoVirtual.produtos);
        }
        return produtosFiltrados;
    }

    public List<Produto> filtrarProdutosAtivos(String termoPesquisa) {
        List<Produto> produtosFiltrados = new ArrayList<>();

        String termoPesquisaUpper = termoPesquisa.toUpperCase();
        for (Produto produto : BancoVirtual.produtos) {
            if (produto.isAtivo()) {
                if (String.valueOf(produto.getId()).equals(termoPesquisa)
                        || produto.getDescricao().toUpperCase().contains(termoPesquisaUpper)
                        || produto.getObservacao().toUpperCase().contains(termoPesquisaUpper)) {
                    produtosFiltrados.add(produto);
                }
            }
        }

        return produtosFiltrados;
    }

    public void ativaProduto(int id, boolean ativo) {
        produtoDAO.ativarProduto(id, ativo);
    }

    public void marcarProdutoComoDeletado(int id) {
        produtoDAO.marcarProdutoComoDeletado(id); // Marca o PRODUTO como deletado
    }

}
