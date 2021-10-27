/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.GrupoProduto;
import facade.GrupoProdutoFacade;
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
public class GrupoProdutoControle implements Serializable {

    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;
    private GrupoProduto gp;
    private ConverterGenerico grupoProdutoConverter;

    public ConverterGenerico getGrupoProdutoConverter() {
        if(grupoProdutoConverter == null){
            grupoProdutoConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProdutoConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProdutoConverter) {
        this.grupoProdutoConverter = grupoProdutoConverter;
    }


    public void novo() {
        gp = new GrupoProduto();
    }

    public String salvar() {
        try{
        grupoProdutoFacade.save(gp);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Grupo de Produto já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "grupoprodutoedita.xhtml";
    }

    public String excluir(GrupoProduto gp) {
        try {
            grupoProdutoFacade.remove(gp);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possivel excluir o Grupo de Produto", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "grupoprodutolista?faces-redirect=true";
    }

    public void editar(GrupoProduto gp) {
        this.gp = gp;
    }

    public GrupoProduto getGp() {
        if(gp == null){

               gp = new GrupoProduto();
        }
        return gp;
    }

    public void setGp(GrupoProduto gp) {
        this.gp = gp;
    }

    public List<GrupoProduto> getLista() {
        return grupoProdutoFacade.findAll();
    }
    public List<GrupoProduto> getListaGrupoProdutoFiltrando(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "nome");
    }
}
