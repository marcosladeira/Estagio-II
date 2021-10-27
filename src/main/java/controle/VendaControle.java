package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.*;
import facade.*;
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
public class VendaControle implements Serializable {
    @Inject
    private VendaFacade vendaFacade;
    @Inject
    private PessoaFacade pessoaFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private FormaPagamentoFacade formaPagamentoFacade;
    @Inject
    private MesaFacade mesaFacade;
    private Venda venda;
    private Mesa mesa;
    private ItensVenda itensVenda;
    private FormaPagamento formaPagamento;
    private ConverterGenerico pessoaConverter;
    private ConverterGenerico produtoConverter;
    private ConverterGenerico formaPagamentoConverter;
    private ConverterGenerico mesaConverter;
    private MoneyConverter moneyConverter;
    private Integer numeroParcelas;

    public void addProduto() {
        System.out.println(venda!=null);
        if (itensVenda.getQuantidade() == null || itensVenda.getValor() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            int estoque = itensVenda.getProduto().getEstoque();
            ItensVenda itemTemp = null;
            for (ItensVenda it : venda.getItensVenda()) {
                if (it.getProduto().equals(itensVenda.getProduto())) {
                    itemTemp = it;
                    estoque = (int) (estoque - it.getQuantidade());
                }
            }
            if ((estoque - itensVenda.getQuantidade()) < 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Estoque insuficiente!",
                                "Restam apenas "
                                        + estoque
                                        + " unidades."));
            } else {
                if (itemTemp == null) {
                    itensVenda.setVenda(venda);
                    venda.getItensVenda().add(itensVenda);
                } else {
                    itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVenda.getQuantidade());
                }
                itensVenda = new ItensVenda();
            }
        }
    }

    public void gerarParcelas() {
        if (getNumeroParcelas()> venda.getFormaPagamento().getNumParcelas()){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O número de parcelas digitado é superior ao número de parcelas da forma de pagamento!",
                            "O número máximo permitido é "
                                    + venda.getFormaPagamento().getNumParcelas()
                                    + " parcelas."));

        }else {
            System.out.println(formaPagamento!=null);
            venda.setContasReceber(new ArrayList<ContasReceber>());
            BigDecimal valor = venda.getTotalVenda().divide(new BigDecimal(numeroParcelas), RoundingMode.HALF_EVEN);
            Date dataVen = venda.getDataVenda();
            for (Integer i = 1; i <= numeroParcelas; i++) {
                ContasReceber cr = new ContasReceber();
                venda.setStatusVenda(StatusVenda.FINALIZADA);
                cr.setStatusContasReceber(StatusContasReceber.APAGAR);
                cr.setDataLancamento(venda.getDataVenda());
                cr.setDescricao(venda.getPessoa().getNome());
                cr.setNumParcelas(i.toString() + "/" + numeroParcelas.toString());
                cr.setValor(valor);
                cr.setDataVencimento(dataVen);
                cr.setVenda(venda);
                if (venda.getPessoa() != null) {
                    cr.setPessoa(venda.getPessoa());
                }
                venda.getContasReceber().add(cr);
                //Soma 1 mês no compcimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }
        }
    }

    public void removeProd(ItensVenda it) {
        venda.getItensVenda().remove(it);
    }

    public void atualizaPrecoProduto() {
        itensVenda.setValor(itensVenda.getProduto().getValorVenda());
    }

    public void novo() {
        venda = new Venda();
        venda.setStatusVenda(StatusVenda.ABERTO);
        venda.setItensVenda(new ArrayList<>());
        itensVenda = new ItensVenda();
        numeroParcelas = 1;
        venda.setContasReceber(new ArrayList<ContasReceber>());
    }

    public void novaVendaMesa(Mesa m) {
        if (m.getOcupada()) {
            venda = vendaFacade.getMesaOcupada(m);
            itensVenda = new ItensVenda();
        } else {
            venda = new Venda();
            venda.setStatusVenda(StatusVenda.ABERTO);
            mesa = m;
            venda.setMesa(mesa);
            venda.setItensVenda(new ArrayList<>());
            itensVenda = new ItensVenda();
            numeroParcelas = 1;
            venda.setContasReceber(new ArrayList<ContasReceber>());
        }

    }

    public String tipoMesa(Mesa mesa) {
        if (mesa.getOcupada()) {
            return "mesafechada.jpg";
        }
        return "mesaaberta.png";
    }

    public void salvar() {
       if(venda.getMesa()==null){
           vendaFacade.save(venda);
       }else{
           venda.getMesa().setOcupada(true);
           mesaFacade.salvar(venda.getMesa());
           vendaFacade.save(venda);
       }
    }

    public void DesocuparMesa() {
        if(venda.getMesa()==null){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Essa venda não possui nenhuma mesa ocupada!",
                            null));
        }else{
            venda.getMesa().setOcupada(Boolean.FALSE);
            vendaFacade.salvar(venda);
            mesaFacade.salvar(venda.getMesa());
            venda = new Venda();
        }
    }

    public void excluir(Venda e) {
        vendaFacade.remove(e);
    }

    public void editar(Venda e) {
        venda = e;
    }

    public List<Venda> getLista() {
        return vendaFacade.findAll();
    }

    public List<Pessoa> getListaPessoaFiltrando(String filtro){
        List<Pessoa> listaRetorno = new ArrayList<Pessoa>();
        listaRetorno.addAll(pessoaFacade.listaFiltrando(filtro, "nome"));
        return listaRetorno;
    }

    public List<Produto> getListaProdutosFiltrando(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }

    public List<FormaPagamento> getListaFormaPagamentoFiltrando(String filtro) {
        return formaPagamentoFacade.listaFiltrando(filtro, "nome");
    }

    public List<Mesa> listaMesas() {
        return mesaFacade.findAll();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public void setPessoaConverter(ConverterGenerico pessoaConverter) {
        this.pessoaConverter = pessoaConverter;
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

    public ConverterGenerico getFormaPagamentoConverter() {
        if (formaPagamentoConverter == null) {
            formaPagamentoConverter = new ConverterGenerico(formaPagamentoFacade);
        }
        return formaPagamentoConverter;
    }

    public void setFormaPagamentoConverter(ConverterGenerico formaPagamentoConverter) {
        this.formaPagamentoConverter = formaPagamentoConverter;
    }

    public ConverterGenerico getMesaConverter() {
        if (mesaConverter == null){
            mesaConverter = new ConverterGenerico(mesaFacade);
        }
        return mesaConverter;
    }

    public void setMesaConverter(ConverterGenerico mesaConverter) {
        this.mesaConverter = mesaConverter;
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

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
