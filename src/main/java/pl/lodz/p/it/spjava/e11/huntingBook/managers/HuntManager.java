package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HuntManager extends AbstractManager {

    @Inject
    HuntFacade huntFacade;
    
    @Inject
    AccountEndpoint accountEndpoint;

    public void addNewHunt(Hunt hunt) throws AppBaseException{
        Hunter myHunterAccount = accountEndpoint.getMyHunterAccount();
        hunt.setHunterId(myHunterAccount);
        
        huntFacade.create(hunt);
        
      }
    
    

}
