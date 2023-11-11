/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversao;

import java.math.BigDecimal;
import util.Moeda;
import util.MoedaDelete;

/**
 *
 * @author hp
 */
public class TesteMoeda {
      public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(12780956);
//        System.out.println("aqui " + Moeda.formatadorDeMoeda(bd));
        
        MoedaDelete dinheiro = new MoedaDelete(""+12780956);
        System.out.println("dinheiro " + dinheiro.getValor());
        System.out.println("dinheiro " + dinheiro.getValorFormatado());
    }
}
