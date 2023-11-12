/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import java.time.LocalDateTime;

public class Log {

    private int id;
    private int idUsuario;
    private String classeModel;
    private EventoLog eventoLog;
    private boolean marcadoComoDeletado;
    private LocalDateTime dataAlteracao;

    public Log(int idUsuario, String classeModel, EventoLog tipoAlteracao, boolean marcadoComoDeletado) {
        this.idUsuario = idUsuario;
        this.classeModel = classeModel;
        this.eventoLog = tipoAlteracao;
        this.marcadoComoDeletado = marcadoComoDeletado;
        this.dataAlteracao = LocalDateTime.now();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClasseModel() {
        return classeModel;
    }

    public void setClasseModel(String classeModel) {
        this.classeModel = classeModel;
    }

    public EventoLog getEventoLog() {
        return eventoLog;
    }

    public void setEventoLog(EventoLog eventoLog) {
        this.eventoLog = eventoLog;
    }

    public boolean isMarcadoComoDeletado() {
        return marcadoComoDeletado;
    }

    public void setMarcadoComoDeletado(boolean marcadoComoDeletado) {
        this.marcadoComoDeletado = marcadoComoDeletado;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public enum EventoLog {
        CRIAR,
        ALTERAR,
        ATIVAR_DESATIVAR,
        DELETAR
    }

}
