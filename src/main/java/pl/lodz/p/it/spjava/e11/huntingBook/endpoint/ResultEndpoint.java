package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;

import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.CullDetailsFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HuntResultFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.ResultManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ResultEndpoint {

    @Inject
    HuntResultFacade resultFacade;
    
    @Inject
    AccountEndpoint accountEndpoint;

    @Inject
    HuntFacade huntFacade;

    @Inject
    ResultManager resultManager;

    @Inject
    CullDetailsFacade cullDetailsFacade;

    @Resource
    SessionContext sctx;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    private Hunt huntToEnd;

    private HuntResult huntResult;

    
    @RolesAllowed({"Hunter"})
    public void addHuntResult(HuntDTO hunt, HuntResultDTO resultDTO) throws AppBaseException {
        Hunt huntToAddResult = new Hunt();

        HuntResult result = new HuntResult();

        huntToAddResult = huntFacade.find(hunt.getId());
             
        result = resultFacade.find(huntToAddResult.getResult().getId());
        
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

    @RolesAllowed({"MOTHunter"})
    public List<CullDetailsDTO> getAllAnimals() {
        List<CullDetailsDTO> allAnimals = new ArrayList<>();
        for (AnimalType animal : AnimalType.values()) {

            allAnimals.add(new CullDetailsDTO(animal, resultFacade.countAnimal(animal)));
        }
        
        return allAnimals;

    }

    @RolesAllowed({"Hunter"})
    public List<CullDetailsDTO> getMyAnimals() {
        Hunter hunter = accountEndpoint.getMyHunterAccount();
       List<Hunt> myHunts = huntFacade.getMyHunt(hunter);
       List<AnimalType> animalTypes = new ArrayList<>();
       for(Hunt hunt : myHunts){
         HuntResult result =  resultFacade.find(hunt.getResult().getId());
         if(result.getAnimalType() != null){
             animalTypes.add(result.getAnimalType());
         }
       }
       
       List<CullDetailsDTO> allAnimals = new ArrayList<>();
       for(AnimalType animal : animalTypes){
           allAnimals.add(new CullDetailsDTO(animal, 1L));
       }
       
       return allAnimals;
       
    }

}
