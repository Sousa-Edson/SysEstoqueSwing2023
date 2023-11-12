/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

    private int id;
    private String nomeUsuario;
    private String nome;
    private String senhaHash; // Armazenar o hash da senha, não a senha em texto simples
    private TipoUsuario tipoUsuario;

    private boolean ativo;
    private boolean deletado;

    // Construtores, getters e setters
    public Usuario() {
    }

    public Usuario(String nomeUsuario, String nome, String senhaHash, TipoUsuario tipoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.nome = nome;
        this.senhaHash = BCrypt.hashpw(senhaHash, BCrypt.gensalt());
        this.tipoUsuario = tipoUsuario;

    }

    public Usuario(int id, String nomeUsuario, String nome, String senhaHash, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nome = nome;
        this.senhaHash = BCrypt.hashpw(senhaHash, BCrypt.gensalt());
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(boolean deletado) {
        this.deletado = deletado;
    }

    // Método para definir e armazenar a senha como um hash usando BCrypt
    public void definirSenha(String senha) {
        this.senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    // Método para verificar se a senha fornecida coincide com o hash armazenado
    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senhaHash);
    }

    // Enum para representar o tipo de usuário
    public enum TipoUsuario {
        ADMINISTRADOR, COMUM
    }
}
