package facade;


import entidades.GrupoProduto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class GrupoProdutoFacade extends AbstractFacade<GrupoProduto>{

    @Inject
    private EntityManager em;

    public GrupoProdutoFacade() {
        super(GrupoProduto.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}