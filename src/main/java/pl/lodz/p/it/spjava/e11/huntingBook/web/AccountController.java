package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.AccountEndpoint;

@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {

    @Inject
    private AccountEndpoint accountEndpoint;

    private HunterDTO hunterCreate;

    private AccountDTO editAccount;

    public AccountDTO getEditAccount() {
        return editAccount;
    }

    public void createHunter(HunterDTO hunterDTO) {
        hunterCreate = hunterDTO;
        accountEndpoint.createAccount(hunterDTO);
        hunterCreate = null;
    }

    public List<AccountDTO> getListOfAllAccounts() {
        return accountEndpoint.getListOfAllAccounts();
    }

    public String getAccountToEdit(AccountDTO accountDTO) {
        editAccount = accountEndpoint.getAccountToEdit(accountDTO);
        return "editAccount";
    }

    public String saveAccountAfterEdit(AccountDTO accountDTO) {
        accountEndpoint.saveAccountAfterEdit(accountDTO);
        return "success";
    }

    String deleteAccount(AccountDTO accountDTO) {
        accountEndpoint.deleteAccount(accountDTO);
        return "deleteSuccess";
    }
}
