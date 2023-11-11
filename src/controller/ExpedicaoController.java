/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TransacaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Expedicao;
import model.Transacao;
import util.DataConverter;

/**
 *
 * @author edson
 */
public class ExpedicaoController {

    private final TransacaoDAO transacaoDAO;

    public ExpedicaoController() throws SQLException {
        transacaoDAO = new TransacaoDAO();
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

    public List<Transacao> listarTransacoesComFiltro(String busca, int status) {
        ClienteController clienteController = new ClienteController();
        CFOPController cfopController = new CFOPController();

        List<Transacao> transacoes = new ArrayList<>();
        for (Transacao transacao : transacaoDAO.listarTransacoes()) {
            if (transacao.getNota().contains(busca) || DataConverter.dataParaString("" + transacao.getData()).contains(busca)) {
                transacao.setCliente(clienteController.obterClientePorId(transacao.getCliente().getId()));
                transacao.setCfop(cfopController.obterCFOPPorId(transacao.getCfop().getId()));
                if (transacao.getTipo().getValor() == 1) {
                    if (transacao.getStatus().getCodigo() == status) {
                        transacoes.add(transacao);
                    }
                    if (status == 99) {
                       if (transacao.getStatus().getCodigo() != 4) {
                        transacoes.add(transacao);
                    }
                    }
                }
            }
        }
        return transacoes;
    }

    public Expedicao quantidadeDeTransacao() {
        Expedicao t = new Expedicao();
        for (Transacao transacao : transacaoDAO.listarTransacoes()) {
            if (transacao.getTipo().getValor() == 1) {
                switch (transacao.getStatus().getCodigo()) {
                    case 0:
                        t.setCriado(t.getCriado() + 1);
                        break;
                    case 1:
                        t.setPendente(t.getPendente() + 1);
                        break;
                    case 2:
                        t.setPreparacao(t.getPreparacao() + 1);
                        break;
                    case 3:
                        t.setAguardando(t.getAguardando() + 1);
                        break;
                    case 4:
                        t.setEnviado(t.getEnviado() + 1);
                        break;
                    case 5:
                        t.setPronto(t.getPronto() + 1);
                        break;
                    default:
                        break;
                }

            }
        }
        return t;

    }
}
