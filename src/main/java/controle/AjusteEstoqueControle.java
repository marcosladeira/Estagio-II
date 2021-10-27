package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.ItensAjusteEstoque;
import entidades.AjusteEstoque;
import entidades.Produto;
import facade.AjusteEstoqueFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author marcos-ladeira
 */
@Named
@ViewAccessScoped
public class AjusteEstoqueControle implements Serializable {

    @Inject
    private AjusteEstoqueFacade ajusteEstoqueFacade;
    private AjusteEstoque ajusteEstoque;
    private ItensAjusteEstoque itensAjusteEstoque;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterProduto;
    private MoneyConverter moneyConverter;
    private Produto produto;
    private List<Produto> produtos;


    public void setarProduto(Produto prod) {
        itensAjusteEstoque.setProduto(prod);
//            itensAjusteEstoque.setEstoqueAntual(itensAjusteEstoque.getEstoqueAntual());
//            itensAjusteEstoque.setEstoqueAnterior(cp.getEstoque());
    }

    public void atualizaPrecoProduto() {
        if(getProduto().getEstoque()!=null){
            itensAjusteEstoque.setEstoqueAnterior(itensAjusteEstoque.getProduto().getEstoque());
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }


    public void addItemComposicaoProduto() {

        {
            itensAjusteEstoque.setProduto(produto);
            itensAjusteEstoque.setAjusteEstoque(ajusteEstoque);
            ajusteEstoque.getItensAjusteEstoque().add(itensAjusteEstoque);
        }

        itensAjusteEstoque = new ItensAjusteEstoque();

    }

    public void removerItemAjusteEstoque(ItensAjusteEstoque it) {
        ajusteEstoque.getItensAjusteEstoque().remove(it);
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public ItensAjusteEstoque getItensAjusteEstoque() {
        return itensAjusteEstoque;
    }

    public void setItensAjusteEstoque(ItensAjusteEstoque itensAjusteEstoque) {
        this.itensAjusteEstoque = itensAjusteEstoque;
    }

    public List<Produto> getListaFiltrandoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }


    public void novo() {
        ajusteEstoque = new AjusteEstoque();
        itensAjusteEstoque = new ItensAjusteEstoque();
    }

    public void excluir(AjusteEstoque e) {
        ajusteEstoqueFacade.excluir(e);
    }

    public void editar(AjusteEstoque e) {
        this.ajusteEstoque = e;
    }

    public void salvar() {
        ajusteEstoqueFacade.salvar(ajusteEstoque);
    }

    public List<AjusteEstoque> listaTodos() {
        return ajusteEstoqueFacade.listaTodos();
    }

    public List<Produto> listaTodosProdutos() {
        return produtoFacade.listaTodos();
    }

    public AjusteEstoque getAjusteEstoque() {
        return ajusteEstoque;
    }

    public void setAjusteEstoque(AjusteEstoque ajusteEstoque) {
        this.ajusteEstoque = ajusteEstoque;
    }
}
