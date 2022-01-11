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

@ViewScoped
@Named
public class HuntersListPageBean implements Serializable {

    static final String GENERAL_MSG_ID = "accountListForm:accountList";
    
    @Inject
    private AccountController accountController;

    private List<AccountDTO> accounts;
    
    private DataModel<AccountDTO> accountDataModel;

    public DataModel<AccountDTO> getAccountDataModel() {
        return accountDataModel;
    }
            
    public HuntersListPageBean() {
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    
    @PostConstruct
    private void initModel() {
        accounts = accountController.findHunterAccount();
        accountDataModel = new ListDataModel<>(accounts);
    }

    public void refresh() {
        initModel();
    }

    public String getHunterToAddCull(){
        return accountController.getHunterToAddCull(accountDataModel.getRowData());
    }
    
}
