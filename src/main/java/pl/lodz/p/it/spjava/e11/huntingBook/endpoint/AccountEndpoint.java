package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.DTOConverter;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AccountException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AdministratorFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HunterFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.AccountManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.AccountUtils;

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

    @Inject
    private AdministratorFacade administratorFacade;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    private Account accountToEdit;

    private Account accountToDelete;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void createAccount(HunterDTO hunterDTO) throws AppBaseException {
        Hunter hunter = new Hunter();

        rewriteDataToNewAccount(hunterDTO, hunter);

        hunter.setIsActive(false);
        hunter.setType(AccountType.HUNTER);
        hunter.setPesel(hunterDTO.getPesel());
        hunter.setPhoneNumber(hunterDTO.getPhoneNumber());
        hunter.setIsHunting(Boolean.FALSE);
        hunter.setVersion(1);

        boolean rollbackTX;
        int retryTXCounter = txRetryLimit;

        do {
            try {
                accountManager.createAccount(hunter);
                rollbackTX = accountManager.isLastTransactionRollback();
            } catch (AppBaseException | EJBTransactionRolledbackException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Próba " + retryTXCounter
                        + " wykonania metody biznesowej zakończona wyjątkiem klasy:"
                        + ex.getClass().getName()
                        + " z komunikatem: " + ex.getMessage());
                rollbackTX = true;
            }
        } while (rollbackTX && --retryTXCounter > 0);

        if (rollbackTX && --retryTXCounter == 0) {
            throw AccountException.createTxRetryRollback();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void createAccount(AdministratorDTO administratorDTO) throws AppBaseException {
        Administrator administrator = new Administrator();
        rewriteDataToNewAccount(administratorDTO, administrator);

        administrator.setAlarmNumber(administratorDTO.getAlarmNumber());
        administrator.setType(AccountType.ADMINISTRATOR);

        boolean rollbackTX;
        int retryTXCounter = txRetryLimit;

        do {
            try {
                accountManager.createAccount(administrator);
                rollbackTX = accountManager.isLastTransactionRollback();
            } catch (AppBaseException | EJBTransactionRolledbackException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Próba " + retryTXCounter
                        + " wykonania metody biznesowej zakończona wyjątkiem klasy:"
                        + ex.getClass().getName()
                        + " z komunikatem: " + ex.getMessage());
                rollbackTX = true;
            }
        } while (rollbackTX && --retryTXCounter > 0);

        if (rollbackTX && --retryTXCounter == 0) {
            throw AccountException.createTxRetryRollback();
        }

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

    private void rewriteDataToNewAccount(AccountDTO accountDTO, Account account) {
        account.setLogin(accountDTO.getLogin());

        rewriteEditableDataToNewAccount(accountDTO, account);

        account.setIsActive(true);

        account.setPassword(AccountUtils.createSHAOfPassword(accountDTO.getPassword()));
    }

    private void rewriteEditableDataToNewAccount(AccountDTO accountDTO, Account account) {
        account.setName(accountDTO.getName());
        account.setSurname(accountDTO.getSurname());
        account.setEmail(accountDTO.getEmail());
    }

}
