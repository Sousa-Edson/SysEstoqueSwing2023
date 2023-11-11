/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Main {

    public static void main(String[] args) {
//           JOptionPane.showMessageDialog(null, "Não é possível salvar uma transacão vazia.", "Aviso", JOptionPane.INFORMATION_MESSAGE );
//        JOptionPane.showInputDialog(null, "1", "2", JOptionPane.WARNING_MESSAGE);

//  Object[] optionsSecundaria = {"Sim", "Não"};
//                    if (JOptionPane.showOptionDialog(null, 
//                            "Deseja  cópiar também as informações complementares? ",
//                            "Cópia de informações complementares", 
//                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
//                            null, optionsSecundaria, optionsSecundaria[1]) == 0) {
//                        System.out.println("aaaaa");
//                    }
        String nome = "edson";
        System.out.println("1 " + nome.substring(1, 3));
        System.out.println("2 " + nome.substring(3));
    }
}
