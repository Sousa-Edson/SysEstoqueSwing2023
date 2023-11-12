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
import enums.StatusNota;
import enums.TipoNota;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.CFOP;
import model.Cliente;
import model.Item;
import model.Log;
import model.Produto;
import model.Transacao;
import model.UsuarioLogado;
import service.LogService;

public class TransacaoDAO {

    private Connection conexao;

//    public TransacaoDAO(Connection conexao) {
//        this.conexao = conexao;
//    }
    public TransacaoDAO() throws SQLException {
        conexao = ConexaoPostgres.obterConexao();
    }

    public boolean adicionarTransacao(Transacao transacao) {
        try {
            String sql = "INSERT INTO transacao (tipo, cfop, cliente, nota, chave, "
                    + "data_transacao, hora_transacao, informacoes_complementares, deletado,nome_motorista) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?) RETURNING id";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, transacao.getTipo().getValor());
            preparedStatement.setInt(2, transacao.getCfop().getId());
            preparedStatement.setInt(3, transacao.getCliente().getId());
            preparedStatement.setString(4, transacao.getNota());
            preparedStatement.setString(5, transacao.getChave());
            preparedStatement.setDate(6, transacao.getData());
            preparedStatement.setTime(7, transacao.getHora());
            preparedStatement.setString(8, transacao.getInformacoesComplementares());
            preparedStatement.setBoolean(9, transacao.isDeletado());
            preparedStatement.setString(10, transacao.getMotorista());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int transacaoId = resultSet.getInt(1);
                transacao.setId(transacaoId);

                // Agora, salve os itens associados a esta transação
                for (Item item : transacao.getItens()) {
                    String sqlItem = "INSERT INTO item (transacao_id, produto_id, complemento, quantidade, tipo) "
                            + "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatementItem = conexao.prepareStatement(sqlItem);
                    preparedStatementItem.setInt(1, transacaoId);
                    preparedStatementItem.setInt(2, item.getProduto().getId());
                    preparedStatementItem.setString(3, item.getComplemento());
                    preparedStatementItem.setBigDecimal(4, item.getQuantidade());
                    preparedStatementItem.setInt(5, item.getTipo().getValor());
                    preparedStatementItem.executeUpdate();
                }
                //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Transacao",
                        Log.EventoLog.CRIAR,
                        false));
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarTransacao(Transacao transacao) {
        try {
            String sql = "UPDATE transacao SET  informacoes_complementares = ?, nome_motorista = ? "
                    + "WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, transacao.getInformacoesComplementares());
            preparedStatement.setString(2, transacao.getMotorista());
            preparedStatement.setInt(3, transacao.getId());
            preparedStatement.executeUpdate();
             //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Transacao",
                        Log.EventoLog.ALTERAR,
                        false));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void excluirTransacao(int id) {
        try {
            String sql = "DELETE FROM transacao WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transacao> listarTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        try {
            String sql = "SELECT id, tipo, cfop, cliente, nota, chave, data_transacao,"
                    + " hora_transacao, informacoes_complementares, nome_motorista,status_nota FROM transacao WHERE deletado = false "
                    + "ORDER BY id DESC";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tipo = resultSet.getInt("tipo");
                int cfop = resultSet.getInt("cfop");
                int cliente = resultSet.getInt("cliente");
                String nota = resultSet.getString("nota");
                String chave = resultSet.getString("chave");
                Date dataTransacao = resultSet.getDate("data_transacao");
                Time horaTransacao = resultSet.getTime("hora_transacao");
                String informacoesComplementares = resultSet.getString("informacoes_complementares");
                String motorista = resultSet.getString("nome_motorista");
                int status = resultSet.getInt("status_nota");

                List<Item> Item = null;

                Transacao transacao = new Transacao(id,
                        TipoNota.getById(tipo),
                        new CFOP(cfop),
                        new Cliente(cliente),
                        nota, chave,
                        dataTransacao,
                        horaTransacao,
                        informacoesComplementares, motorista, StatusNota.getById(status),
                        Item);
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transacoes;
    }

    public Transacao obterTransacaoPorId(int id) {
        Transacao transacao = null;
        try {
            String sql = "SELECT tipo, cfop, cliente, nota, chave, data_transacao, "
                    + "hora_transacao, informacoes_complementares, nome_motorista , status_nota FROM transacao WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int tipo = resultSet.getInt("tipo");
                int cfop = resultSet.getInt("cfop");
                int cliente = resultSet.getInt("cliente");
                String nota = resultSet.getString("nota");
                String chave = resultSet.getString("chave");
                Date dataTransacao = resultSet.getDate("data_transacao");
                Time horaTransacao = resultSet.getTime("hora_transacao");
                String informacoesComplementares = resultSet.getString("informacoes_complementares");
                String motorista = resultSet.getString("nome_motorista");
                int status = resultSet.getInt("status_nota");

                List<Item> Item = null;

                transacao = new Transacao(id,
                        TipoNota.getById(tipo),
                        new CFOP(cfop),
                        new Cliente(cliente),
                        nota, chave,
                        dataTransacao,
                        horaTransacao,
                        informacoesComplementares, motorista, StatusNota.getById(status),
                        Item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacao;
    }

    public void marcarTransacaoComoDeletada(int id) {
        try {
            String sql = "UPDATE transacao SET deletado = true WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            marcarItensDaTransacaoComoDeletada(id);
             //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Transacao",
                        Log.EventoLog.DELETAR,
                        true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void marcarItensDaTransacaoComoDeletada(int id) {
        try {
            String sql = "UPDATE item SET deletado = true WHERE transacao_id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transacao> listarTransacoesAtivas() {
        List<Transacao> transacoes = new ArrayList<>();
        try {
            String sql = "SELECT id, tipo, cfop, cliente, nota, chave, data_transacao,"
                    + " hora_transacao, informacoes_complementares,nome_motorista, deletado FROM transacao WHERE deletado = false";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tipo = resultSet.getInt("tipo");
                int cfop = resultSet.getInt("cfop");
                int cliente = resultSet.getInt("cliente");
                String nota = resultSet.getString("nota");
                String chave = resultSet.getString("chave");
                Date dataTransacao = resultSet.getDate("data_transacao");
                Time horaTransacao = resultSet.getTime("hora_transacao");
                String informacoesComplementares = resultSet.getString("informacoes_complementares");
                boolean deletado = resultSet.getBoolean("deletado");
                String motorista = resultSet.getString("nome_motorista");
                List<Item> Item = null;

                Transacao transacao = new Transacao(id,
                        TipoNota.getById(tipo),
                        new CFOP(cfop),
                        new Cliente(cliente),
                        nota, chave,
                        dataTransacao,
                        horaTransacao,
                        informacoesComplementares, motorista,
                        Item);
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    public List<Item> listarItensAtivosDaTransacao(int idTransacao) {
        List<Item> itens = new ArrayList<>();
        try {
            String sql = "SELECT id, produto_id, complemento, quantidade, tipo, transacao_id, "
                    + "deletado FROM item WHERE deletado = false and transacao_id =" + idTransacao;
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int produto = resultSet.getInt("produto_id");
                String complemento = resultSet.getString("complemento");
                BigDecimal bigDecimal = resultSet.getBigDecimal("quantidade");
                int tipoNota = resultSet.getInt("tipo");
                boolean deletado = resultSet.getBoolean("deletado");
                List<Item> Item = null;

                Item item = new Item(new Produto(produto), complemento, bigDecimal, TipoNota.getById(tipoNota));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    public boolean atualizaTransacaoExpedicao(Transacao transacao) {
        try {
            String sql = "UPDATE transacao SET tipo = ?, cfop = ?, cliente = ?, nota = ?, chave = ?, "
                    + "data_transacao = ?, hora_transacao = ?, informacoes_complementares = ?, deletado = ?, nome_motorista = ? ,"
                    + "status_nota=? "
                    + "WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, transacao.getTipo().getValor());
            preparedStatement.setInt(2, transacao.getCfop().getId());
            preparedStatement.setInt(3, transacao.getCliente().getId());
            preparedStatement.setString(4, transacao.getNota());
            preparedStatement.setString(5, transacao.getChave());
            preparedStatement.setDate(6, transacao.getData());
            preparedStatement.setTime(7, transacao.getHora());
            preparedStatement.setString(8, transacao.getInformacoesComplementares());
            preparedStatement.setBoolean(9, transacao.isDeletado());
            preparedStatement.setString(10, transacao.getMotorista());
            preparedStatement.setInt(11, transacao.getStatus().getCodigo());
            preparedStatement.setInt(12, transacao.getId()); // Assumindo que o ID seja o parâmetro para a atualização

            int linhasAfetadas = preparedStatement.executeUpdate();

             //salvando log
                LogService.salvarLog(new Log(
                        UsuarioLogado.getUsuarioLogado().getId(),
                        "Transacao",
                        Log.EventoLog.ALTERAR,
                        false));
            
            return linhasAfetadas > 0; // Retorna verdadeiro se a atualização for bem-sucedida (uma ou mais linhas foram atualizadas)
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> listarTodosItensAtivos() {
        List<Item> itens = new ArrayList<>();
        try {
            String sql = "SELECT id, produto_id, complemento, quantidade, tipo, transacao_id, "
                    + "deletado FROM item WHERE deletado = false ORDER BY id DESC ";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int produto = resultSet.getInt("produto_id");
                String complemento = resultSet.getString("complemento");
                BigDecimal bigDecimal = resultSet.getBigDecimal("quantidade");
                int tipoNota = resultSet.getInt("tipo");
                boolean deletado = resultSet.getBoolean("deletado");
                int transacao = resultSet.getInt("transacao_id");
                List<Item> Item = null;

                Item item = new Item(id, new Produto(produto), complemento,
                        bigDecimal, TipoNota.getById(tipoNota), new Transacao(transacao));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

}
