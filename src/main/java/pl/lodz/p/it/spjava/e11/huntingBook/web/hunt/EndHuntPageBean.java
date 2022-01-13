package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Named
@RequestScoped
public class EndHuntPageBean {
    
    @Inject
    HuntController huntController;
    
    private List<TypeOfResult> types;
    
    private HuntDTO hunt = new HuntDTO();
    
    private HuntResultDTO result = new HuntResultDTO();
    
    public EndHuntPageBean() {
    }

    public HuntDTO getHunt() {
        return hunt;
    }
    
    @PostConstruct
    private void init(){
        hunt = huntController.getEndHunt();
        types = new ArrayList<>();
        types.add(TypeOfResult.HIT);
        types.add(TypeOfResult.MISS);
        types.add(TypeOfResult.NOTHING);
    }

    public List<TypeOfResult> getTypes() {
        return types;
    }

    public HuntResultDTO getResult() {
        return result;
    }
       
    public String endHunt() throws AppBaseException{
        return huntController.endHunt(hunt, result);
    }
    
    
}
