package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.ResultFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Result;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ResultManager extends AbstractManager {

    @Inject
    ResultFacade resultFacade;

    public void addHuntResult(Result result) throws AppBaseException {
        resultFacade.create(result);
        
    }

}
