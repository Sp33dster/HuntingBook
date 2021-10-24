package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

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
    
    private MasterOfTheHunterDTO account = new MasterOfTheHunterDTO();

    private String repeatPassword = "";
    
    public CreateMasterOfTheHunterPageBean() {
    }

    public MasterOfTheHunterDTO getAccount() {
        return account;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    
    public String create(){
        if(!(repeatPassword.equals(account.getPassword()))){
            ContextUtils.emitInternationalizedMessage("createMasterOfTheHunterForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        
        return accountController.createMasterOfTheHunter(account);
    }
        
}
