package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;

@Named
@RequestScoped
public class EndHuntPageBean {
    
    @Inject
    HuntController huntController;
    
    private HuntDTO hunt = new HuntDTO();

    public EndHuntPageBean() {
    }

    public HuntDTO getHunt() {
        return hunt;
    }
    
    @PostConstruct
    private void init(){
        hunt = huntController.getEndHunt();
    }
    
    public String endHunt() throws AppBaseException{
        return huntController.endHunt(hunt);
    }
    
    
}
