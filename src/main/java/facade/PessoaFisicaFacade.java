package facade;

import entidades.PessoaFisica;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class PessoaFisicaFacade extends AbstractFacade<PessoaFisica>{

    @Inject
    private EntityManager em;

    public PessoaFisicaFacade(){
        super(PessoaFisica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
