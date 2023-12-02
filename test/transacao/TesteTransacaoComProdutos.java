/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transacao;

/**
 *
 * @author edson
 */
 
import controller.TransacaoController;
import controller.ProdutoController;
import enums.TipoNota;
import java.math.BigDecimal;
import model.Transacao;
import model.Produto;
 

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.CFOP;
import model.Cliente;
import model.NCM;
import model.Unidade;

public class TesteTransacaoComProdutos {
    public static void main(String[] args) {
        try {
            TransacaoController transacaoController = new TransacaoController();
            ProdutoController produtoController = new ProdutoController();
 
//            System.out.println("produto::"+produtoController.listarProdutos());

            // Criação de uma lista de produtos
            List<Produto> produtos = new ArrayList<>();
            produtos.add(produtoController.obterProdutoPorId(18));
            produtos.add(produtoController.obterProdutoPorId(15));

            // Criação de uma transação com produtos
            Transacao transacao = new Transacao();
            transacao.setTipo(TipoNota.ENTRADA);
            transacao.setCfop(new CFOP(2));
            transacao.setCliente(new Cliente(1));
            transacao.setNota("Nota1");
            transacao.setChave("Chave1");
//            Timestamp dataHora = Timestamp.valueOf("2023-10-11 08:00:00");
//            transacao.setDataHora(LocalDateTime.now());
            transacao.setInformacoesComplementares("Informações1");
//            transacao.setProdutos(produtos);

            // Salva a transação no banco de dados
            transacaoController.salvarTransacao(transacao);

            // Lista todas as transações
            List<Transacao> transacoes = transacaoController.listarTransacoes();
            for (Transacao t : transacoes) {
                System.out.println("ID: " + t.getId());
                System.out.println("Tipo: " + t.getTipo());
                System.out.println("CFOP: " + t.getCfop().getId());
                System.out.println("Cliente: " + t.getCliente().getId());
                System.out.println("Nota: " + t.getNota());
                System.out.println("Chave: " + t.getChave());
//                System.out.println("Data e Hora: " + t.getDataHora());
                System.out.println("Informações Complementares: " + t.getInformacoesComplementares());

                // Lista os produtos da transação
//                for (Produto p : t.getProdutos()) {
//                    System.out.println("Produto: " + p.getDescricao());
//                    System.out.println("Valor: " + p.getValor());
//                    System.out.println("Descrição: " + p.getDescricao());
//                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
