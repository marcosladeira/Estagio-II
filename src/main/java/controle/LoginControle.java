/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Permissoes;
import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author marcos-ladeira
 */
@Named
@SessionScoped
public class LoginControle implements Serializable {

    @Inject
    private UsuarioFacade usuarioFacade;
    private Usuario usuario = new Usuario();

    public Boolean validaMenu(String menu){
        for (Permissoes p : usuario.getPerfil().getPermissoes()) {
            if(menu.equals(p.getNome())){
                return true;
            } else {
            }
        }
        return false;
    }

    public String logar(){
        usuario = usuarioFacade.validaUsuario(usuario);
        if(usuario.getPerfil() != null){
            return "index.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Usuário e/ou senha inválidos!",
                            null));
            return "login.xhtml";
        }
    }
    public String logoff() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
