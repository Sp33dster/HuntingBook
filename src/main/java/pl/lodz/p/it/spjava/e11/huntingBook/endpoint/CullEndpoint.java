package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.CullException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.CullFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.CullManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.CullDetails;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CullEndpoint {

    @Inject
    CullManager cullManager;

    @Inject
    CullFacade cullFacade;

    @Inject
    AccountFacade accountFacade;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    public void addCull(CullDTO cullDTO, List<CullDetailsDTO> cullDetailsDTO, AccountDTO hunterDTO) throws AppBaseException {
        Hunter hunter = (Hunter) accountFacade.find(hunterDTO.getId());
        Cull cull = new Cull();
        List<CullDetails> cullDetailsList = new ArrayList<>();
        
        cull.setHunterId(hunter);
        cull.setStartDate(cullDTO.getStartDate());
        cull.setEndDate(cullDTO.getEndDate());
        for (CullDetailsDTO cullDetailDTO : cullDetailsDTO) {
            CullDetails cullDetail = new CullDetails();
            cullDetail.setAnimal(cullDetailDTO.getAnimal());
            cullDetail.setQuantity(cullDetailDTO.getQuantity());

            cullDetailsList.add(cullDetail);
        }

        cull.setCullDetails(cullDetailsList);

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
