package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.model.MasterOfTheHunter;

@Stateless
public class MasterOfTheHunterFacade extends AbstractFacade<MasterOfTheHunter> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterOfTheHunterFacade() {
        super(MasterOfTheHunter.class);
    }
    
}
