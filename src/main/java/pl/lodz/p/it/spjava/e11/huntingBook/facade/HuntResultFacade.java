package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult_;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@LocalBean
public class HuntResultFacade extends AbstractFacade<HuntResult> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HuntResultFacade() {
        super(HuntResult.class);
    }

    public Long countAnimal(AnimalType animal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HuntResult> result = cq.from(HuntResult.class);

        
        cq.select(cb.count(result)).where(cb.equal(result.get(HuntResult_.animalType), animal));
        
        
        return em.createQuery(cq).getSingleResult();
    }

}
