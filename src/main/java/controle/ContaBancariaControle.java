package controle;

import converter.MoneyConverter;
import entidades.ContaBancaria;
import facade.ContaBancariaFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@SessionScoped
public class ContaBancariaControle implements Serializable {

    @Inject
    private ContaBancariaFacade contaBancariaFacade;
    private ContaBancaria contaBancaria;
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public void novo(){
        contaBancaria = new ContaBancaria();
    }

    public String excluir(ContaBancaria contaBancaria){
        try {
            contaBancariaFacade.remove(contaBancaria);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir a Conta Bancária", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "contabancarialista?faces-redirect=true";
    }

    public void editar(ContaBancaria e){
        this.contaBancaria = e;
    }

    public String salvar() {
        try {
            contaBancaria.setDebito(new BigDecimal("0"));
            contaBancaria.setCredito(new BigDecimal("0"));
            contaBancariaFacade.save(contaBancaria);
        } catch (Exception e){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Conta Bancária já está cadastrada!", e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        return "contabancariaedita.xhtml";
    }

    public List<ContaBancaria> getLista() {
        return contaBancariaFacade.findAll();
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
}
