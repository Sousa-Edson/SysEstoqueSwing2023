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
import model.CFOP;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        // Realize validações, se necessário
        // Certifique-se de que o código CFOP seja único

        CFOP cfop = new CFOP(id, codigo, descricao, ativo);

        if (cfop.getDescricao().trim().isEmpty() || cfop.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (id == 0) {
            // Se o ID for zero, trata-se de uma inserção
            return cfopDAO.adicionarCFOP(cfop);
        } else {
            // Caso contrário, é uma atualização
            return cfopDAO.atualizarCFOP(cfop);
        }
    }

    public void excluirCFOP(int id) {
        cfopDAO.excluirCFOP(id);
    }

    public List<CFOP> listarCFOPs() {
        return cfopDAO.listarCFOPs();
    }

    public CFOP obterCFOPPorId(int id) {
        return cfopDAO.obterCFOPPorId(id);
    }

    public void marcarCFOPComoInativo(int id) {
        cfopDAO.marcarCFOPComoInativo(id);
    }

    public boolean codigoDescricaoVazios(String codigo, String descricao) {
        return codigo.trim().isEmpty() || descricao.trim().isEmpty();
    }

    public List<CFOP> listarCFOPsAtivos() {
        return cfopDAO.listarCFOPsAtivos();
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
