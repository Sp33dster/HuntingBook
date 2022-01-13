package pl.lodz.p.it.spjava.e11.huntingBook.facade;

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
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
@LocalBean
public class CullFacade extends AbstractFacade<Cull> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CullFacade() {
        super(Cull.class);
    }

    public Cull getMyCull(Hunter hunterId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cull> query = cb.createQuery(Cull.class);
        Root<Cull> cull = query.from(Cull.class);
        query = query.select(cull);
        query = query.where(cb.equal(cull.get("hunter_id"), hunterId));

        TypedQuery<Cull> typedQuery = em.createQuery(query);

        return typedQuery.getSingleResult();
    }
}
