/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.TransacaoDAO;
import enums.TipoNota;
import java.math.BigDecimal;
import model.Transacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import repository.BancoVirtual;
import util.DataConverter;
import util.Moeda;

public class TransacaoController {

    private final TransacaoDAO transacaoDAO;
    private List<Item> itens = new ArrayList<>();

    public TransacaoController() throws SQLException {
        transacaoDAO = new TransacaoDAO();
    }

    public boolean salvarTransacao(Transacao transacao) {
        return transacaoDAO.adicionarTransacao(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoDAO.listarTransacoes();
    }

    public Transacao obterTransacaoPorId(int id) {
        Transacao t = transacaoDAO.obterTransacaoPorId(id);
        ClienteController clienteController = new ClienteController();
        t.setCliente(clienteController.obterClientePorId(t.getCliente().getId()));
        return t;
    }

    public boolean atualizarTransacao(Transacao transacao) {
        return transacaoDAO.atualizarTransacao(transacao);
    }

    public void excluirTransacao(int id) {
//        transacaoDAO.excluirTransacao(id);
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        System.out.println("adicionado:::" + item.toString());
    }

    public void editaItem(Item item) {
        System.out.println("editaItem::" + item);
        for (int i = 0; i < itens.size(); i++) {
            if (i == item.getId() - 1) {
                itens.get(i).setQuantidade(item.getQuantidade());
                itens.get(i).setComplemento(item.getComplemento());
                break;
            }
        }
    }

    public List<Item> listarItens() {
        return itens;
    }

    public void limparItens() {
        itens.clear();
    }

    public String itensValorTotal() {
        BigDecimal multiplicacao = new BigDecimal(0);
        int contador = 0;
        for (Item item : itens) {
            contador++;
            multiplicacao = multiplicacao.add(item.getQuantidade().multiply(item.getProduto().getValor()));
        }
        return "Qtd de itens: " + contador + " | Valor total: " + Moeda.formatadorDeMoeda("" + multiplicacao);
    }

    public void atualizarTipoParaTodosItens(TipoNota tipoNota) {
        for (Item item : itens) {
            item.setTipo(tipoNota);
        }
    }

    public void excluirUmItens(int idItem) {
        itens.remove(idItem - 1);
    }

    public void listarItensAtivosDaTransacao(int idTransacao) {
        ProdutoController produtoController = null;
        try {
            produtoController = new ProdutoController();
        } catch (SQLException ex) {
            Logger.getLogger(TransacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Item item : transacaoDAO.listarItensAtivosDaTransacao(idTransacao)) {
            item.setProduto(produtoController.obterProdutoPorId(item.getProduto().getId()));
            itens.add(item);
        }
    }

    public void carregaTransacoesSeVazio() {
        BancoVirtual.transacoes.clear();
        BancoVirtual.transacoes.addAll(transacaoDAO.listarTransacoes());
        System.out.println("### carregaTransacoesSeVazio \ntamanho: " + BancoVirtual.transacoes.size() + " registros");
    }

    public List<Transacao> listarTransacoesComFiltro(String busca, int tipoBusca) {
        List<Transacao> transacoes = new ArrayList<>();
        for (Transacao transacao : BancoVirtual.transacoes) {
            if (transacao.getNota().contains(busca) || transacao.getCliente().getNomeFantasia().contains(busca.toUpperCase()) || DataConverter.dataParaString("" + transacao.getData()).contains(busca)) {
                switch (tipoBusca) {
                    case 1:
                        if (transacao.getTipo().getValor() == 0) {
                            transacoes.add(transacao);
                        }
                        break;
                    case 2:
                        if (transacao.getTipo().getValor() == 1) {
                            transacoes.add(transacao);
                        }
                        break;
                    default:
                        transacoes.add(transacao);
                        break;
                }
            }
        }
        return transacoes;
    }

    public void marcarTransacaoComoDeletado(int id) {
        transacaoDAO.marcarTransacaoComoDeletada(id);
    }

    public boolean salvarExpedicao(Transacao transacao) {
        return transacaoDAO.atualizaTransacaoExpedicao(transacao);
    }

    public List<Item> listarTodosItensAtivos(String busca, int tipoBusca) {
        List<Item> itensRelatorio = new ArrayList<>();
        for (Item item : transacaoDAO.listarTodosItensAtivos()) {
            if (item.getTransacao().getNota().contains(busca)
                    || DataConverter.dataParaString("" + item.getTransacao().getData()).contains(busca)
                    || item.getProduto().getDescricao().contains(busca.toUpperCase())
                    || item.getTransacao().getCliente().getNomeFantasia().contains(busca.toUpperCase())) {
                switch (tipoBusca) {
                    case 1:
                        if (item.getTransacao().getTipo().getValor() == 0) {
                            itensRelatorio.add(item);
                        }
                        break;
                    case 2:
                        if (item.getTransacao().getTipo().getValor() == 1) {
                            itensRelatorio.add(item);
                        }
                        break;
                    default:
                        itensRelatorio.add(item);
                        break;
                }
            }
        }

        return itensRelatorio;
    }
}
