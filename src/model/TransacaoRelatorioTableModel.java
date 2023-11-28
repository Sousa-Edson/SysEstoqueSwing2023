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
import javax.swing.SwingConstants;
import util.Moeda;
import util.Numero;

public class TransacaoRelatorioTableModel extends AbstractTableModel {

    private final List<Item> itens;
    private final String[] colunas = {"ID", "Tipo da Nota", "Data", "Nota", "Cliente", "Produto", "Complemento", "Quantidade", "Unidade", "Valor"};

    public TransacaoRelatorioTableModel(List<Item> itens) {
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
                return item.getId(); // Supondo que getId() retorne o ID do item
            case 1:
                return item.getTransacao().getTipo(); // Supondo que getTipoNota() retorne o tipo da nota (entrada/saída)

            case 2:
                Date data = item.getTransacao().getData(); // Supondo que getData() retorne a data da nota
                if (data != null) {
                    SimpleDateFormat dataFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    return dataFormatter.format(data);
                } else {
                    return ""; // Ou outra mensagem indicando valor nulo
                }
            case 3:
                return item.getTransacao().getNota(); // Supondo que getNota() retorne o número da nota
            case 4:
                return item.getTransacao().getCliente().getNomeFantasia(); // Supondo que getCliente().getNome() retorne o nome do cliente
            case 5:
                return item.getProduto().getDescricao(); // Supondo que getProduto().getNome() retorne o nome do produto
            case 6:
                return item.getComplemento(); // Supondo que getComplemento() retorne o complemento
            case 7:
                  
                return Numero.deStringForBigDecimal("" + item.getQuantidade());
//                return item.getQuantidade();
            case 8:
                return item.getProduto().getUnidade().getSigla();
            case 9:
                return Moeda.formatadorDeMoeda("" + item.getProduto().getValor());

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}
