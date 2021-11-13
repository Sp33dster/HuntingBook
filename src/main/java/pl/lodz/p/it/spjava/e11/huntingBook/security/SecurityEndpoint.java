package pl.lodz.p.it.spjava.e11.huntingBook.security;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;


@Stateless
@Named
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SecurityEndpoint {
    
    @Inject
    private AccountFacade accountFacade;
    
    public Account findByName(String loginName){
        return accountFacade.findLogin(loginName);
    }
}
