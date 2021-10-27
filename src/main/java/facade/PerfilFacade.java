/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Perfil;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

/**
 *
 * @author marcos-ladeira
 */
@Transacional
public class PerfilFacade extends AbstractFacade<Perfil>{

    @Inject
    private EntityManager em;

    public PerfilFacade() {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
