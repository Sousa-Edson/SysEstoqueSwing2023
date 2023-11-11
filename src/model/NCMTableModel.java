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

public class NCMTableModel extends AbstractTableModel {
    private List<NCM> listaNCMs;
    private String[] colunas = {"ID", "Código", "Descrição", "Ativo"};

    public NCMTableModel(List<NCM> listaNCMs) {
        this.listaNCMs = listaNCMs;
    }

    @Override
    public int getRowCount() {
        return listaNCMs.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NCM ncm = listaNCMs.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ncm.getId();
            case 1:
                return ncm.getCodigo();
            case 2:
                return ncm.getDescricao();
            case 3:
                return ncm.isAtivo();
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

    public NCM getNCMAt(int rowIndex) {
        return listaNCMs.get(rowIndex);
    }
}
