/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author Edson
 */
public class MoedaDelete { 
    
    private BigDecimal valor;
    private static final String UNIDADE_MONETARIA = "R$#,##0.00";
    private static final DecimalFormatSymbols FORMATO_SIMBOLOS = new DecimalFormatSymbols(new Locale("pt", "BR"));
    private static final DecimalFormat FORMATO = new DecimalFormat(UNIDADE_MONETARIA, FORMATO_SIMBOLOS);


    public MoedaDelete(String valor) {
        this.valor = new BigDecimal(valor);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getValorFormatado() {
        FORMATO.setMinimumFractionDigits(4); // Configurar o mínimo de 2 casas decimais
        return FORMATO.format(valor);
    }
}
