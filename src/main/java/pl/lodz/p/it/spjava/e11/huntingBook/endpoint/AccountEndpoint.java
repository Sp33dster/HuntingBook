package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.DTOConverter;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HunterFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.AccountManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@LocalBean
public class AccountEndpoint {

    @Inject
    private AccountManager accountManager;

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private HunterFacade hunterFacade;
    
    private Account accountToEdit;
    
    private Account accountToDelete;

    public void createAccount(HunterDTO hunterDTO) {
        Hunter hunter = new Hunter();

        hunter.setLogin(hunterDTO.getLogin());
        hunter.setPassword(hunterDTO.getPassword());
        hunter.setIsActive(false);
        hunter.setName(hunterDTO.getName());
        hunter.setSurname(hunterDTO.getSurname());
        hunter.setEmail(hunterDTO.getEmail());
        hunter.setType(AccountType.HUNTER);
        hunter.setPesel(hunterDTO.getPesel());
        hunter.setPhoneNumber(hunterDTO.getPhoneNumber());
        hunter.setIsHunting(Boolean.FALSE);
        hunter.setVersion(1);

        accountManager.createAccount(hunter);

    }

    public List<AccountDTO> getListOfAllAccounts() {
        return DTOConverter.createAccountDTOListFromEntity(accountFacade.findAll());
    }

    public AccountDTO getAccountToEdit(AccountDTO accountDTO) {
    accountToEdit = accountFacade.findLogin(accountDTO.getLogin());
    return DTOConverter.createAccountDTOFromEntity(accountToEdit);
            }

    public void saveAccountAfterEdit(AccountDTO accountDTO) {
        accountToEdit.setName(accountDTO.getName());
        accountToEdit.setSurname(accountDTO.getSurname());
        accountToEdit.setEmail(accountDTO.getEmail());
        accountFacade.edit(accountToEdit);
        accountToEdit = null;
    }

    public void deleteAccount(AccountDTO accountDTO) {
        accountToDelete = accountFacade.findLogin(accountDTO.getLogin());
        accountFacade.remove(accountToDelete);
    }

}
