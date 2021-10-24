package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.CullException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.CullFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.CullManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.CullDetails;


@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CullEndpoint {
    
    @Inject
    CullManager cullManager;
    
    @Inject
    CullFacade cullFacade;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;
    
    public void addCull(CullDTO cullDTO) throws AppBaseException {
        
        Cull cull = new Cull();

        cull.setStartDate(cullDTO.getStartDate());
        cull.setEndDate(cullDTO.getEndDate());
        for (CullDetailsDTO cullDetailDTO : cullDTO.getCullDetails()) {
           CullDetails  cullDetails = new CullDetails();
           cullDetails.setAnimal(cullDetailDTO.getAnimal());
           cullDetails.setQuantity(cullDetailDTO.getQuantity());
           
           cull.getCullDetails().add(cullDetails);
        }


        boolean rollbackTX;
        int retryTXCounter = txRetryLimit;

        do {
            try {
                cullManager.addCull(cull);
                rollbackTX = cullManager.isLastTransactionRollback();
            } catch (AppBaseException | EJBTransactionRolledbackException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Próba " + retryTXCounter
                        + " wykonania metody biznesowej zakończona wyjątkiem klasy:"
                        + ex.getClass().getName());
                rollbackTX = true;
            }

        } while (rollbackTX && --retryTXCounter > 0);

        if (rollbackTX && retryTXCounter == 0) {
            throw CullException.createCullExceptionWithTxRetryRollback();
        }
    }
            
            
    
    
}
