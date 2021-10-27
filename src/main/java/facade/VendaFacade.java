package facade;

import entidades.*;
import util.Transacional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Transacional
public class VendaFacade extends AbstractFacade<Venda> {

    @Inject
    private EntityManager em;

    public VendaFacade(){
        super(Venda.class);
    }

    @Override
    protected EntityManager getEntityManager(){
        return em;
    }

    @Override
    public void save(Venda entity) {
        for(ItensVenda it : entity.getItensVenda()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() - it.getQuantidade()));
            em.merge(p);
        }
        super.save(entity);
    }

    @Override
    public void remove(Venda entity) {
        for(ItensVenda it : entity.getItensVenda()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() + it.getQuantidade()));
            em.merge(p);
        }
        super.remove(entity);
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> listavendas = super.findAll();
        for(Venda v : listavendas){
            v.getItensVenda().size();
            v.getContasReceber().size();
        }
        return listavendas;
    }

    public Venda getMesaOcupada(Mesa m){
        try {
            String hql = "from Venda obj where obj.mesa = :param and obj.statusVenda = :param2";
            Query q = getEntityManager().createQuery(hql);
            q.setParameter("param", m);
            q.setParameter("param2", StatusVenda.FINALIZADA);
            Venda v = (Venda) q.getResultList().get(0);
            return v;
        }catch (Exception e){
            return null;
        }
    }

}
