/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Estado;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marcos-ladeira
 */
@Named
@SessionScoped
public class CidadeControle implements Serializable{
    @Inject
    private CidadeFacade cidadeFacade;
    @Inject
    private EstadoFacade estadoFacade;
    private Cidade cid;
    private ConverterGenerico estadoConverter;

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }

    public void novo(){
        cid = new Cidade();
    }

    public String salvar(){
        try {
            cidadeFacade.save(cid);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Cidade já está cadastrada!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "cidadeedita.xhtml";    }

    public String excluir(Cidade cidade){
        try {
            cidadeFacade.remove(cidade);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir a Cidade", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "cidadelista?faces-redirect=true";
    }

    public void editar(Cidade e){
        cid = e;
    }

    public Cidade getCid() {
        if(cid == null){

               cid = new Cidade();
        }
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }

    public List<Cidade> getLista(){
        return cidadeFacade.findAll();
    }

    public List<Estado> getListaEstadosFiltrando(String filtro){
        return estadoFacade.listaFiltrando(filtro, "nome", "sigla");
    }


}
