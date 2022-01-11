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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.lodz.p.it.spjava.e11.huntingBook.ejb.interceptor.LoggingInterceptor;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
@LocalBean
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    public Account findLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get("login"), login));
        TypedQuery<Account> tq = em.createQuery(query);

        return tq.getSingleResult();
    }

    public List<Account> matchAccounts(String login, String name, String surname, String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        Predicate criteria = cb.conjunction();
        if (null != login && !(login.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get("login"), '%' + login + '%'));
        }
        if (null != name && !(name.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get("name"), '%' + name + '%'));
        }
        if (null != surname && !(surname.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get("surname"), '%' + surname + '%'));
        }
        if (null != email && !(email.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get("email"), '%' + email + '%'));
        }

        query = query.where(criteria);
        TypedQuery<Account> tq = em.createQuery(query);
        return tq.getResultList();
    }

    public Hunter findHunterLogin(String myLogin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hunter> query = cb.createQuery(Hunter.class);
        Root<Hunter> from = query.from(Hunter.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get("login"), myLogin));
        TypedQuery<Hunter> tq = em.createQuery(query);

        return tq.getSingleResult();
    }

    public List<Account> findActiveHunters() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);

        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.equal(from.get("type"), AccountType.HUNTER);
        predicates[1] = cb.equal(from.get("isActive"), Boolean.TRUE);
        query = query.where(predicates);

        TypedQuery<Account> tq = em.createQuery(query);

        return tq.getResultList();
    }

    public List<Account> findHunters() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get("type"), AccountType.HUNTER));

        TypedQuery<Account> tq = em.createQuery(query);

        return tq.getResultList();
    }

}
