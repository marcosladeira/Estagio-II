package controle;

import entidades.Mesa;

import facade.MesaFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class MesaControle implements Serializable {

    @Inject
    private MesaFacade mesaFacade;
    private Mesa mesa;

    public void novo() {
        mesa = new Mesa();
    }

    public String salvar() {
        try {
        mesa.setOcupada(Boolean.FALSE);
        mesaFacade.save(mesa);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Mesa já está cadastrada!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "mesaedita.xhtml";
    }

    public String excluir(Mesa mesa) {
        try {
            mesaFacade.remove(mesa);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possível excluir a Mesa", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "mesalista?faces-redirect=true";
    }

    public void editar(Mesa e) {
        mesa = e;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Mesa> getLista() {
        return mesaFacade.findAll();
    }

}
