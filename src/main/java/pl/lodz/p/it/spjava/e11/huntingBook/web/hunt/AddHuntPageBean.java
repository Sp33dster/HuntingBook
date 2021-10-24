package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;

@Named
@RequestScoped
public class AddHuntPageBean {
    
    @Inject
    HuntController huntController;
    
    private HuntDTO hunt = new HuntDTO();

    public AddHuntPageBean() {
    }

    public HuntDTO getHunt() {
        return hunt;
    }
    
    public String addNewHunt(){
        return huntController.addNewHunt(hunt);
    }
    
}
