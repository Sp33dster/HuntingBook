package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.AccountUtils;

@Named
@RequestScoped
public class EditAccountPageBean {

    @Inject
    private AccountController accountController;

    private AccountDTO account = new AccountDTO();

    public EditAccountPageBean() {
    }

    public AccountDTO getAccount() {
        return account;
    }

    @PostConstruct
    private void init() {
        account = accountController.getEditAccount();
    }

    public boolean isHunter() {
        return AccountUtils.isHunter(account);
    }

    public boolean isAdministrator() {
        return AccountUtils.isAdministrator(account);
    }

    public boolean isMasterOfTheHunter() {
        return AccountUtils.isMasterOfTheHunter(account);
    }

    public String saveHunterAfterEdit() throws AppBaseException {
        return accountController.saveHunterAfterEdit(account);
    }

    public String saveAdministratorAfterEdit() throws AppBaseException {
        return accountController.saveAdministratorAfterEdit(account);
    }

    public String saveMasterOfTheHunterAfterEdit() throws AppBaseException {
        return accountController.saveMasterOfTheHunterAfterEdit(account);
    }
}
