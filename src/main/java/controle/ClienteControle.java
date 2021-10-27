package controle;

import entidades.Cliente;
import facade.ClienteFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ClienteControle implements Serializable{

    @Inject
    private ClienteFacade clienteFacade;
    private Cliente cli;

    public void novo() {
        cli = new Cliente();
    }

    public String salvar() {
        try {
            clienteFacade.save(cli);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Cliente já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "clienteedita.xhtml";
    }

    public String excluir(Cliente cliente) {
        try {
            clienteFacade.remove(cliente);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir o Cliente", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "clientelista?faces-redirect=true";
    }

    public void editar(Cliente e) {
        cli = e;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public List<Cliente> getLista() {
        return clienteFacade.findAll();
    }
}
