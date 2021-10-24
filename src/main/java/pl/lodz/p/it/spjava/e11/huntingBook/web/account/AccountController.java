package pl.lodz.p.it.spjava.e11.huntingBook.web.account;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.MasterOfTheHunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.AccountEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AccountException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());

    @Inject
    private AccountEndpoint accountEndpoint;

    private HunterDTO hunterCreate;

    private AdministratorDTO administratorCreate;

    private AccountDTO editAccount;

    private AccountDTO accountToChangePassword;

    public AccountDTO getEditAccount() {
        return editAccount;
    }

    public AccountDTO getAccountToChangePassword() {
        return accountToChangePassword;
    }

    public String createHunter(HunterDTO hunterDTO) {
        try {
            LOG.log(Level.INFO, "Zgłoszenie akcji createHunter (" + ContextUtils.getUserAddress() + ")");
            hunterCreate = hunterDTO;
            accountEndpoint.createAccount(hunterDTO);
            hunterCreate = null;
            return "success";
        } catch (AccountException ae) {
            if (AccountException.KEY_DB_CONSTRAINT.equals(ae.getMessage())) {
                ContextUtils.emitInternationalizedMessage("login", AccountException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createHunter wyjątku: ", ae);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createHunter wyjątku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }

    }

    public String createAdministrator(AdministratorDTO administratorDTO) {
        try {
            LOG.log(Level.INFO, "Zgłoszenie akcji createAdministrator (" + ContextUtils.getUserAddress() + ")");

            administratorCreate = administratorDTO;
            accountEndpoint.createAccount(administratorCreate);
            administratorCreate = null;
            return "success";
        } catch (AccountException ae) {
            if (AccountException.KEY_DB_CONSTRAINT.equals(ae.getMessage())) {
                ContextUtils.emitInternationalizedMessage("login", AccountException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createAdministrator wyjątku: ", ae);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createAdministrator wyjątku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    String createMasterOfTheHunter(MasterOfTheHunterDTO masterOfTheHunterDTO) {
        try {
            LOG.log(Level.INFO, "Zgłoszenie akcji createAdministrator (" + ContextUtils.getUserAddress() + ")");

            accountEndpoint.createAccount(masterOfTheHunterDTO);

            return "success";
        } catch (AccountException ae) {
            if (AccountException.KEY_DB_CONSTRAINT.equals(ae.getMessage())) {
                ContextUtils.emitInternationalizedMessage("login", AccountException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createAdministrator wyjątku: ", ae);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createAdministrator wyjątku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public List<AccountDTO> getListOfAllAccounts() {
        return accountEndpoint.getListOfAllAccounts();
    }

    public String getAccountToEdit(AccountDTO accountDTO) {
        editAccount = accountEndpoint.getAccountToEdit(accountDTO);
        return "editAccount";
    }

    public String saveHunterAfterEdit(AccountDTO accountDTO) throws AppBaseException {
        accountEndpoint.saveHunterAfterEdit(accountDTO);
        return "success";
    }

    public String saveMasterOfTheHunterAfterEdit(AccountDTO accountDTO) throws AppBaseException {
        accountEndpoint.saveMasterOfTheHunterAfterEdit(accountDTO);
        return "success";
    }

    public String saveAdministratorAfterEdit(AccountDTO accountDTO) throws AppBaseException {
        accountEndpoint.saveAdministratorAfterEdit(accountDTO);
        return "success";
    }

    public String deleteAccount(AccountDTO accountDTO) {
        accountEndpoint.deleteAccount(accountDTO);
        return "deleteSuccess";
    }

    public void activateAccount(AccountDTO accountDTO) {
        accountEndpoint.activateAccount(accountDTO);
        ContextUtils.emitSuccessMessage(AccountsListPageBean.GENERAL_MSG_ID);
    }

    public void deactivateAccount(AccountDTO accountDTO) {
        accountEndpoint.deactivateAccount(accountDTO);
        ContextUtils.emitSuccessMessage(AccountsListPageBean.GENERAL_MSG_ID);
    }

    public String startChangingPassword(AccountDTO accountDTO) {
        this.accountToChangePassword = accountDTO;
        return "changePassword";
    }

    public String changePassword(String password) {
        accountEndpoint.changePassword(accountToChangePassword, password);
        return "success";
    }

    public List<AccountDTO> matchAccounts(String login, String name, String surname, String email) {
        return accountEndpoint.matchAccounts(login, name, surname, email);
    }

    public AccountDTO getMyAccount() {
        return accountEndpoint.getMyAccountDTO();
    }

    String changeMyPassword(String oldPassword, String newPassword) {
        accountEndpoint.changeMyPassword(oldPassword, newPassword);
        return "success";
    }
    
    @PostConstruct
    private void init(){
        LOG.severe("Session started: " + ContextUtils.getSessionID());
    }
    
    public String invalidateSession(){
        ContextUtils.invalidateSession();
        return "cancelAction";
        
    }
    
    public String getMyLogin(){
        return ContextUtils.getUserName();
    }
}
