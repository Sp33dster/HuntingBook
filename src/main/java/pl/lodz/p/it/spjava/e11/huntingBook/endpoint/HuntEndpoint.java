package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.HuntException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.HuntManager;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.ResultManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Result;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.DTOConverter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HuntEndpoint {

    @Inject
    HuntFacade huntFacade;

    @Inject
    HuntManager huntManager;

    @Inject
    ResultEndpoint resultEndpoint;
    
    @Inject
    ResultManager resultManager;

    @Resource
    SessionContext sctx;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;
    
    private Hunt huntToEnd;
    
    private Result huntResult;

    public void addNewHunt(HuntDTO huntDTO) throws AppBaseException {

               
        Hunt hunt = new Hunt();
        
        hunt.setStartTime(huntDTO.getStartTime());
        hunt.setArea(huntDTO.getArea());
        if (huntDTO.getEndTime()!=null) {
            hunt.setEndTime(huntDTO.getEndTime());
        }
        
        boolean rollbackTx;
        int retryTxCounter = txRetryLimit;

        do {
            try {
                huntManager.addNewHunt(hunt);
                rollbackTx = huntManager.isLastTransactionRollback();
            } catch (AppBaseException | EJBTransactionRolledbackException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Próba " + retryTxCounter
                        + " wykonania metody biznesowej zakończona wyjątkiem klasy: "
                        + ex.getClass().getName());
                rollbackTx = true;
            }
        } while (rollbackTx && --retryTxCounter > 0);
        if (rollbackTx && retryTxCounter == 0) {
            throw HuntException.createHuntExceptionWithTxRetryRollback();

        }
    }

    public HuntDTO getHuntToEnd(HuntDTO hunt) {
        huntToEnd = huntFacade.find(hunt.getId());
        return DTOConverter.createHuntDTOFromEntity(huntToEnd);
    }

    public void endHunt(HuntDTO hunt, ResultDTO result) throws AppBaseException {
       if (huntToEnd == null){
           throw new IllegalArgumentException("Brak wczytanego polowania do modyfikacji");
       }
       
       huntToEnd.setEndTime(hunt.getEndTime());
       huntResult = resultEndpoint.addHuntResult(result);
       huntToEnd.setResult(huntResult);
       
       huntManager.addNewHunt(huntToEnd);
       
    }

}
