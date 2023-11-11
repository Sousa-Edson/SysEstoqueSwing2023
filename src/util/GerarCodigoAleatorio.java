/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author edson
 */
public class GerarCodigoAleatorio {
    // Método para gerar um código aleatório de 4 dígitos

    public static String gerarCodigoAleatorio() {
        int numeroAleatorio = (int) (Math.random() * 10000);
        return String.format("%04d", numeroAleatorio);
    }
}
