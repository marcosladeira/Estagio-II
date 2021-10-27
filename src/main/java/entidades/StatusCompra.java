package entidades;

public enum StatusCompra {
    ABERTO("Aberto"), FINALIZADA("Finalizada");
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private StatusCompra(String descricao) {
        this.descricao = descricao;
    }
}
