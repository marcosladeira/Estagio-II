package entidades;

public enum StatusVenda {
    ABERTO("Aberto"), FINALIZADA("Finalizada");
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private StatusVenda(String descricao) {
        this.descricao = descricao;
    }
}
