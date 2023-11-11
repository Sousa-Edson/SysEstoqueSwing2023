/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.ClienteDAO;
import model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean adicionarCliente(Cliente cliente) {
        if (cliente.getId() == 0) {
            return clienteDAO.adicionarCliente(cliente);
        } else {
            return clienteDAO.atualizarCliente(cliente);
        }
    }

    public boolean atualizarCliente(Cliente cliente) {
        return clienteDAO.atualizarCliente(cliente);
    }

    public void excluirCliente(int id) {
        clienteDAO.excluirCliente(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public Cliente obterClientePorId(int id) {
        return clienteDAO.obterClientePorId(id);
    }

    public void marcarClienteComoDeletado(int id) {
        clienteDAO.marcarClienteComoDeletado(id);
    }
}
