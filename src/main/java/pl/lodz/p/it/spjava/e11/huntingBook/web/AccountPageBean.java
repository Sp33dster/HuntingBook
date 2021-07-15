package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;

@Named
@RequestScoped
public class AccountPageBean {

    public AccountPageBean() {
    }
    
    @Inject
    private AccountController accountController;
    
    private HunterDTO hunterDTO = new HunterDTO();
    
    public HunterDTO getHunterDTO(){
        return hunterDTO;
    }
    
    public void create() {
         
        accountController.createAccount(hunterDTO);
    }
            
}
