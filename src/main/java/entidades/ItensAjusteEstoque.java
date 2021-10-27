package entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ItensAjusteEstoque implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer estoqueAnterior;
    private Integer estoqueAtual;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private AjusteEstoque ajusteEstoque;

    public Integer getEstoqueAnterior() {
        return estoqueAnterior;
    }

    public void setEstoqueAnterior(Integer estoqueAnterior) {
        this.estoqueAnterior = estoqueAnterior;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public AjusteEstoque getAjusteEstoque() {
        return ajusteEstoque;
    }

    public void setAjusteEstoque(AjusteEstoque ajusteEstoque) {
        this.ajusteEstoque = ajusteEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensCompra)) {
            return false;
        }
        ItensAjusteEstoque other = (ItensAjusteEstoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItensAjusteEstoque[ id=" + id + " ]";
    }
}
