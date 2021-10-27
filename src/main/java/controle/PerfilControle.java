package controle;

import entidades.Perfil;
import entidades.Permissoes;
import facade.PerfilFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PerfilControle implements Serializable {

    @Inject
    private PerfilFacade perfilFacade;
    private Perfil per;
    private Permissoes permissoes;

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public void adicionar(){
        permissoes.setPerfil(per);
        per.getPermissoes().add(permissoes);
        permissoes = new Permissoes();
    }

    public void novo() {
        per = new Perfil();
        permissoes = new Permissoes();
        per.setPermissoes(new ArrayList<Permissoes>());
    }

    public String salvar() {
        try {
            perfilFacade.save(per);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Perfil já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "perfiledita.xhtml";
    }
    public String excluir(Perfil perfil) {
        try{
        perfilFacade.remove(perfil);
    } catch (Exception e){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir o Perfil", e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        return "perfillista?faces-redirect=true";
    }

    public void editar(Perfil e) {
        per = e;
    }

    public Perfil getPer() {
        if(per == null){
            per = new Perfil();
        }
        return per;
    }

    public void setPer(Perfil per) {
        this.per = per;
    }

    public List<Perfil> getLista() {
        return perfilFacade.findAll();
    }

}
