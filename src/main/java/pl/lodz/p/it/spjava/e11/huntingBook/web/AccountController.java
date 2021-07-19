package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.AccountEndpoint;

@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {

    @Inject
    private AccountEndpoint accountEndpoint;

    private HunterDTO hunterCreate;

    public void createHunter(HunterDTO hunterDTO) {
        System.out.println("KontoKontroler");
        hunterCreate = hunterDTO;
        accountEndpoint.createAccount(hunterDTO);
        hunterCreate = null;
    }
}
