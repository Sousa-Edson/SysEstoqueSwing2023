/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author edson
 */
public enum TipoEmpresa {

    INDUSTRIA(1, "indústria"),
    SERVICOS(2, "serviços"),
    COMERCIO(3, "comércio");

    private final int id;
    private final String descricao;

    TipoEmpresa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEmpresa getById(int id) {
        for (TipoEmpresa tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de TipoCliente inválido: " + id);
    }

    @Override
    public String toString() {
        return descricao;
    }

}
