package facade;

import entidades.Pessoa;
import entidades.PessoaFisica;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class PessoaFacade extends AbstractFacade<Pessoa>{

    @Inject
    private EntityManager em;

    public PessoaFacade(){
        super(Pessoa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
