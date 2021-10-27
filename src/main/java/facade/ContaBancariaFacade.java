package facade;

import entidades.ContaBancaria;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ContaBancariaFacade extends AbstractFacade<ContaBancaria>{

    @Inject
    private EntityManager em;

    public ContaBancariaFacade() {
        super(ContaBancaria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
