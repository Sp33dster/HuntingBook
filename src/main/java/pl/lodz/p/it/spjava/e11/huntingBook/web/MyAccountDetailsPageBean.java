package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.AccountUtils;

@Named
@RequestScoped
public class MyAccountDetailsPageBean {
    
    @Inject
    private AccountController accountController;
    
    private AccountDTO account = new AccountDTO();

    public MyAccountDetailsPageBean() {
    }

    public AccountDTO getAccount() {
        return account;
    }
    
    public boolean isHunter(){
        return AccountUtils.isHunter(account);
    }
    
    public boolean isAdministrator(){
        return AccountUtils.isAdministrator(account);
    }
    
    public boolean isMasterOfTheHunter(){
        return AccountUtils.isMasterOfTheHunter(account);
    }
    
    @PostConstruct
    private void init(){
        account = accountController.getMyAccount();
    }
}
