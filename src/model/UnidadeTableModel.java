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
import model.Unidade;

public class UnidadeTableModel extends AbstractTableModel {
    private List<Unidade> unidades;
    private String[] colunas = {"ID", "Sigla", "Descrição", "Ativo"};

    public UnidadeTableModel(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    @Override
    public int getRowCount() {
        return unidades.size();
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) { // Coluna "Ativo" onde queremos exibir um JCheckBox
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Unidade unidade = unidades.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return unidade.getId();
            case 1:
                return unidade.getSigla();
            case 2:
                return unidade.getDescricao();
            case 3:
                return unidade.isAtivo();
            default:
                return null;
        }
    }

    // Adicione métodos para adicionar, atualizar e remover unidades conforme necessário
}
