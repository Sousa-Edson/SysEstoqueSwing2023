/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author edson
 */
import dao.UsuarioDAO;
import java.util.List;
import model.Log;
import model.Usuario;
import service.LogService;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean adicionarUsuario(String nome, String nomeUsuario, String senha, String tipoUsuario) {
        Usuario.TipoUsuario tipo = Usuario.TipoUsuario.valueOf(tipoUsuario.toUpperCase());
        Usuario usuario = new Usuario(nomeUsuario, nome, senha, tipo);

        // Adicione validações ou lógica de negócios, se necessário
        return usuarioDAO.adicionarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuariosNaoDeletados();
    }

    public Usuario obterUsuarioPorId(int id) {
        return usuarioDAO.obterUsuarioPorId(id);
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) {
        // Obter o usuário pelo nome de usuário
        Usuario usuario = usuarioDAO.obterUsuarioPorNomeUsuario(nomeUsuario);

        // Verificar se o usuário existe e a senha é válida
        if (usuario != null && usuario.verificarSenha(senha)) {
            //salvando log
            LogService.salvarLog(new Log(
                    usuario.getId(),
                    "Login:::" + nomeUsuario,
                    Log.EventoLog.LOGAR,
                    false));
            return usuario;
        }
        //salvando log
        LogService.salvarLog(new Log(
                1,
                "Login:::" + nomeUsuario,
                Log.EventoLog.NEGADO,
                false));
        return null;
    }

    public void atualizarStatusUsuario(int idUsuario, boolean ativo) {
        usuarioDAO.atualizarStatusUsuario(idUsuario, ativo);
    }

    public void atualizarTipoUsuario(int idUsuario, String novoTipoUsuario) {
        usuarioDAO.atualizarTipoUsuario(idUsuario, novoTipoUsuario);
    }

    public void marcarUsuarioComoDeletado(int idUsuario) {
        usuarioDAO.marcarUsuarioComoDeletado(idUsuario);
    }
}
