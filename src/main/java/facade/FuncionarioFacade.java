package facade;

import entidades.Funcionario;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class FuncionarioFacade extends AbstractFacade<Funcionario> {

    @Inject
    private EntityManager em;

    public FuncionarioFacade(){
        super(Funcionario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
