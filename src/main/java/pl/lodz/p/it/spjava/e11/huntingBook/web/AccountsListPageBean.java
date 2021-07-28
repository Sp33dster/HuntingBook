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
    
    @Inject
    private AccountController accountController;
    
    private List<AccountDTO> accounts;

    public AccountsListPageBean() {
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }
    
    public String editAccount(AccountDTO accountDTO){
        return accountController.getAccountToEdit(accountDTO);
    }
    
    public String deleteAccount(AccountDTO accountDTO){
        return accountController.deleteAccount(accountDTO);
    }
    
    @PostConstruct
    private void initModel(){
        accounts = accountController.getListOfAllAccounts();
    }
    
}
