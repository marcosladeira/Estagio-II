/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Funcionario;
import entidades.Perfil;
import entidades.Usuario;
import facade.FuncionarioFacade;
import facade.PerfilFacade;
import facade.UsuarioFacade;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marcos-ladeira
 */
@Named
@ViewAccessScoped
public class UsuarioControle implements Serializable {

    @Inject
    private UsuarioFacade usuarioFacade;
    @Inject
    private PerfilFacade perfilFacade;
    private Usuario usuario;
    private ConverterGenerico converterFuncionario;
    private ConverterGenerico converterPerfil;
    @Inject
    private FuncionarioFacade funcionarioFacade;

    public List<Perfil> listaPerfis(){
        return perfilFacade.findAll();
    }

    public ConverterGenerico getConverterPerfil() {
        if(converterPerfil == null){
            converterPerfil = new ConverterGenerico(perfilFacade);
        }
        return converterPerfil;
    }

    public void setConverterPerfil(ConverterGenerico converterPerfil) {
        this.converterPerfil = converterPerfil;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar() {
        if (!usuario.getSenha().equals(usuario.getConfirmasenha())) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "As senhas são diferente!",
                            null));
        } else {
            usuarioFacade.save(usuario);
        }
    }

    public String excluir(Usuario usuario) {
        try {
            usuarioFacade.remove(usuario);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possível excluir o Usuário", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "usuariolista?faces-redirect=true";
    }

    public ConverterGenerico getConverterFuncionario() {
        if(converterFuncionario == null){
            converterFuncionario = new ConverterGenerico(funcionarioFacade);
        }
        return converterFuncionario;
    }

    public void setConverterFuncionario(ConverterGenerico converterFuncionario) {
        this.converterFuncionario = converterFuncionario;
    }

    public void editar(Usuario e) {
        usuario = e;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return usuarioFacade.findAll();
    }

    public List<Funcionario> filtrandoFuncionario(String parte){
        return funcionarioFacade.listaFiltrando(parte, "nome");
    }
}
