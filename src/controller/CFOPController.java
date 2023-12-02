/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.CFOPDAO; // Importe a classe DAO correspondente
import java.sql.SQLException;
import java.util.ArrayList;
import model.CFOP;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.BancoVirtual;

public class CFOPController {

    private CFOPDAO cfopDAO;

    
    public CFOPController() {
        try {
            cfopDAO = new CFOPDAO(); // Inicialize o DAO correspondente
        } catch (SQLException ex) {
            Logger.getLogger(CFOPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean salvarCFOP(int id, String codigo, String descricao, boolean ativo) {
        CFOP cfop = new CFOP(id, codigo, descricao, ativo);

        if (cfop.getDescricao().trim().isEmpty() || cfop.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (id == 0) {
            return cfopDAO.adicionarCFOP(cfop);
        } else {
            return cfopDAO.atualizarCFOP(cfop);
        }
    }

    public void excluirCFOP(int id) {
        cfopDAO.excluirCFOP(id);
    }

    public void carregaCfopSeVazio() {
        BancoVirtual.cfops.clear();
        BancoVirtual.cfops.addAll(cfopDAO.listarCFOPs());
    }

    public List<CFOP> listarCFOPs() {
        return BancoVirtual.cfops;
    }

    public CFOP obterCFOPPorId(int id) {
        for (CFOP cfop : BancoVirtual.cfops) {
            if (cfop.getId() == id) {
                return cfop;
            }
        }
        return null;
    }

    public void marcarCFOPComoInativo(int id) {
        cfopDAO.marcarCFOPComoInativo(id);
    }

    public boolean codigoDescricaoVazios(String codigo, String descricao) {
        return codigo.trim().isEmpty() || descricao.trim().isEmpty();
    }
 
    public List<CFOP> listarCFOPsAtivos() {
        List<CFOP> cfops = new ArrayList<>();
        for (CFOP cfop : BancoVirtual.cfops) {
            if (cfop.isAtivo()) {
                cfops.add(cfop);
            }
        }
        return cfops;
    }

    public void marcarCFOPComoDeletado(int id) {
        cfopDAO.marcarCFOPComoDeletado(id); // Marca o CFOP como deletado
    }

    public boolean validarCFOP(String cfop) {
        // Remove espaços em branco
        cfop = cfop.replaceAll("\\s", "");

        // Verifica se o NCM possui exatamente 8 dígitos numéricos
        if (cfop.matches("\\d{4}")) {
            return true;
        } else {
            return false;
        }
    }
}
