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

public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    private String[] colunas = {"ID", "Descrição", "Unidade", "Valor", "NCM", "Observação", "Ativo"};

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getId();
            case 1:
                return produto.getDescricao();
            case 2:
                return produto.getUnidade();
            case 3:
                return Moeda.formatadorDeMoeda("" + produto.getValor());
            case 4:
                return produto.getNcm();
            case 5:
                return produto.getObservacao();
            case 6:
                return produto.isAtivo();
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
        if (columnIndex == 6) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public Produto getProdutoAt(int rowIndex) {
        return produtos.get(rowIndex);
    }
    
    
}
