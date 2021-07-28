package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.AccountUtils;

@Named
@RequestScoped
public class EditAccountPageBean {
    
    @Inject
    private AccountController accountController;
    
    private AccountDTO accountDTO = new AccountDTO();

    public EditAccountPageBean() {
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }
    
    @PostConstruct
    private void init(){
        accountDTO = accountController.getEditAccount();
    }
    
    public boolean isHunter(){
        return AccountUtils.isHunter(accountDTO);
    }
    
//    public boolean isAdministrator(){
//        return AccountUtils.isAdministrator(accountDTO);
//    }
    
//    public boolean isMasterOfTheHunter(){
//        return AccountUtils.isMasterOfTheHunter(accountDTO);
//    }
     public String saveAccountAfterEdit(){
         return accountController.saveAccountAfterEdit(accountDTO);
     }
}
