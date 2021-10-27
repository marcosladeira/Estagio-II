package facade;

import entidades.Usuario;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.Transacional;

@Transacional
public class UsuarioFacade extends AbstractFacade<Usuario>{

    @Inject
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }


    public Usuario validaUsuario(Usuario u) {
        String hql = "from Usuario obj where obj.login = :filtro1 and obj.senha = :filtro2";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro1", u.getLogin());
        q.setParameter("filtro2", u.getSenha());
        if(q.getResultList().isEmpty()){
            return u;
        }else{
            return (Usuario) q.getSingleResult();
        }

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}


