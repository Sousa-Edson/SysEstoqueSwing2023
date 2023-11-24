/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.TipoCliente;
import enums.TipoEmpresa;

/**
 *
 * @author edson
 */
public class Cliente {

    private int id;
    private TipoCliente tipoCliente;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;

    private String endereco;
    private String cep;
    private String complemento;
    private String bairro;
    private String cidade;

    private String contato;
    private String responsavelLegal;
    private TipoEmpresa tipoEmpresa;

    private boolean deletado;
    private boolean ativo;

    // Construtores, getters e setters aqui...
    public Cliente() {
    }

    public Cliente(int id, TipoCliente tipoCliente, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual,
            String inscricaoMunicipal, String endereco, String contato, String responsavelLegal, TipoEmpresa tipoEmpresa, String cep,
            String complemento, String bairro, String cidade) {
        this.id = id;
        this.tipoCliente = tipoCliente;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.endereco = endereco;
        this.contato = contato;
        this.responsavelLegal = responsavelLegal;
        this.tipoEmpresa = tipoEmpresa;

        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Cliente(int id, TipoCliente tipoCliente, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual,
            String inscricaoMunicipal, String endereco, String contato, String responsavelLegal, TipoEmpresa tipoEmpresa, boolean ativo,
            String cep, String complemento, String bairro, String cidade) {
        this.id = id;
        this.tipoCliente = tipoCliente;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.endereco = endereco;
        this.contato = contato;
        this.responsavelLegal = responsavelLegal;
        this.tipoEmpresa = tipoEmpresa;
        this.ativo = ativo;
        
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Cliente(int id, TipoCliente tipoCliente, String cnpj, String razaoSocial, String nomeFantasia, String inscricaoEstadual,
            String inscricaoMunicipal, String endereco, String cep, String complemento, String bairro, String cidade, String contato,
            String responsavelLegal, TipoEmpresa tipoEmpresa, boolean deletado) {
        this.id = id;
        this.tipoCliente = tipoCliente;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.endereco = endereco;

        this.contato = contato;
        this.responsavelLegal = responsavelLegal;
        this.tipoEmpresa = tipoEmpresa;
        this.deletado = deletado;
        this.ativo = ativo;

        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    // Outros métodos, se necessário...
    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(int id, String nomeFantasia) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getResponsavelLegal() {
        return responsavelLegal;
    }

    public void setResponsavelLegal(String responsavelLegal) {
        this.responsavelLegal = responsavelLegal;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
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
        return nomeFantasia;
    }

}
