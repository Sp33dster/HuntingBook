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

import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.ResultException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.ResultFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.ResultManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Result;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ResultEndpoint {

    @Inject
    ResultFacade resultFacade;

    @Inject
    ResultManager resultManager;

    @Resource
    SessionContext sctx;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    private Hunt huntToEnd;

    private Result huntResult;

    public Result addHuntResult(ResultDTO resultDTO) throws AppBaseException {
        Result result = new Result();

        result.setTypeOfResult(resultDTO.getTypeOfResult());
        if (resultDTO.getTypeOfResult().equals(TypeOfResult.HIT)) {
            result.setAnimalType(resultDTO.getAnimalType());
            result.setAnimalWeight(resultDTO.getAnimalWeight());
            result.setIsPrivateUse(resultDTO.getIsPrivateUse());
            result.setIsConfirmed(Boolean.FALSE);
        }
        boolean rollbackTX;
        int retryTXCounter = txRetryLimit;

        do {
            try {
                resultManager.addHuntResult(result);
                rollbackTX = resultManager.isLastTransactionRollback();
            } catch (AppBaseException | EJBTransactionRolledbackException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Próba " + retryTXCounter
                        + " wykonania metody biznesowej zakończona wyjątkiem klasy:"
                        + ex.getClass().getName());
                rollbackTX = true;
            }

        } while (rollbackTX && --retryTXCounter > 0);

        if (rollbackTX && retryTXCounter == 0) {
            throw ResultException.createResultExceptionWithTxRetryRollback();
        }
        return result;
    }
}
