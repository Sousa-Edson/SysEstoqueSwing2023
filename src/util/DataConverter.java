/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author edson
 */
public class DataConverter {

    public static String dataParaString(String dataNoFormatoISO) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatoSaida.format(formatoEntrada.parse(dataNoFormatoISO));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
