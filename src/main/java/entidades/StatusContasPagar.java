package entidades;

public enum StatusContasPagar {
    PAGO("Pago"), APAGAR("A Pagar"), PAGOPARCIALMENTE("Pago Parcialmente");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private StatusContasPagar(String descricao) {
        this.descricao = descricao;
    }
}
