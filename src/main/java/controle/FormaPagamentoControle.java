package controle;

import entidades.FormaPagamento;
import facade.FormaPagamentoFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class FormaPagamentoControle implements Serializable {
    @Inject
    private FormaPagamentoFacade formaPagamentoFacade;
    private FormaPagamento formaPagamento;

    public void novo(){
        formaPagamento = new FormaPagamento();
    }

    public String salvar() {
        try {
            formaPagamentoFacade.save(formaPagamento);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Forma de Pagamento já está cadastrada!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "formapagamentoedita.xhtml";
    }

    public String excluir(FormaPagamento formaPagamento) {
        try {
            formaPagamentoFacade.remove(formaPagamento);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir a Forma de Pagamento", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "formapagamentolista?faces-redirect=true";
    }

    public void editar(FormaPagamento e) {
        formaPagamento = e;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getLista() {
        return formaPagamentoFacade.findAll();
    }

}
