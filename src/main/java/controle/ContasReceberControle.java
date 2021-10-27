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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class ContasReceberControle implements Serializable {

    @Inject
    private ContasReceberFacade contasReceberFacade;
    @Inject
    private PessoaFacade pessoaFacade;
    @Inject
    private ContaBancariaFacade contaBancariaFacade;
    private ContasReceber contasReceber;
    private BaixaContasReceber baixaContasReceber;
    private ConverterGenerico pessoaConverter;
    private ConverterGenerico contaBancariaConverter;
    private MoneyConverter moneyConverter;

    public void novo(){
        contasReceber =  new ContasReceber();
        contasReceber.setStatusContasReceber(StatusContasReceber.APAGAR);
    }

    public void baixar(){
        if(contasReceber.getValor().compareTo(contasReceber.getValorPago().add(baixaContasReceber.getValor()))>=0){
            baixaContasReceber.setContasReceber(contasReceber);
            contasReceber.getBaixaContasReceber().add(baixaContasReceber);
            contasReceber.getContaBancaria().setCredito(baixaContasReceber.getValor().add(contasReceber.getContaBancaria().getCredito()));
            contaBancariaFacade.save(contasReceber.getContaBancaria());
            if (Objects.equals(contasReceber.getValor(), contasReceber.getValorPago())){
                contasReceber.setStatusContasReceber(StatusContasReceber.PAGO);
            }else{
                contasReceber.setStatusContasReceber(StatusContasReceber.PAGOPARCIALMENTE);
            }
            baixaContasReceber = new BaixaContasReceber();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a receber!",
                            null));
        }
    }

    public void novaBaixa(ContasReceber cr){
        this.contasReceber = cr;
        baixaContasReceber = new BaixaContasReceber();
    }

    public void removerBaixa(BaixaContasReceber b){
        contasReceber.getBaixaContasReceber().remove(b);
        if (Objects.equals(contasReceber.getValor(), contasReceber.getValorReceber())){
            contasReceber.setStatusContasReceber(StatusContasReceber.APAGAR);
        }else{
            contasReceber.setStatusContasReceber(StatusContasReceber.PAGOPARCIALMENTE);
        }    }

    public String getCorValor(){
        if(Objects.equals(contasReceber.getValorPago(), contasReceber.getValor())){
            return "green";
        }else{
            return "red";
        }
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


    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }


    public ContasReceberFacade getContasReceberFacade() {
        return contasReceberFacade;
    }

    public void setContasReceberFacade(ContasReceberFacade contasReceberFacade) {
        this.contasReceberFacade = contasReceberFacade;
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

    public ConverterGenerico getContaBancariaConverter() {
        if (contaBancariaConverter == null) {
            contaBancariaConverter = new ConverterGenerico(contaBancariaFacade);
        }
        return contaBancariaConverter;
    }

    public void setContaBancariaConverter(ConverterGenerico contaBancariaConverter) {
        this.contaBancariaConverter = contaBancariaConverter;
    }

    public List<Pessoa> getListaPessoaFiltrando(String filtro){
        List<Pessoa> listaRetorno = new ArrayList<Pessoa>();
        listaRetorno.addAll(pessoaFacade.listaFiltrando(filtro, "nome"));
        return listaRetorno;
    }

    public void excluir(ContasReceber e) {
        contasReceberFacade.remove(e);
    }

    public void salvar() {
        contasReceber.setNumParcelas("1");
        contasReceberFacade.save(contasReceber);
    }

    public void editar(ContasReceber e) {
        contasReceber = e;
    }

    public List<ContasReceber> getLista() {
        return contasReceberFacade.findAll();
    }

    public List<ContaBancaria> getListaContaBancariaFiltrando(String filtro) {
        return contaBancariaFacade.listaFiltrando(filtro, "Nomebanco");
    }
}
