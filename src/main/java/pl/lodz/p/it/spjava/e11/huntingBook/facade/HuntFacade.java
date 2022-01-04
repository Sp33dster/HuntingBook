package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.lodz.p.it.spjava.e11.huntingBook.ejb.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
@LocalBean
public class HuntFacade extends AbstractFacade<Hunt> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HuntFacade() {
        super(Hunt.class);
    }

    public List<Hunt> getMyHunt(Hunter hunterId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hunt> query = cb.createQuery(Hunt.class);
        Root<Hunt> hunt = query.from(Hunt.class);
        query = query.select(hunt);
        query = query.where(cb.equal(hunt.get("hunterId"), hunterId));

        TypedQuery<Hunt> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    public Hunt getHunt(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hunt> query = cb.createQuery(Hunt.class);
        Root<Hunt> hunt = query.from(Hunt.class);
        query = query.select(hunt);
        query = query.where(cb.equal(hunt.get("id"), id));
        
        TypedQuery<Hunt> typedQuery = em.createQuery(query);
        
        return typedQuery.getSingleResult();
    
    }

}
