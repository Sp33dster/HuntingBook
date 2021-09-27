package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@RequestScoped
public class CreateMasterOfTheHunterPageBean {
    
    @Inject
    private AccountController accountController;
    
    private MasterOfTheHunterDTO masterOfTheHunterDTO = new MasterOfTheHunterDTO();

    private String passwordRepeat = "";
    
    public CreateMasterOfTheHunterPageBean() {
    }

    public MasterOfTheHunterDTO getMasterOfTheHunterDTO() {
        return masterOfTheHunterDTO;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    
    public String create(){
        if(!(passwordRepeat.equals(masterOfTheHunterDTO.getPassword()))){
            ContextUtils.emitInternationalizedMessage("createMasterOfTheHunterForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        
        return accountController.createMasterOfTheHunter(masterOfTheHunterDTO);
    }
        
}
