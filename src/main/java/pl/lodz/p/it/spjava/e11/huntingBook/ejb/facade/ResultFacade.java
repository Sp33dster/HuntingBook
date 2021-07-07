package pl.lodz.p.it.spjava.e11.huntingBook.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Result;

@Stateless
public class ResultFacade extends AbstractFacade<Result> {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultFacade() {
        super(Result.class);
    }

}
