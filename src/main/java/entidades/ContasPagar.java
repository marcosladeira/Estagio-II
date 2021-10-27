package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

@Entity
public class ContasPagar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataLancamento;
    private Date dataVencimento;
    private BigDecimal valor;
    private String numParcelas;
    private String descricao;
    @ManyToOne
    private ContaBancaria contaBancaria;
    @ManyToOne
    private Compra compra;
    @ManyToOne
    private PessoaJuridica pessoaJuridica;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "contasPagar")
    private List<BaixaContasPagar> baixaContasPagar;
    private StatusContasPagar statusContasPagar;

    public StatusContasPagar getStatusContasPagar() {
        return statusContasPagar;
    }

    public void setStatusContasPagar(StatusContasPagar statusContasPagar) {
        this.statusContasPagar = statusContasPagar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(String numParcelas) {
        this.numParcelas = numParcelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<BaixaContasPagar> getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(List<BaixaContasPagar> baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

    public BigDecimal getValorPago(){
        BigDecimal total = BigDecimal.ZERO;
        if(baixaContasPagar !=null){
            for (BaixaContasPagar b : baixaContasPagar){
                total = total.add(b.getValor());
            }
        }
        return total;
    }

    public BigDecimal getValorReceber(){
        BigDecimal total;
        total = getValor().subtract(getValorPago());
        return total;
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
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasPagar[ id=" + id + " ]";
    }

}
