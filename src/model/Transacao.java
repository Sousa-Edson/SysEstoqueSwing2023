/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import enums.StatusNota;
import enums.TipoNota;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Transacao {

    private int id;
    private TipoNota tipo;
    private CFOP cfop;
    private Cliente cliente;
    private String nota;
    private String chave;

    private Date data;
    private Time hora;

    private String informacoesComplementares;
    private String motorista;

    private List<Item> itens;
    private boolean deletado;

    private StatusNota status;

//    private Expedicao expedicao;
    public Transacao(int id, TipoNota tipo, CFOP cfop, Cliente cliente, String nota,
            String chave, Date data, Time hora, String informacoesComplementares, List<Item> itens) {
        this.id = id;
        this.tipo = tipo;
        this.cfop = cfop;
        this.cliente = cliente;
        this.nota = nota;
        this.chave = chave;
        this.data = data;
        this.hora = hora;
        this.informacoesComplementares = informacoesComplementares;
        this.itens = itens;
    }

    public Transacao(int id, TipoNota tipo, CFOP cfop, Cliente cliente, String nota,
            String chave, Date data, Time hora, String informacoesComplementares, String motorista, List<Item> itens) {
        this.id = id;
        this.tipo = tipo;
        this.cfop = cfop;
        this.cliente = cliente;
        this.nota = nota;
        this.chave = chave;
        this.data = data;
        this.hora = hora;
        this.informacoesComplementares = informacoesComplementares;
        this.motorista = motorista;
        this.itens = itens;
    }

    public Transacao(int id, TipoNota tipo, CFOP cfop, Cliente cliente, String nota,
            String chave, Date data, Time hora, String informacoesComplementares, String motorista,
            StatusNota status, List<Item> itens) {
        this.id = id;
        this.tipo = tipo;
        this.cfop = cfop;
        this.cliente = cliente;
        this.nota = nota;
        this.chave = chave;
        this.data = data;
        this.hora = hora;
        this.informacoesComplementares = informacoesComplementares;
        this.motorista = motorista;
        this.status = status;
        this.itens = itens;
    }

    public Transacao() {
    }

    public Transacao(int id) {
        this.id = id;
    }

    public Transacao(int id, TipoNota tipo, String nota, Cliente cliente, Date data) {
        this.id = id;
        this.tipo = tipo;
        this.nota = nota;
        this.cliente = cliente;
        this.data = data;
    }

    // Resto da classe (Getters e Setters)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
    }

    public CFOP getCfop() {
        return cfop;
    }

    public void setCfop(CFOP cfop) {
        this.cfop = cfop;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getInformacoesComplementares() {
        return informacoesComplementares;
    }

    public void setInformacoesComplementares(String informacoesComplementares) {
        this.informacoesComplementares = informacoesComplementares;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public StatusNota getStatus() {
        return status;
    }

    public void setStatus(StatusNota status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transacao{" + "id=" + id + ", tipo=" + tipo + ", cfop=" + cfop + ", cliente=" + cliente + ", nota=" + nota + ", chave=" + chave + ", data=" + data + ", hora=" + hora + ", informacoesComplementares=" + informacoesComplementares + ", motorista=" + motorista + ", itens=" + itens + ", deletado=" + deletado + '}';
    }

}
