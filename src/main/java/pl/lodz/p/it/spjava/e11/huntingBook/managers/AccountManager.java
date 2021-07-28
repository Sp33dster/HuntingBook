package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HunterFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountManager extends AbstractManager {

    @Inject
    private HunterFacade hunterFacade;

    @Inject
    private AccountFacade accountFacade;

    public void createAccount(Hunter hunter) {
        hunterFacade.create(hunter);
    }

}
