/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.text.DateFormatter;

public class TransacaoTableModel extends AbstractTableModel {

    private final List<Transacao> transacoes;
    private final String[] colunas = {"ID", "Tipo", "CFOP", "Cliente", "Nota", "Chave", "Data", "Hora"};

    public TransacaoTableModel(List<Transacao> transacoes) {
//        System.out.println("" + transacoes.get(3).getData());
        this.transacoes = transacoes;
    }

    @Override
    public int getRowCount() {
        return transacoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transacao transacao = transacoes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return transacao.getId();
            case 1:
                return transacao.getTipo().name();  // Mostra o nome do enum
            case 2:
                return transacao.getCfop().getCodigo();  // Mostra o ID do CFOP
            case 3:
                return transacao.getCliente().getNomeFantasia();  // Mostra o nome do cliente
            case 4:
                return transacao.getNota();
            case 5:
                return transacao.getChave();
            case 6:
                Date data = transacao.getData();
                if (data != null) {
                    SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    return dataFormatter.format(data);
                } else {
                    return ""; // Ou outra mensagem indicando valor nulo
                }
            case 7:
                Time hora = transacao.getHora();
                if (hora != null) {
                    SimpleDateFormat horaFormatter = new SimpleDateFormat("HH:mm:ss");
                    return horaFormatter.format(hora);
                } else {
                    return ""; // Ou outra mensagem indicando valor nulo
                }

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // Se desejar, pode definir os tipos de dados de coluna em getColumnClass para classificar corretamente
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        } else if (columnIndex == 6) {
            return java.sql.Date.class;  // Ajuste o tipo de data conforme necessário
        } else if (columnIndex == 7) {
            return java.sql.Time.class;  // Ajuste o tipo de hora conforme necessário
        } else {
            return String.class;
        }
    }
}
