package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@RequestScoped
public class ChangeAccountPasswordPageBean {

    @Inject
    private AccountController accountController;

    private AccountDTO account;

    public ChangeAccountPasswordPageBean() {
    }

    public AccountDTO getAccount() {
        return account;
    }

    @PostConstruct
    private void init() {
        account = accountController.getAccountToChangePassword();
        account.setPassword(new String());
    }

    private String repeatPassword = "";

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String changePassword() {
        if (!(repeatPassword.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("changeAccountPasswordForm:repeatPassword", "passwords.not.matching");
            return null;
        }
        return accountController.changePassword(account.getPassword());
    }

}
