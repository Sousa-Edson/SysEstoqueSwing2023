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

public class UsuarioTableModel extends AbstractTableModel {

    private final List<Usuario> usuarios;
    private final String[] colunas = {"ID", "Nome", "Nome de Usuário", "Tipo de Usuário", "Ativo"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getNomeUsuario();
            case 3:
                return usuario.getTipoUsuario();
            case 4:
                return usuario.isAtivo();
            default:
                return null; // Este caso não deveria ocorrer
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    public Usuario getUsuarioAt(int rowIndex) {
        return usuarios.get(rowIndex);
    }
}
