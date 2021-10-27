/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.PessoaFisica;
import facade.CidadeFacade;
import facade.PessoaFisicaFacade;
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
public class PessoaFisicaControle implements Serializable {

    @Inject
    private PessoaFisicaFacade pessoaFisicaFacade;
    private PessoaFisica pessoaFisica;
    @Inject
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;


    public String salvar(){
        try{
        pessoaFisicaFacade.save(pessoaFisica);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Pessoa Física já está cadastrada!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "pessoafisicaedita.xhtml";
    }

    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if(converterCidade == null){
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }


    public void novo(){
        pessoaFisica = new PessoaFisica();
    }

    public void excluir(PessoaFisica e){
        pessoaFisicaFacade.remove(e);
    }

    public void editar(PessoaFisica e){
        this.pessoaFisica = e;
    }

    public List<PessoaFisica> listaTodos() {
        return pessoaFisicaFacade.findAll();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
