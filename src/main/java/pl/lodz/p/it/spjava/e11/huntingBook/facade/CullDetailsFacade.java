package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import java.util.List;
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
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.CullDetails;
import pl.lodz.p.it.spjava.e11.huntingBook.model.CullDetails_;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@LocalBean
public class CullDetailsFacade extends AbstractFacade<CullDetails> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CullDetailsFacade() {
        super(CullDetails.class);
    }

    public List<CullDetails> getCullDetails(Cull cullId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CullDetails> query = cb.createQuery(CullDetails.class);
        Root<CullDetails> cullDetails = query.from(CullDetails.class);
        query = query.select(cullDetails);
        query = query.where(cb.equal(cullDetails.get(CullDetails_.cullId), cullId));

        TypedQuery<CullDetails> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();

    }

    public Long countAnimal(AnimalType animal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CullDetails> cullDetails = cq.from(CullDetails.class);

        cq.select(cb.count(cq.from(CullDetails.class)));
        cq.where(cb.equal(cullDetails.get(CullDetails_.animal), animal));
        TypedQuery<Long> typedQuery = em.createQuery(cq);
        return em.createQuery(cq).getSingleResult();
    }

}
