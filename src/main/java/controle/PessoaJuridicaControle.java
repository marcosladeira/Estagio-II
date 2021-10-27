/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.PessoaJuridica;
import facade.CidadeFacade;
import facade.PessoaJuridicaFacade;
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
public class PessoaJuridicaControle implements Serializable {

    @Inject
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    private PessoaJuridica pessoaJuridica;
    @Inject
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;

    public String salvar() {
        try{
        pessoaJuridicaFacade.save(pessoaJuridica);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Esta Pessoa Jurídica já está cadastrada!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "pessoajuridicaedita.xhtml";
    }

    public List<Cidade> listaFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }

    public void novo() {
        pessoaJuridica = new PessoaJuridica();
    }

    public void excluir(PessoaJuridica e) {
        pessoaJuridicaFacade.remove(e);
    }

    public void editar(PessoaJuridica e) {
        this.pessoaJuridica = e;
    }

    public List<PessoaJuridica> listaTodos() {
        return pessoaJuridicaFacade.findAll();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
}
