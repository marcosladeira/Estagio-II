package facade;

import entidades.PessoaJuridica;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class PessoaJuridicaFacade extends AbstractFacade<PessoaJuridica>{

    @Inject
    private EntityManager em;

    public PessoaJuridicaFacade(){
        super(PessoaJuridica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
