/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Cidade;
import entidades.Funcionario;
import facade.CidadeFacade;
import facade.FuncionarioFacade;
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
public class FuncionarioControle implements Serializable {

    @Inject
    private FuncionarioFacade funcionarioFacade;
    @Inject
    private CidadeFacade cidadeFacade;
    private Funcionario funcionario;
    private ConverterGenerico converterCidade;
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public String salvar(){
        try{
        funcionarioFacade.save(funcionario);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Funcionário já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "funcionarioedita.xhtml";
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
        funcionario = new Funcionario();
    }

    public void excluir(Funcionario e){
        funcionarioFacade.remove(e);
    }

    public void editar(Funcionario e){
        this.funcionario = e;
    }

    public List<Funcionario> listaTodos() {
        return funcionarioFacade.findAll();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
