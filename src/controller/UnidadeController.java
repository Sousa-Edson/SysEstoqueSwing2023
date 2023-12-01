/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.UnidadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Unidade;

import java.util.List;
import repository.BancoVirtual;

public class UnidadeController {

    private final UnidadeDAO unidadeDAO;
//    private List<Unidade> unidades = new ArrayList<>();

    public UnidadeController() throws SQLException {
        unidadeDAO = new UnidadeDAO();
    }

    public boolean salvararUnidade(int id, String sigla, String descricao, boolean ativo) {
        Unidade unidade = new Unidade(id, sigla, descricao, ativo);
        if (unidade.getDescricao().trim().isEmpty() == true) {
            return false;
        }
        if (id == 0) {
            return unidadeDAO.adicionarUnidade(unidade);
        } else {
            return unidadeDAO.atualizarUnidade(unidade);
        }
    }

    public void excluirUnidade(int id) {
        unidadeDAO.excluirUnidade(id);
    }

    public void carregarUnidadeSeVazia() {
//        if (BancoVirtual.unidades.isEmpty()) {
//            System.out.println("unidade vazia");
        BancoVirtual.unidades.clear();
        BancoVirtual.unidades.addAll(unidadeDAO.listarUnidades());

//        }
    }

    public List<Unidade> listarUnidades() {
        return BancoVirtual.unidades;
    }

    public Unidade obterUnidadePorId(int id) {
        for (Unidade unidade : BancoVirtual.unidades) {
        if (unidade.getId() == id) {
            return unidade; // Retorna a unidade se encontrar o ID correspondente
        }
    } 
    return null;
//        return unidadeDAO.obterUnidadePorId(id);
    }

    public void marcarUnidadeComoDeletada(int id) {
        unidadeDAO.marcarUnidadeComoDeletada(id);
    }

    public boolean siglaDescricaoVazias(String sigla, String descricao) {
        return sigla.trim().isEmpty() || descricao.trim().isEmpty();
    }

    public List<Unidade> listarUnidadesAtivas() {
        List<Unidade> unidades = new ArrayList<>();
        for (Unidade unidade : BancoVirtual.unidades) {
            if (unidade.isAtivo()) {
                unidades.add(unidade);
            }
        }
        return unidades;
    }
}
