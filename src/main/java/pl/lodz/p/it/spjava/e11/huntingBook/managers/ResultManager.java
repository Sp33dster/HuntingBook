package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntResultFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ResultManager extends AbstractManager implements SessionSynchronization{

    @Inject
    HuntResultFacade resultFacade;

    public void addHuntResult(HuntResult result) throws AppBaseException {
        resultFacade.create(result);
        
    }

}
