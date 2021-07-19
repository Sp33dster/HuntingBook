package pl.lodz.p.it.spjava.e11.huntingBook.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;

@Named
@RequestScoped
public class CreateHunterBean {

    public CreateHunterBean() {
    }
    
    @Inject
    private AccountController accountController;
    
    private HunterDTO hunter = new HunterDTO();
    
    private String repeatPassword;
    
    public HunterDTO getHunter(){
        return hunter;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    
    public void create(){
        System.out.println("Utwórz klientaBean");
        accountController.createHunter(hunter);
    }
    
    
}
