package entidades;

public enum StatusContasReceber {
    PAGO("Pago"), APAGAR("A Pagar"), PAGOPARCIALMENTE("Pago Parcialmente");
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private StatusContasReceber(String descricao) {
        this.descricao = descricao;
    }
}
