package facade;

import entidades.Compra;
import entidades.ItensCompra;
import entidades.Produto;
import util.Transacional;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Transacional
public class CompraFacade extends AbstractFacade<Compra>{

    @Inject
    private EntityManager em;

    public CompraFacade(){
        super(Compra.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void save(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() + it.getQuantidade()));
            em.merge(p);
        }
        super.save(entity);
    }

    @Override
    public void remove(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() - it.getQuantidade()));
            em.merge(p);
        }
        super.remove(entity);
    }

    @Override
    public List<Compra> findAll() {
        List<Compra> listacompras = super.findAll();
        for(Compra v : listacompras){
            v.getItensCompra().size();
            v.getContasPagar().size();
        }
        return listacompras;
    }
}
