package pl.lodz.p.it.spjava.e11.huntingBook.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;

@Stateless
public class CullFacade extends AbstractFacade<Cull> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CullFacade() {
        super(Cull.class);
    }

}
