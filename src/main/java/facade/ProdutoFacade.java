package facade;

import entidades.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ProdutoFacade extends AbstractFacade<Produto>{

    @Inject
    private EntityManager em;

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
