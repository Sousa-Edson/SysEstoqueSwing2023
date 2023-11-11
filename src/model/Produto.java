/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author edson
 */
public class Produto {

    private int id; // Identificador único do produto
    private String descricao;
    private Unidade unidade; // Associação com a tabela Unidade
    private BigDecimal valor;
    private NCM ncm; // Associação com a tabela NCM
    private String observacao;
    private boolean ativo;
    private boolean deletado;

    // Construtor
    public Produto(int id, String descricao, Unidade unidade, BigDecimal valor, NCM ncm, String observacao, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valor = valor;
        this.ncm = ncm;
        this.observacao = observacao;
        this.ativo = ativo;
    }

    public Produto() {
    }

    public Produto(int id) {
        this.id = id;
    }

    

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public NCM getNcm() {
        return ncm;
    }

    public void setNcm(NCM ncm) {
        this.ncm = ncm;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
