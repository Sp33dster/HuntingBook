package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.ejb.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;

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
    
}
