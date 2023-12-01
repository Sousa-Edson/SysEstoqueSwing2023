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
    private List<Unidade> unidades = new ArrayList<>();

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
        System.out.println("controller.UnidadeController.carregarUnidadeSeVazia()");
        if (unidades.isEmpty()) {
            System.out.println("unidade vazia");
            BancoVirtual.unidades.addAll(unidadeDAO.listarUnidades());
            unidades.addAll(BancoVirtual.unidades);
            System.out.println("unidade tamanho::" + unidades.size());
        }
    }

    public List<Unidade> listarUnidades() {
        System.out.println("unidade tamanho::" + unidades.size());
        return BancoVirtual.unidades;
    }

    public Unidade obterUnidadePorId(int id) {
        return unidadeDAO.obterUnidadePorId(id);
    }

    public void marcarUnidadeComoDeletada(int id) {
        unidadeDAO.marcarUnidadeComoDeletada(id);
    }

    public boolean siglaDescricaoVazias(String sigla, String descricao) {
        return sigla.trim().isEmpty() || descricao.trim().isEmpty();
    }

    public List<Unidade> listarUnidadesAtivas() {
        return unidadeDAO.listarUnidadesAtivas();
    }
}
