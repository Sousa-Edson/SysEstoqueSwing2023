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

public class ClienteTableModel extends AbstractTableModel {
    private final List<Cliente> clientes;
    private final String[] colunas = {"ID", "Tipo Cliente", "CNPJ", "Razão Social",
        "Nome Fantasia", "Inscrição Estadual", "Inscrição Municipal", "Endereço", "Contato", "Responsável Legal", "Tipo Empresa"};

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return cliente.getId();
            case 1:
                return cliente.getTipoCliente();
            case 2:
                return cliente.getCnpj();
            case 3:
                return cliente.getRazaoSocial();
            case 4:
                return cliente.getNomeFantasia();
            case 5:
                return cliente.getInscricaoEstadual();
            case 6:
                return cliente.getInscricaoMunicipal();
            case 7:
                return cliente.getEndereco();
            case 8:
                return cliente.getContato();
            case 9:
                return cliente.getResponsavelLegal();
            case 10:
                return cliente.getTipoEmpresa();
            default:
                return null;
        }
    }
}
