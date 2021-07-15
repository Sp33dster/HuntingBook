package pl.lodz.p.it.spjava.e11.huntingBook.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AccountFacade extends AbstractFacade<Account>{
    
    @PersistenceContext(unitName = "HuntingBook_PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public AccountFacade(){
        super(Account.class);
    }
    
    
    
}
