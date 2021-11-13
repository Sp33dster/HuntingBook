package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@RequestScoped
public class ChangeMyAccountPasswordPageBean {
    
    @Inject
    private AccountController accountController;
    
    private AccountDTO account = new AccountDTO();
    
    private String repeatPassword = "";
    
    private String oldPassword = "";

    public ChangeMyAccountPasswordPageBean() {
    }

    public AccountDTO getAccount() {
        return account;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    public String changePassword(){
        if(!(repeatPassword.equals(account.getPassword()))){
            ContextUtils.emitInternationalizedMessage("changeMyPasswordForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        return accountController.changeMyPassword(oldPassword, account.getPassword());
    }
    
}
