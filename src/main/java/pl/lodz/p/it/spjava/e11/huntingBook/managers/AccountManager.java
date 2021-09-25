package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.MasterOfTheHunter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountManager extends AbstractManager {

    @Inject
    private AccountFacade accountFacade;

    public void createAccount(Hunter hunter) throws AppBaseException {
        accountFacade.create(hunter);
    }

    public void createAccount(Administrator administrator) throws AppBaseException {
        accountFacade.create(administrator);
    }

    public void createAccount(MasterOfTheHunter masterOfTheHunter) throws AppBaseException {
        accountFacade.create(masterOfTheHunter);
    }

}
