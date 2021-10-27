package facade;

import entidades.ContasPagar;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class ContasPagarFacade extends AbstractFacade<ContasPagar>{
    @Inject
    private EntityManager em;

    public ContasPagarFacade() {
        super(ContasPagar.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
