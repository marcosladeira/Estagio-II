package facade;

import entidades.ContasReceber;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class ContasReceberFacade extends AbstractFacade<ContasReceber>{
    @Inject
    private EntityManager em;

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }



}
