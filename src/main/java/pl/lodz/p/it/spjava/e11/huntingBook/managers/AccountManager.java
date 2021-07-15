package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.Stateful;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateful
public class AccountManager extends AbstractManager{
    
    @Inject
    private AccountFacade accountFacade;
    
    public void createAccount(Hunter hunter){
        accountFacade.create(hunter);
    }
}
