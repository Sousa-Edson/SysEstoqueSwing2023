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
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Log;

public class LogDAO {

    private Connection conexao;

    public LogDAO() {
        try {
            this.conexao = ConexaoPostgres.obterConexao(); // Supondo que você tenha uma classe de conexão
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarLog(Log log) {
        try {
            String sql = "INSERT INTO log (id_usuario, classe_model, tipo_alteracao, marcado_como_deletado, data_alteracao) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, log.getIdUsuario());
            preparedStatement.setString(2, log.getClasseModel());
            preparedStatement.setString(3, log.getEventoLog().toString());
            preparedStatement.setBoolean(4, log.isMarcadoComoDeletado());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(log.getDataAlteracao()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int idGerado = resultSet.getInt(1);
                log.setId(idGerado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Log> obterLogsPorUsuario(int idUsuario) {
        List<Log> logs = new ArrayList<>();
        try {
            String sql = "SELECT id, id_usuario, classe_model, tipo_alteracao, marcado_como_deletado, data_alteracao FROM log WHERE id_usuario = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String classeModel = resultSet.getString("classe_model");
                Log.EventoLog tipoAlteracao =  Log.EventoLog.valueOf(resultSet.getString("tipo_alteracao"));
                boolean marcadoComoDeletado = resultSet.getBoolean("marcado_como_deletado");
                LocalDateTime dataAlteracao = resultSet.getTimestamp("data_alteracao").toLocalDateTime();

                Log log = new Log(idUsuario, classeModel, tipoAlteracao, marcadoComoDeletado);
                log.setId(id);
                log.setDataAlteracao(dataAlteracao);

                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    // Outros métodos relacionados a logs, se necessário
}

