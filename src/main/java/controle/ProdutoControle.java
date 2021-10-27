package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.GrupoProduto;
import entidades.Produto;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProdutoControle implements Serializable {

    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;
    private Produto produto;
    private ConverterGenerico grupoProdutoConverter;
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

    public ConverterGenerico getGrupoProdutoConverter() {
        if(grupoProdutoConverter == null){
            grupoProdutoConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProdutoConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProdutoConverter) {
        this.grupoProdutoConverter = grupoProdutoConverter;
    }

    public void novo(){
        produto = new Produto();
    }

    public String salvar(){
        try {
            produto.setEstoque(0);
            produtoFacade.save(produto);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Produto já está cadastrado!", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "produtoedita.xhtml";
    }

    public String excluir(Produto produto){
        try {
            produtoFacade.remove(produto);
        } catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não é possível excluir o Produto", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "produtolista?faces-redirect=true";
    }

    public void editar(Produto e){
        produto = e;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getLista(){
        return produtoFacade.findAll();
    }

    public List<GrupoProduto> getListaFiltrando(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "nome");
    }

}
