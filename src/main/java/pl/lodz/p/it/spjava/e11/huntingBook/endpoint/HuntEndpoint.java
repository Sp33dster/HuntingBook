package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.HuntException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntResultFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.HuntManager;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.ResultManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.DTOConverter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HuntEndpoint {

    @Inject
    HuntFacade huntFacade;

    @Inject
    HuntResultFacade resultFacade;

    @Inject
    AccountEndpoint accountEndpoint;

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

    private Hunt huntToResult;

    private HuntResult huntResult;

    @RolesAllowed({"Hunter"})
    public void addNewHunt(HuntDTO huntDTO) throws AppBaseException {

        Hunt hunt = new Hunt();

        hunt.setStartTime(huntDTO.getStartTime());
        hunt.setArea(huntDTO.getArea());
        if (huntDTO.getEndTime() != null) {
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

    @RolesAllowed({"Hunter"})
    public HuntDTO getHuntToEnd(HuntDTO hunt) {
        huntToEnd = huntFacade.find(hunt.getId());
        return DTOConverter.createHuntDTOFromEntity(huntToEnd);
    }

    @RolesAllowed({"Hunter"})
    public HuntDTO getHuntToAddResult(HuntDTO hunt) {
        huntToResult = huntFacade.getHunt(hunt.getId());
        return DTOConverter.createHuntDTOFromEntity(huntToResult);

    }

    @RolesAllowed({"Hunter"})
    public void endHunt(HuntDTO hunt, HuntResultDTO result) throws AppBaseException {
        if (huntToEnd == null) {
            throw new IllegalArgumentException("Brak wczytanego polowania do modyfikacji");
        }
        HuntResult huntToEndResult = new HuntResult();
        huntToEndResult.setTypeOfResult(result.getTypeOfResult());
        huntToEnd.setEndTime(hunt.getEndTime());
        huntToEnd.setResult(huntToEndResult);
        huntToEnd.setIsEnded(Boolean.TRUE);
        
        huntFacade.edit(huntToEnd);
        huntToEnd = null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<HuntDTO> getMyHunts() {
        Hunter hunter = new Hunter();
        hunter = accountEndpoint.getMyHunterAccount();

        return DTOConverter.createHuntsDTOListFromEntity(huntFacade.getMyHunt(hunter));
    }

    @RolesAllowed({"MOTHunter"})
    public void confirmResult(HuntDTO hunt) throws AppBaseException{
        Hunt huntToConfirmResult = new Hunt();
        HuntResult result = new HuntResult();

        huntToConfirmResult = huntFacade.getHunt(hunt.getId());
        result = resultFacade.find(huntToConfirmResult.getResult().getId());

        result.setIsConfirmed(Boolean.TRUE);

        huntToConfirmResult.setResult(result);

        huntFacade.edit(huntToConfirmResult);

        result = null;
        huntToConfirmResult = null;
    }

}
