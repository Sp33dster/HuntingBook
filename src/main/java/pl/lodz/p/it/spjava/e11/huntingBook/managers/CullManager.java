package pl.lodz.p.it.spjava.e11.huntingBook.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.CullFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CullManager extends AbstractManager {

    @Inject
    CullFacade cullFacade;
    
    public void addCull(Cull cull) throws AppBaseException {
        cullFacade.create(cull);
    }

}
