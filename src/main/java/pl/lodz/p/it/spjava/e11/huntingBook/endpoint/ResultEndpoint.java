package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;

import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntFacade;
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
    HuntFacade huntFacade;

    @Inject
    ResultManager resultManager;

    @Resource
    SessionContext sctx;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    private Hunt huntToEnd;

    private Result huntResult;

    public void addHuntResult(HuntDTO hunt, ResultDTO resultDTO) throws AppBaseException {
        Hunt huntToAddResult = new Hunt();

        Result result = new Result();

        huntToAddResult = huntFacade.find(hunt.getId());

        result.setTypeOfResult(resultDTO.getTypeOfResult());
        if (resultDTO.getTypeOfResult().equals(TypeOfResult.HIT)) {
            result.setAnimalType(resultDTO.getAnimalType());
            result.setAnimalWeight(resultDTO.getAnimalWeight());
            result.setIsPrivateUse(resultDTO.getIsPrivateUse());
            result.setIsConfirmed(Boolean.FALSE);
        }

        huntToAddResult.setResult(result);

        huntFacade.edit(huntToAddResult);

        huntToAddResult = null;
        result = null;
    }
}
