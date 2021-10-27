package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.*;
import facade.CompraFacade;
import facade.FormaPagamentoFacade;
import facade.PessoaJuridicaFacade;
import facade.ProdutoFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class CompraControle implements Serializable {
    @Inject
    private CompraFacade compraFacade;
    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private FormaPagamentoFacade formaPagamentoFacade;
    private Compra compra;
    private ItensCompra itensCompra;
    private FormaPagamento formaPagamento;
    private ConverterGenerico pessoaJuridicaConverter;
    private ConverterGenerico produtoConverter;
    private ConverterGenerico formaPagamentoConverter;
    private MoneyConverter moneyConverter;
    private Integer numeroParcelas;

    public void addProduto() {
        System.out.println(compra!=null);
        if (itensCompra.getQuantidade() == null || itensCompra.getValor() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            int estoque = itensCompra.getProduto().getEstoque();
            ItensCompra itemTemp = null;
            for (ItensCompra it : compra.getItensCompra()) {
                if (it.getProduto().equals(itensCompra.getProduto())) {
                    itemTemp = it;
                    estoque = (int) (estoque + it.getQuantidade());
                }
            }
            if (itemTemp == null) {
                itensCompra.setCompra(compra);
                compra.getItensCompra().add(itensCompra);
            } else {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensCompra.getQuantidade());
            }
            itensCompra = new ItensCompra();
        }
    }

    public void removeProd(ItensCompra it) {
        compra.getItensCompra().remove(it);
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public void atualizaPrecoProduto() {
        itensCompra.setValor(itensCompra.getProduto().getValorCompra());
    }

    public void novo() {
        compra = new Compra();
        compra.setStatusCompra(StatusCompra.ABERTO);
        compra.setItensCompra(new ArrayList<>());
        itensCompra = new ItensCompra();
        numeroParcelas = 1;
        compra.setContasPagar(new ArrayList<ContasPagar>());
    }

    public void gerarParcelas() {
        if (getNumeroParcelas()> compra.getFormaPagamento().getNumParcelas()){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O número de parcelas digitado é superior ao número de parcelas da forma de pagamento!",
                            "O número máximo permitido é "
                                    + compra.getFormaPagamento().getNumParcelas()
                                    + " parcelas."));
        }else {
            System.out.println(formaPagamento!=null);
            compra.setContasPagar(new ArrayList<ContasPagar>());
            BigDecimal valor = compra.getTotalCompra().divide(new BigDecimal(numeroParcelas), RoundingMode.HALF_EVEN);
            Date dataComp = compra.getDataCompra();
            for (Integer i = 1; i <= numeroParcelas; i++) {
                ContasPagar cp = new ContasPagar();
                compra.setStatusCompra(StatusCompra.FINALIZADA);
                cp.setStatusContasPagar(StatusContasPagar.APAGAR);
                cp.setDataLancamento(compra.getDataCompra());
                cp.setDescricao(compra.getPessoaJuridica().getNome());
                cp.setNumParcelas(i.toString() + "/" + numeroParcelas.toString());
                cp.setValor(valor);
                cp.setDataVencimento(dataComp);
                cp.setCompra(compra);
                if (compra.getPessoaJuridica() != null) {
                    cp.setPessoaJuridica(compra.getPessoaJuridica());
                }
                compra.getContasPagar().add(cp);
                //Soma 1 mês no compcimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataComp);
                cal.add(Calendar.MONTH, 1);
                dataComp = cal.getTime();
            }
        }
    }

    public ConverterGenerico getPessoaJuridicaConverter() {
        if (pessoaJuridicaConverter == null) {
            pessoaJuridicaConverter = new ConverterGenerico(pessoaJuridicaFacade);
        }
        return pessoaJuridicaConverter;
    }

    public void setPessoaJuridicaConverter(ConverterGenerico pessoaJuridicaConverter) {
        this.pessoaJuridicaConverter = pessoaJuridicaConverter;
    }

    public ConverterGenerico getFormaPagamentoConverter() {
        if (formaPagamentoConverter == null) {
            formaPagamentoConverter = new ConverterGenerico(formaPagamentoFacade);
        }
        return formaPagamentoConverter;
    }

    public void setFormaPagamentoConverter(ConverterGenerico formaPagamentoConverter) {
        this.formaPagamentoConverter = formaPagamentoConverter;
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public void salvar() {
        compraFacade.save(compra);
    }
    public void excluir(Compra e) {
        compraFacade.remove(e);
    }

    public void editar(Compra e) {
        compra = e;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Compra> getLista() {
        return compraFacade.findAll();
    }

    public List<PessoaJuridica> getListaPessoaJuridicaFiltrando(String filtro) {
        return pessoaJuridicaFacade.listaFiltrando(filtro, "nome", "cnpj");
    }

    public List<Produto> getListaProdutosFiltrando(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }

    public List<FormaPagamento> getListaFormaPagamentoFiltrando(String filtro) {
        return formaPagamentoFacade.listaFiltrando(filtro, "nome");
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
