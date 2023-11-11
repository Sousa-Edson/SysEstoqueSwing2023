/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class ConexaoPostgres {
     private static final String URL = "jdbc:postgresql://localhost:5432/SysEstoqueSwing2023";
    private static final String USUARIO =  "admin"; // admin // postgres
    private static final String SENHA = "123456"; //123456 // 1
    
      public static Connection obterConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
        return null;
    }
    
}
