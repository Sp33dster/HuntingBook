package pl.lodz.p.it.spjava.e11.huntingBook.endpoint;

import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.AccountFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.facade.HunterFacade;
import pl.lodz.p.it.spjava.e11.huntingBook.managers.AccountManager;
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

    public void createAccount(HunterDTO hunterDTO) {
        Hunter hunter = new Hunter();
        System.out.println("Konto kontroler");

        hunter.setLogin(hunterDTO.getLogin());
        System.out.println("login");
        hunter.setPassword(hunterDTO.getPassword());
        System.out.println("password");
        hunter.setIsActive(false);
        hunter.setName(hunterDTO.getName());
        System.out.println("name");
        hunter.setSurname(hunterDTO.getSurname());
        hunter.setEmail(hunterDTO.getEmail());
        hunter.setType(AccountType.HUNTER);
        hunter.setPesel(hunterDTO.getPesel());
        hunter.setPhoneNumber(hunterDTO.getPhoneNumber());
        hunter.setIsHunting(Boolean.FALSE);
        hunter.setVersion(1);
        System.out.println("przed create");
        accountManager.createAccount(hunter);
        System.out.println("po create");
    }

}
