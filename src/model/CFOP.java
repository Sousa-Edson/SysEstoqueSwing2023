/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
public class CFOP {

    private int id;
    private String codigo;
    private String descricao;
    private boolean ativo;
    private boolean deletado;

    public CFOP() {
        // Construtor padrão
    }

    public CFOP(int id) {
        this.id = id;
    }

    public CFOP(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
    

    public CFOP(int id, String codigo, String descricao, boolean ativo) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public CFOP(int id, String codigo, String descricao, boolean ativo, boolean deletado) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
        this.deletado = deletado;
    }

    // Métodos getters e setters para os atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    @Override
    public String toString() {
        return codigo ;
    }

}
