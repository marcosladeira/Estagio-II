package controle;

import entidades.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class EstadoControle implements Serializable {

    @Inject
    private EstadoFacade estadoFacade;
    private Estado est;

    public void novo() {
        est = new Estado();
    }

    public String salvar() {
        try {
            estadoFacade.save(est);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Estado já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "estadoedita.xhtml";
    }

    public String excluir(Estado estado) {
        try {
            estadoFacade.remove(estado);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir o Estado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "estadolista?faces-redirect=true";
    }

    public void editar(Estado e) {
        est = e;
    }

    public Estado getEst() {
        return est;
    }

    public void setEst(Estado est) {
        this.est = est;
    }

    public List<Estado> getLista() {
        return estadoFacade.findAll();
    }

}
