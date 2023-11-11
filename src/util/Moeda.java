/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author edson
 */
public class Moeda {

    private static final String UNIDADE_MONETARIA = "R$ #,##0.00";
    private static final DecimalFormatSymbols FORMATO_SIMBOLOS = new DecimalFormatSymbols(new Locale("pt", "BR"));
    private static final DecimalFormat FORMATO = new DecimalFormat(UNIDADE_MONETARIA, FORMATO_SIMBOLOS);

    public static String formatadorDeMoeda(String valor) {
        FORMATO.setMinimumFractionDigits(4);
        BigDecimal bg = new BigDecimal(valor);
        return FORMATO.format(bg);
    }

    public static String deMoedaParaNumero(String valor) throws SQLException {
        return valor.trim().replace("R$", "").replace(".", "").replace(",", ".").trim();
    }
}
