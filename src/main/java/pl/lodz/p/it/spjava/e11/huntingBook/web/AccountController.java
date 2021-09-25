package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AdministratorDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
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

    public AccountDTO getEditAccount() {
        return editAccount;
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

    public List<AccountDTO> getListOfAllAccounts() {
        return accountEndpoint.getListOfAllAccounts();
    }

    public String getAccountToEdit(AccountDTO accountDTO) {
        editAccount = accountEndpoint.getAccountToEdit(accountDTO);
        return "editAccount";
    }

    public String saveAccountAfterEdit(AccountDTO accountDTO) {
        accountEndpoint.saveAccountAfterEdit(accountDTO);
        return "success";
    }

    String deleteAccount(AccountDTO accountDTO) {
        accountEndpoint.deleteAccount(accountDTO);
        return "deleteSuccess";
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
}
