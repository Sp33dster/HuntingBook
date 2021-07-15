package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.AccountManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HunterEndpoint {
    
    
    @Inject
    private AccountManager accountManager;
    
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void createAccount(HunterDTO hunterDTO    ){
        Hunter hunter = new Hunter();
         
        createAccountEntityFromAccountDTO(hunterDTO, hunter);
        
        hunter.setPesel(hunterDTO.getPesel());
        hunter.setPhoneNumber(hunterDTO.getPhoneNumber());
        
        accountManager.createAccount(hunter);
                
    }
    
    private static void createAccountEntityFromAccountDTO(AccountDTO accountDTO, Account account){
        account.setName(accountDTO.getName());
        account.setSurname(accountDTO.getSurname());
        account.setLogin(accountDTO.getLogin());
        account.setEmail(accountDTO.getEmail());
    }
    
}
