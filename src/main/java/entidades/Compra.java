package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Compra implements Serializable, ClassePai{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataCompra;
    @ManyToOne
    private PessoaJuridica pessoaJuridica;
    private BigDecimal valorTotal;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "compra",
            orphanRemoval = true)
    private List<ItensCompra> itensCompra;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "compra",
            orphanRemoval = true)
    private List<ContasPagar> contasPagar;
    @ManyToOne
    private FormaPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(StatusCompra statusCompra) {
        this.statusCompra = statusCompra;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ContasPagar> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<ContasPagar> contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Date  getDataCompra() {
        return dataCompra;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<ItensCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getTotalCompra(){
        valorTotal = BigDecimal.ZERO;
        for(ItensCompra it : itensCompra) {
            valorTotal = valorTotal.add(it.getSubTotal());
        }
        return valorTotal;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Compra[ id=" + id + " ]";
    }
}
