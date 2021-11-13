package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@RequestScoped
public class CreateHunterPageBean implements Serializable {

    public CreateHunterPageBean() {
    }

    @Inject
    private AccountController accountController;

    private HunterDTO account = new HunterDTO();

    private String repeatPassword = "";

    public HunterDTO getAccount() {
        return account;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String create() {
        if (!(repeatPassword.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createHunterForm:passwordRepeat", "passwords.not.matching");
            return null;

        } else {
            return accountController.createHunter(account);
                    }
    }

}
