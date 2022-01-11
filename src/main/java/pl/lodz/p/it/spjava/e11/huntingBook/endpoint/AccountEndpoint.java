package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.DTOConverter;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AccountException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AdministratorFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HunterFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.AccountManager;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.MasterOfTheHunter;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;
import pl.lodz.p.it.spjava.e11.huntingBook.security.SHA256HashGenerator;

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

    @Inject
    private SHA256HashGenerator generator;

    @Resource(name = "txRetryLimit")
    private int txRetryLimit;

    @Resource
    protected SessionContext sxtx;

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

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void createAccount(MasterOfTheHunterDTO masterOfTheHunterDTO) throws AppBaseException {
        MasterOfTheHunter motHunter = new MasterOfTheHunter();
        rewriteDataToNewAccount(masterOfTheHunterDTO, motHunter);

        motHunter.setType(AccountType.MASTER_OF_THE_HUNTER);
        motHunter.setContactNumber(masterOfTheHunterDTO.getContactNumber());

        boolean rollbackTX;
        int retryTXCounter = txRetryLimit;

        do {
            try {
                accountManager.createAccount(motHunter);
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

    public void saveHunterAfterEdit(AccountDTO hunterDTO) throws AppBaseException {
        if (null == accountToEdit) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        rewriteEditableDataToNewAccount(hunterDTO, accountToEdit);

        ((Hunter) accountToEdit).setPesel(((HunterDTO) hunterDTO).getPesel());
        ((Hunter) accountToEdit).setPhoneNumber(((HunterDTO) hunterDTO).getPhoneNumber());

        accountFacade.edit(accountToEdit);
        accountToEdit = null;
    }

    public void saveMasterOfTheHunterAfterEdit(AccountDTO motHunterDTO) throws AppBaseException {
        if (null == accountToEdit) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        rewriteEditableDataToNewAccount(motHunterDTO, accountToEdit);

        ((MasterOfTheHunter) accountToEdit).setContactNumber(((MasterOfTheHunterDTO) motHunterDTO).getContactNumber());

        accountFacade.edit(accountToEdit);
        accountToEdit = null;
    }

    public void saveAdministratorAfterEdit(AccountDTO administratorDTO) throws AppBaseException {
        if (null == accountToEdit) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        rewriteEditableDataToNewAccount(administratorDTO, accountToEdit);

        ((Administrator) accountToEdit).setAlarmNumber(((AdministratorDTO) administratorDTO).getAlarmNumber());

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

        account.setPassword(generator.generateHash(accountDTO.getPassword()));
    }

    private void rewriteEditableDataToNewAccount(AccountDTO accountDTO, Account account) {
        account.setName(accountDTO.getName());
        account.setSurname(accountDTO.getSurname());
        account.setEmail(accountDTO.getEmail());
    }

    public void activateAccount(AccountDTO accountDTO) {
        Account account = accountFacade.find(accountDTO.getId());
        account.setIsActive(true);
    }

    public void deactivateAccount(AccountDTO accountDTO) {
        Account account = accountFacade.find(accountDTO.getId());
        account.setIsActive(false);
    }

    public void changePassword(AccountDTO accountDTO, String password) {
        Account account = accountFacade.find(accountDTO.getId());
        account.setPassword(generator.generateHash(password));
    }

    public void changeMyPassword(String oldOne, String newOne) {
        Account myAccount = getMyAccount();
        if (!myAccount.getPassword().equals(generator.generateHash(oldOne))) {
            throw new IllegalArgumentException("Podane dotychczasowe hasło nie zgadza się");
        }
        myAccount.setPassword(generator.generateHash(newOne));
    }

    public AccountDTO getMyAccountDTO() {
        return DTOConverter.createAccountDTOFromEntity(getMyAccount());
    }

    public Account getMyAccount() {
        return accountFacade.findLogin(getMyLogin());
    }

    public List<AccountDTO> matchAccounts(String login, String name, String surname, String email) {
        return DTOConverter.createAccountDTOListFromEntity(accountFacade.matchAccounts(login, name, surname, email));
    }

    public String getMyLogin() throws IllegalStateException {
        return sxtx.getCallerPrincipal().getName();
    }

    public Hunter getMyHunterAccount() {
        return accountFacade.findHunterLogin(getMyLogin());
    }

    public List<AccountDTO> getActiveHuntersList() {
        return DTOConverter.createAccountDTOListFromEntity(accountFacade.findActiveHunters());
    }

    public List<AccountDTO> findHunters() {
        return DTOConverter.createAccountDTOListFromEntity(accountFacade.findHunters());
    }

    public AccountDTO getHunterToAddCull(AccountDTO hunter) {
        return DTOConverter.createAccountDTOFromEntity(accountFacade.find(hunter.getId()));
    }

}
