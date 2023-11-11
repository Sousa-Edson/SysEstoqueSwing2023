/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author edson
 */
public enum StatusNota {
    CRIADO(0, "Criado", "**Criado:** Indica que a nota foi gerada ou criada no sistema, mas ainda não foi processada."),
    PENDENTE(1, "Pendente", "**Pendente:** A nota foi criada e aguarda ação ou processamento."),
    PREPARACAO(2, "Em preparação", "**Preparação:** Significa que a nota está sendo preparada para envio."),
    AGUARDANDO_APROVACAO(3, "Aguardando aprovação", "**Aguardando Aprovação:** Necessita de revisão ou autorização antes de ser enviada."),
    ENVIADO(4, "Enviado", "**Enviado:** A nota foi enviada ao destinatário ou aguarda envio."),
    PRONTO_PARA_COLETA(5, "Pronto para coleta", "**Pronto para Coleta:** A nota está pronta para ser coletada."),
    RECEBIDO(6, "Recebido", "**Recebido:** Indica que o destinatário recebeu a nota."),
    PROCESSADO(7, "Processado", " **Processado:** A nota foi processada e incluída no sistema."),
    CONCLUIDO(8, "Concluído", " **Concluído:** Significa que todos os processos relacionados à nota foram finalizados.");

    private final String descricao;
    private final int codigo;
    private final String informacao;

    StatusNota(int codigo, String descricao, String informacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.informacao = informacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getInformacao() {
        return informacao;
    }
    public static StatusNota getById(int id) {
        for (StatusNota tipo : values()) {
            if (tipo.codigo == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de StatusNota inválido: " + id);
    }

    
    
}
