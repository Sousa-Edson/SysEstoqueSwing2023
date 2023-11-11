/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author edson
 */
public enum TipoNota {
    ENTRADA(0), SAIDA(1);
    int valor;

    TipoNota(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
     public static TipoNota getById(int id) {
        for (TipoNota tipo : values()) {
            if (tipo.valor == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de TipoNota inválido: " + id);
    }
} 
