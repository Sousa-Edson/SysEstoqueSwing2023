/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author edson
 */
import conexao.ConexaoPostgres;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Log;
import model.UsuarioLogado;
import service.LogService;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() {
        try {
            conexao = ConexaoPostgres.obterConexao();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
    }

    public boolean adicionarUsuario(Usuario usuario) {
        try (PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO usuario (nome, nome_usuario, senha_hash, tipo_usuario) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getNomeUsuario());
            preparedStatement.setString(3, usuario.getSenhaHash());
            preparedStatement.setString(4, usuario.getTipoUsuario().name());
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    1,
                    "Usuario",
                    Log.EventoLog.CRIAR,
                    false));
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário", e);
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM usuario"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = criarUsuario(resultSet);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }

        return usuarios;
    }

    public Usuario obterUsuarioPorId(int id) {
        try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM usuario WHERE id = ?")) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return criarUsuario(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter usuário por ID", e);
        }

        return null;
    }

    // Outros métodos conforme necessário
    private Usuario criarUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setNomeUsuario(resultSet.getString("nome_usuario"));
        usuario.setSenhaHash(resultSet.getString("senha_hash"));
        usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(resultSet.getString("tipo_usuario")));
        usuario.setAtivo(resultSet.getBoolean("ativo"));
        // Adicione outros campos conforme necessário

        return usuario;
    }

    public Usuario obterUsuarioPorNomeUsuario(String nomeUsuario) {
        try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM usuario WHERE nome_usuario = ?")) {
            preparedStatement.setString(1, nomeUsuario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return criarUsuario(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter usuário por nome de usuário", e);
        }

        return null;
    }

    public void atualizarStatusUsuario(int idUsuario, boolean ativo) {
        try {
            String sql = "UPDATE usuario SET ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, ativo);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Usuario",
                    Log.EventoLog.ATIVAR_DESATIVAR,
                    false));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do usuário", e);
        }
    }

    public void atualizarTipoUsuario(int idUsuario, String novoTipoUsuario) {
        try {
            String sql = "UPDATE usuario SET tipo_usuario = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novoTipoUsuario);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Usuario",
                    Log.EventoLog.ALTERAR,
                    false));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tipo de usuário", e);
        }
    }

    public void marcarUsuarioComoDeletado(int idUsuario) {
        try {
            String sql = "UPDATE usuario SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeUpdate();
            //salvando log
            LogService.salvarLog(new Log(
                    UsuarioLogado.getUsuarioLogado().getId(),
                    "Usuario",
                    Log.EventoLog.DELETAR,
                    true));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao marcar usuário como deletado", e);
        }
    }

    public List<Usuario> listarUsuariosNaoDeletados() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM usuario WHERE deletado =false  ORDER BY id DESC"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = criarUsuario(resultSet);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }

        return usuarios;
    }

}
