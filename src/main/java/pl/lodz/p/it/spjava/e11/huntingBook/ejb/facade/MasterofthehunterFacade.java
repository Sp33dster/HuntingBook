package pl.lodz.p.it.spjava.e11.huntingBook.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Masterofthehunter;

@Stateless
public class MasterofthehunterFacade extends AbstractFacade<Masterofthehunter> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterofthehunterFacade() {
        super(Masterofthehunter.class);
    }

}
