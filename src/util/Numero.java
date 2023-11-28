/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author edson
 */
public class Numero {

    private static final DecimalFormat FORMATO = new DecimalFormat();

    public static boolean isNumeroValido(String input) {
        return input.matches("-?\\d+");
    }

    public static String deStringForBigDecimal(String valor) {
        FORMATO.setMinimumFractionDigits(3);
        BigDecimal bg = new BigDecimal(valor);
//                return FORMATO.format(bg).trim().replace(".", ",");
        return FORMATO.format(bg).trim();
    }

    public static String deBigDecimalForString(String valor) {
        System.out.println("v++" + valor);
        return FORMATO.format(valor).trim().replace(".", "").replace(",", ".");
    }

}
