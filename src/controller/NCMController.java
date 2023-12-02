/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.NCMDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NCM;

import java.util.List;
import repository.BancoVirtual;

public class NCMController {

    private final NCMDAO ncmDAO;

    public NCMController() throws SQLException {
        ncmDAO = new NCMDAO();
    }

    public boolean salvarNCM(int id, String codigo, String descricao, boolean ativo) {
        NCM ncm = new NCM(id, codigo, descricao, ativo);
        if (ncm.getDescricao().trim().isEmpty() || ncm.getCodigo().trim().isEmpty()) {
            return false;
        }
        if (id == 0) {
            return ncmDAO.adicionarNCM(ncm);
        } else {
            return ncmDAO.atualizarNCM(ncm);
        }
    }

    public void excluirNCM(int id) {
        ncmDAO.excluirNCM(id);
    }

    public void carregaNcmSeVazio() {
        BancoVirtual.ncms.clear();
        BancoVirtual.ncms.addAll(ncmDAO.listarNCMs());
        System.out.println("### carregaNcmSeVazio \ntamanho: " + BancoVirtual.ncms.size() + " registros");
    }

    public List<NCM> listarNCMs() {
        return BancoVirtual.ncms;
    }

    public NCM obterNCMPorId(int id) {
        for (NCM ncm : BancoVirtual.ncms) {
            if (ncm.getId() == id) {
                return ncm;
            }
        }
        return null;
    }

    public void marcarNCMComoDeletado(int id) {
        ncmDAO.marcarNCMComoDeletado(id);
    }

    public boolean codigoDescricaoVazios(String codigo, String descricao) {
        return codigo.trim().isEmpty() || descricao.trim().isEmpty();
    }

    public List<NCM> listarNCMsAtivos() {
        List<NCM> ncms = new ArrayList<>();
        for (NCM ncm : BancoVirtual.ncms) {
            if (ncm.isAtivo()) {
                ncms.add(ncm);
            }
        }
        return ncms;
    }

    public boolean validarNCM(String ncm) {
        // Remove espaços em branco
        ncm = ncm.replaceAll("\\s", "");

        // Verifica se o NCM possui exatamente 8 dígitos numéricos
        if (ncm.matches("\\d{8}")) {
            return true;
        } else {
            return false;
        }
    }

}
