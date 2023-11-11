/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author edson
 */
public class DataValidator {
    

public static boolean isDataValid(String dataStr, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false);  // Define para não ser tolerante a datas inválidas.

        try {
            Date data = sdf.parse(dataStr);
            // A data é válida.
            return true;
        } catch (ParseException e) {
            // A data é inválida.
            return false;
        }
    }
}