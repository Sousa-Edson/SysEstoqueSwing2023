/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.CFOP;
import model.Cliente;
import model.NCM;
import model.Produto;
import model.Transacao;
import model.Unidade;

/**
 *
 * @author edson
 */
public interface BancoVirtual {

    List<Unidade> unidades = new ArrayList<>();

    List<CFOP> cfops = new ArrayList<>();

    List<NCM> ncms = new ArrayList<>();

    List<Produto> produtos = new ArrayList<>();

    List<Transacao> transacoes = new ArrayList<>();
    
    List<Cliente> clientes = new ArrayList<>();

}
