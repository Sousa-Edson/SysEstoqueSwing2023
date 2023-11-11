/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CFOPTableModel extends AbstractTableModel {

    private List<CFOP> listaCFOPs;
    private String[] colunas = {"ID", "Código", "Descrição", "Ativo"};

    public CFOPTableModel(List<CFOP> listaCFOPs) {
        this.listaCFOPs = listaCFOPs;
    }

    @Override
    public int getRowCount() {
        return listaCFOPs.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CFOP cfop = listaCFOPs.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cfop.getId();
            case 1:
                return cfop.getCodigo();
            case 2:
                return cfop.getDescricao();
            case 3:
                return cfop.isAtivo();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public CFOP getCFOPAt(int rowIndex) {
        return listaCFOPs.get(rowIndex);
    }
}
