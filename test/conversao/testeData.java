/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversao;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author edson
 */
public class testeData {
    public static void main(String[] args) { 
         String dataNoFormatoISO = "2023-11-24";
        
        // Crie um objeto SimpleDateFormat para o formato de entrada
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
        
        // Crie um objeto SimpleDateFormat para o formato de saída
        SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            // Faça o parsing da data no formato de entrada
            Date data = formatoEntrada.parse(dataNoFormatoISO);
            
            // Formate a data para o formato de saída desejado
            String dataFormatada = formatoSaida.format(data);
            
            // Exiba a data formatada
            System.out.println("Data formatada: " + dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    
    
}
