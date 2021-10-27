
package util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtil {

    //Criando o entityManager, static final, cria uma vez só.
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnibarPU");

    //fabrica de entity managed
    @Produces
    //O objeto do entityManager fica vivo na memoria somente durante a requisição.
    @RequestScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //Disposes, ao fim da requisiÇão o objeto é fechado.
    public void close(@Disposes EntityManager manager) {
        manager.close();
    }
}
