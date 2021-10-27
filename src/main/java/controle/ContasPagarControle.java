package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.*;
import facade.ContaBancariaFacade;
import facade.ContasPagarFacade;
import facade.PessoaJuridicaFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class ContasPagarControle implements Serializable {

    @Inject
    private ContasPagarFacade contasPagarFacade;
    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @Inject
    private ContaBancariaFacade contaBancariaFacade;
    private ContasPagar contasPagar;
    private ConverterGenerico pessoaJuridicaConverter;
    private ConverterGenerico contaBancariaConverter;
    private BaixaContasPagar baixaContasPagar;
    private MoneyConverter moneyConverter;

    public void baixar(){
        if(contasPagar.getValor().compareTo(contasPagar.getValorPago().add(baixaContasPagar.getValor()))>=0){
            if (contasPagar.getContaBancaria().getSaldoConta().compareTo(baixaContasPagar.getValor())<0){
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "A conta bancária selecionada não possui saldo suficiente para baixar essa conta a pagar!",
                                null));
            }else{
                baixaContasPagar.setContasPagar(contasPagar);
                contasPagar.getBaixaContasPagar().add(baixaContasPagar);
                contasPagar.getContaBancaria().setDebito(baixaContasPagar.getValor().add(contasPagar.getContaBancaria().getDebito()));
                contaBancariaFacade.save(contasPagar.getContaBancaria());
                if (Objects.equals(contasPagar.getValor(), contasPagar.getValorPago())){
                    contasPagar.setStatusContasPagar(StatusContasPagar.PAGO);
                }else{
                    contasPagar.setStatusContasPagar(StatusContasPagar.PAGOPARCIALMENTE);
                }
                baixaContasPagar = new BaixaContasPagar();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a pagar!",
                            null));
        }
    }

    public void novo(){
        contasPagar =  new ContasPagar();
        contasPagar.setStatusContasPagar(StatusContasPagar.APAGAR);
    }

    public void salvar() {
        contasPagar.setNumParcelas("1");
        contasPagarFacade.save(contasPagar);
    }

    public void novaBaixa(ContasPagar cp){
        this.contasPagar = cp;
        baixaContasPagar = new BaixaContasPagar();
    }

    public void removerBaixa(BaixaContasPagar b){
        contasPagar.getBaixaContasPagar().remove(b);
        if (Objects.equals(contasPagar.getValor(), contasPagar.getValorReceber())){
            contasPagar.setStatusContasPagar(StatusContasPagar.APAGAR);
        }else{
            contasPagar.setStatusContasPagar(StatusContasPagar.PAGOPARCIALMENTE);
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

    public ConverterGenerico getContaBancariaConverter() {
        if (contaBancariaConverter == null) {
            contaBancariaConverter = new ConverterGenerico(contaBancariaFacade);
        }
        return contaBancariaConverter;
    }

    public void setContaBancariaConverter(ConverterGenerico contaBancariaConverter) {
        this.contaBancariaConverter = contaBancariaConverter;
    }

    public String getCorValor(){
        if(Objects.equals(contasPagar.getValorPago(), contasPagar.getValor())){
            return "green";
        }else{
            return "red";
        }
    }

    public void excluir(ContasPagar e) {
        contasPagarFacade.remove(e);
    }

    public void editar(ContasPagar e) {
        contasPagar = e;
    }

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }


    public BaixaContasPagar getBaixaContasPagar() {
        return baixaContasPagar;
    }

    public void setBaixaContasPagar(BaixaContasPagar baixaContasPagar) {
        this.baixaContasPagar = baixaContasPagar;
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public ContasPagarFacade getContasPagarFacade() {
        return contasPagarFacade;
    }

    public void setContasPagarFacade(ContasPagarFacade contasPagarFacade) {
        this.contasPagarFacade = contasPagarFacade;
    }

    public PessoaJuridicaFacade getPessoaJuridicaFacade() {
        return pessoaJuridicaFacade;
    }

    public void setPessoaJuridicaFacade(PessoaJuridicaFacade pessoaJuridicaFacade) {
        this.pessoaJuridicaFacade = pessoaJuridicaFacade;
    }

    public List<ContasPagar> getLista() {
        return contasPagarFacade.findAll();
    }

    public List<PessoaJuridica> getListaPessoaJuridicaFiltrando(String filtro) {
        return pessoaJuridicaFacade.listaFiltrando(filtro, "nome");
    }

    public List<ContaBancaria> getListaContaBancariaFiltrando(String filtro) {
        return contaBancariaFacade.listaFiltrando(filtro, "Nomebanco");
    }
}
