package facade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractFacade<T>  implements Serializable{

    private Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public AbstractFacade(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public T buscar(Long id){
        return getEntityManager().find(entityClass, id);
    }
    public void remover(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }


    public void salvar(T objeto){
        getEntityManager().merge(objeto);
    }

    public void excluir(T objeto){
        getEntityManager().remove(getEntityManager().merge(objeto));
    }
    public void save(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }


    public List<T> listaTodos(){
        Query q = getEntityManager().createQuery("from "+entityClass.getSimpleName());
        return q.getResultList();
    }
    public List<T> findAll() {
        Query q=getEntityManager().createQuery("from "+entityClass.getSimpleName());
        return q.getResultList();
    }

    public List<T> listaFiltrando(String filtro, String... atributos) {
        String hql = "from " + entityClass.getSimpleName() + " obj where ";
        for (String atributo : atributos) {
            hql += "lower(obj." + atributo + ") like :filtro OR ";
        }
        hql = hql.substring(0, hql.length() - 3);
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        return q.getResultList();
    }


}
