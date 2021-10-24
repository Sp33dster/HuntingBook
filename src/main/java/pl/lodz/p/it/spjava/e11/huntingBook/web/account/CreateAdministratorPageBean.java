package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@RequestScoped
public class CreateAdministratorPageBean implements Serializable {

    private String repeatPassword = "";

    @Inject
    private AccountController accountController;

    private AdministratorDTO account = new AdministratorDTO();

    public AdministratorDTO getAccount() {
        return account;
    }

    public CreateAdministratorPageBean() {
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String create() {
        if (!(repeatPassword.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createAdministratorForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
        return accountController.createAdministrator(account);
    }

}
