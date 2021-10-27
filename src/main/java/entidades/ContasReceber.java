package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class ContasReceber implements Serializable {
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
    private Venda venda;
    @ManyToOne
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "contasReceber")
    private List<BaixaContasReceber> baixaContasReceber;
    @Enumerated(EnumType.STRING)
    private StatusContasReceber statusContasReceber;

    public StatusContasReceber getStatusContasReceber() {
        return statusContasReceber;
    }

    public void setStatusContasReceber(StatusContasReceber statusContasReceber) {
        this.statusContasReceber = statusContasReceber;
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

    public List<BaixaContasReceber> getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(List<BaixaContasReceber> baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }
    public String getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(String numParcelas) {
        this.numParcelas = numParcelas;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public BigDecimal getValorPago(){
        BigDecimal total = BigDecimal.ZERO;
       if(baixaContasReceber!=null){
           for(BaixaContasReceber b : baixaContasReceber){
               total = b.getValor().add(total);
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
        ContasReceber other = (ContasReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasReceber[ id=" + id + " ]";
    }


}
