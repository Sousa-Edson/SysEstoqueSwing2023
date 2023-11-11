/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author edson
 */
public class StringUtils {

    public static String restringirTamanhoString(String texto, int tamanhoMaximo) {
        if (texto.length() <= tamanhoMaximo) {
            return texto;  // A string já está dentro do limite
        } else {
            return texto.substring(0, tamanhoMaximo);  // Retorna os primeiros 50 caracteres
        }
    }
}
