package facade;

import entidades.Cliente;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class ClienteFacade extends AbstractFacade<Cliente>{
    @Inject
    private EntityManager em;

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
