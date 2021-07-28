package pl.lodz.p.it.spjava.e11.huntingBook.web;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;

@Named
@RequestScoped
public class CreateHunterBean implements Serializable {

    public CreateHunterBean() {
    }

    @Inject
    private AccountController accountController;

    private HunterDTO hunter = new HunterDTO();

    private String repeatPassword;

    public HunterDTO getHunter() {
        return hunter;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String create() {
        if (hunter.getPassword().equals(repeatPassword)) {
            accountController.createHunter(hunter);
            return "success";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("createAccountForm", new FacesMessage("Passwords shoud be the same"));
            return "";
        }
    }

}
