package facade;


import entidades.Mesa;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class MesaFacade extends AbstractFacade<Mesa>{

    @Inject
    private EntityManager em;

    public MesaFacade() {
        super(Mesa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
