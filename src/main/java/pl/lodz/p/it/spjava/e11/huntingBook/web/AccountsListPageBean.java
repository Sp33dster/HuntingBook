package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;

@ViewScoped
@Named
public class AccountsListPageBean implements Serializable {

    static final String GENERAL_MSG_ID = "accountListForm:accountList";
    
    @Inject
    private AccountController accountController;

    private List<AccountDTO> accounts;

    private String searchByLogin = "";
    private String searchByEmail = "";
    private String searchByName = "";
    private String searchBySurname = "";

    public AccountsListPageBean() {
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public String getSearchByLogin() {
        return searchByLogin;
    }

    public void setSearchByLogin(String searchByLogin) {
        this.searchByLogin = searchByLogin;
    }

    public String getSearchByEmail() {
        return searchByEmail;
    }

    public void setSearchByEmail(String searchByEmail) {
        this.searchByEmail = searchByEmail;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public String getSearchBySurname() {
        return searchBySurname;
    }

    public void setSearchBySurname(String searchBySurname) {
        this.searchBySurname = searchBySurname;
    }

    public String editAccount(AccountDTO accountDTO) {
        return accountController.getAccountToEdit(accountDTO);
    }

    public String deleteAccount(AccountDTO accountDTO) {
        return accountController.deleteAccount(accountDTO);
    }

    public void activateAccount(AccountDTO accountDTO) {
        accountController.activateAccount(accountDTO);
        initModel();
    }

    public void deactivateAccount(AccountDTO accountDTO) {
        accountController.deactivateAccount(accountDTO);
        initModel();
    }

    public String startChangingPassword(AccountDTO accountDTO) {
        return accountController.startChangingPassword(accountDTO);
    }

    @PostConstruct
    private void initModel() {
        accounts = accountController.matchAccounts(searchByLogin, searchByName, searchByName, searchByEmail);
    }

    public void refresh() {
        initModel();
    }

    public void erase() {
        searchByLogin = "";
        searchByEmail = "";
        searchByName = "";
        searchBySurname = "";
        initModel();
    }
}
