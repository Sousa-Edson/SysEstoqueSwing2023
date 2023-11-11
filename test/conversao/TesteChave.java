/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversao;

/**
 *
 * @author edson
 */
public class TesteChave {

    public static void main(String[] args) {
        String chave = "3523 1104 6169 3100 0170 5500 1000 0021 7210 002 17362";
        String chave1 = "3523 1104 6169 3100 0170 5500 1000 0021 7210 0002 1736 0000";
        String chave2 = "3523 1104 6169 3100 0170 5500 1000 0021 7210 0002";
        System.out.println(chave.substring(0, 54));

        System.out.println(chave1.substring(0, 54));

        if (chave2.length() > 54) {
            System.out.println(chave2.substring(0, 54));
        }

    }

}
