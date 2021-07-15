package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.HunterEndpoint;


@Named("accountController")
@SessionScoped
public class AccountController implements Serializable {
    
    @Inject
    private HunterEndpoint hunterEndpoint;
    
    private HunterDTO hunterCreate;
    
    public void createAccount(HunterDTO hunterDTO){
        hunterCreate = hunterDTO;
        hunterEndpoint.createAccount(hunterCreate);
        
    }
}
