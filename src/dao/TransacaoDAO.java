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
import model.Unidade;
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
            String sql = "SELECT \n"
                    + "    t.id, \n"
                    + "    t.tipo, \n"
                    + "    t.cfop, \n"
                    + "    t.cliente, \n"
                    + "    t.nota, \n"
                    + "    t.chave, \n"
                    + "    t.data_transacao, \n"
                    + "    t.hora_transacao, \n"
                    + "    t.informacoes_complementares, \n"
                    + "    t.deletado, \n"
                    + "    t.nome_motorista, \n"
                    + "    t.status_nota, \n"
                    + "    t.idantigo,\n"
                    + "    c.codigo,\n"
                    + "    c.descricao,\n"
                    + "    cl.tipo_cliente,\n"
                    + "    cl.cnpj,\n"
                    + "    cl.razao_social,\n"
                    + "    cl.nome_fantasia,\n"
                    + "    cl.inscricao_estadual,\n"
                    + "    cl.inscricao_municipal,\n"
                    + "    cl.endereco,\n"
                    + "    cl.contato,\n"
                    + "    cl.responsavel_legal,\n"
                    + "    cl.tipo_empresa,\n"
                    + "    cl.deletado AS deletado_cliente,\n"
                    + "    cl.idantigo AS idantigo_cliente,\n"
                    + "    cl.ativo AS ativo_cliente,\n"
                    + "    cl.cep,\n"
                    + "    cl.complemento,\n"
                    + "    cl.bairro,\n"
                    + "    cl.cidade\n"
                    + "FROM \n"
                    + "    public.transacao t\n"
                    + "    INNER JOIN public.cfop c ON t.cfop = c.id\n"
                    + "    INNER JOIN public.cliente cl ON t.cliente = cl.id\n"
                    + "ORDER BY \n"
                    + "    t.id desc;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tipo = resultSet.getInt("tipo");
                int cfop = resultSet.getInt("cfop");
                String codigo = resultSet.getString("descricao");
                int cliente = resultSet.getInt("cliente");
                String nome_fantasia = resultSet.getString("nome_fantasia");
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
                        new CFOP(cfop, codigo),
                        new Cliente(cliente, nome_fantasia),
                        nota, chave,
                        dataTransacao,
                        horaTransacao,
                        informacoesComplementares, motorista, StatusNota.getById(status),
                        Item);
//                 gean luiz
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
//            String sql = "SELECT id, produto_id, complemento, quantidade, tipo, transacao_id, "
//                    + "deletado FROM item WHERE deletado = false ORDER BY id DESC ";
            String sql = "SELECT \n"
                    + "    t.id AS ID,\n"
                    + "    t.tipo AS Tipo_Nota,\n"
                    + "    t.data_transacao AS Data,\n"
                    + "    t.nota AS Nota, \n"
                    + "    c.nome_fantasia,\n"
                    + "    p.descricao AS Produto,\n"
                    + "    i.complemento AS Complemento,\n"
                    + "    i.quantidade AS Quantidade, \n"
                    + "    u.sigla,\n"
                    + "    p.valor AS Valor\n"
                    + "FROM \n"
                    + "    item i\n"
                    + "    INNER JOIN produto p ON i.produto_id = p.id\n"
                    + "    INNER JOIN transacao t ON i.transacao_id = t.id\n"
                    + "    INNER JOIN cliente c ON t.cliente  = c.id\n"
                    + "    INNER JOIN unidade u ON p.unidade_id  = u.id\n"
                    + "WHERE \n"
                    + "    i.deletado = false\n"
                    + "ORDER BY \n"
                    + "    t.id DESC;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String produto = resultSet.getString("Produto");
                String complemento = resultSet.getString("complemento");
                BigDecimal bigDecimal = resultSet.getBigDecimal("quantidade");
                BigDecimal valor = resultSet.getBigDecimal("Valor");
                int tipoNota = resultSet.getInt("Tipo_Nota");
                String nota = resultSet.getString("Nota");
                int transacao = resultSet.getInt("ID");
                String cliente = resultSet.getString("nome_fantasia");
                String unidade = resultSet.getString("sigla");
                List<Item> Item = null;
                Date dataTransacao = resultSet.getDate("Data");

                Item item = new Item(id, new Produto(produto, valor, new Unidade(unidade)), complemento,
                        bigDecimal, TipoNota.getById(tipoNota), new Transacao(transacao, TipoNota.getById(tipoNota),
                        nota, new Cliente(cliente), dataTransacao));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

}
