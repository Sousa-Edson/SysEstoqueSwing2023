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
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ExpedicaoTableModel extends AbstractTableModel {

    private final List<Transacao> expedicoes;
    private final String[] colunas = {"#", "Número da Nota", "Cliente", "Data", "Status"};

    public ExpedicaoTableModel(List<Transacao> expedicoes) {
        this.expedicoes = expedicoes;
    }

    @Override
    public int getRowCount() {
        return expedicoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transacao expedicao = expedicoes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return expedicao.getId();
            case 1:
                return expedicao.getNota();
            case 2:
                return expedicao.getCliente();
            case 3:
                Date data = expedicao.getData(); // Supondo que getData() retorne a data da nota
                if (data != null) {
                    SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    return dataFormatter.format(data);
                } else {
                    return ""; // Ou outra mensagem indicando valor nulo
                }
            case 4:
                return expedicao.getStatus();
            default:
                return null;
        }
    }
}
