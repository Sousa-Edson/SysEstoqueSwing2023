/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import enums.TipoNota;
import java.math.BigDecimal;

public class Item {

    private int id;
    private Produto produto;
    private String complemento;
    private BigDecimal quantidade;
    private TipoNota tipo;

    private Transacao transacao;

    public Item(int id, Produto produto, String complemento, BigDecimal quantidade, TipoNota tipo) {
        this.id = id;
        this.produto = produto;
        this.complemento = complemento;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public Item(Produto produto, String complemento, BigDecimal quantidade, TipoNota tipo) {
        this.produto = produto;
        this.complemento = complemento;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public Item(int id, Produto produto, String complemento, BigDecimal quantidade, TipoNota tipo, Transacao transacao) {
        this.id = id;
        this.produto = produto;
        this.complemento = complemento;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.transacao = transacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", produto=" + produto + ",produto-id=" + produto.getId() + ", complemento=" + complemento + ", quantidade=" + quantidade + ", tipo=" + tipo + '}';
    }

}
