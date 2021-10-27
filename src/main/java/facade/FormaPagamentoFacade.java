package facade;

import entidades.FormaPagamento;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Transacional
public class FormaPagamentoFacade extends AbstractFacade<FormaPagamento> {

    @Inject
    private EntityManager em;

    public FormaPagamentoFacade(){
        super(FormaPagamento.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
