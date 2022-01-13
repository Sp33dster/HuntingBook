package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;

@ViewScoped
@Named
public class AccountsListPageBean implements Serializable {

    static final String GENERAL_MSG_ID = "accountListForm:accountList";

    @Inject
    private AccountController accountController;

    private List<AccountDTO> accounts;

    private DataModel<AccountDTO> accountDataModel;

    public DataModel<AccountDTO> getAccountDataModel() {
        return accountDataModel;
    }

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

    public String editAccount() {
        return accountController.getAccountToEdit(accountDataModel.getRowData());
    }

    public String deleteAccount() {
        return accountController.deleteAccount(accountDataModel.getRowData());
    }

    public void activateAccount() throws AppBaseException {
        accountController.activateAccount(accountDataModel.getRowData());
        initModel();
    }

    public void deactivateAccount() {
        accountController.deactivateAccount(accountDataModel.getRowData());
        initModel();
    }

    public String startChangingPassword() {
        return accountController.startChangingPassword(accountDataModel.getRowData());
    }

    @PostConstruct
    private void initModel() {
        accounts = accountController.matchAccounts(searchByLogin, searchByName, searchByName, searchByEmail);
        accountDataModel = new ListDataModel<>(accounts);
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
