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
import model.Unidade;

import java.util.List;

public class UnidadeController {

    private final UnidadeDAO unidadeDAO;

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

    public List<Unidade> listarUnidades() {
        return unidadeDAO.listarUnidades();
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
