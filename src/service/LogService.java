/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import dao.LogDAO;
import java.util.List;
import model.Log;

public class LogService {

    private static final LogDAO logDAO = new LogDAO();

    private LogService() {
        // Construtor privado para evitar a criação de instâncias
    }

    public static void salvarLog(Log log) {
        logDAO.salvarLog(log);
        System.out.println("log::"+log);
    }

    public static List<Log> obterLogsPorUsuario(int idUsuario) {
        return logDAO.obterLogsPorUsuario(idUsuario);
    }

    // Outros métodos relacionados a logs, se necessário
}
