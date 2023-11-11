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
import util.Moeda;
import util.Numero;

public class ItemTableModel extends AbstractTableModel {

    private List<Item> itens;
    private String[] colunas = {"#", "Id do Produto", "Nome do Produto", "Unidade", "Valor", "Quantidade", "Complemento", "Total item"};

    public ItemTableModel(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = itens.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return item.getProduto().getId();
            case 2:
                return item.getProduto().getDescricao();
            case 3:
                return item.getProduto().getUnidade().getSigla();
            case 4:
                return Moeda.formatadorDeMoeda("" + item.getProduto().getValor());
            case 5:
                return Numero.deStringForBigDecimal("" + item.getQuantidade());
            case 6:
                return item.getComplemento();
            case 7:
                return Moeda.formatadorDeMoeda("" + item.getQuantidade().multiply(item.getProduto().getValor()));
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
