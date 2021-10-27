package facade;

import entidades.AjusteEstoque;
import entidades.ItensAjusteEstoque;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import entidades.Produto;
import util.Transacional;

@Transacional
public class AjusteEstoqueFacade extends AbstractFacade<AjusteEstoque> {

    @Inject
    private EntityManager em;

    public AjusteEstoqueFacade() {
        super(AjusteEstoque.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvar(AjusteEstoque aj) {
        for (ItensAjusteEstoque it : aj.getItensAjusteEstoque()) {
            Produto p = it.getProduto();
            it.setEstoqueAnterior(p.getEstoque());
            p.setEstoque(it.getEstoqueAtual());
            em.merge(p);
        }
        super.salvar(aj);
    }

    @Override
    public void excluir(AjusteEstoque aj) {
        for (ItensAjusteEstoque it : aj.getItensAjusteEstoque()) {
            Produto p = it.getProduto();
            p.setEstoque(it.getEstoqueAnterior());
            em.merge(p);
        }
        super.excluir(aj);
    }
}
