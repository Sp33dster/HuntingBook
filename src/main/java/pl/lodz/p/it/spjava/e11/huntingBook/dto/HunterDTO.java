package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

public class HunterDTO extends AccountDTO {

    @NotNull(message = "{constraint.notnull}")
    @Size(max = 12, message = "{constraint.string.length.toolong}")
    private String pesel;

    @NotNull(message = "{constraint.notnull}")
    @Size(min = 6, max = 9, message = "{constraint.string.length.notinrange}")
    private String phoneNumber;

    private Boolean isHunting;

    private List<Cull> listOfCull;

    private List<Hunt> listOfHunt;

    public HunterDTO(String pesel, String phoneNumber, Long id, String login, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, isActive, name, surname, email, type);
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.isHunting = false;
    }

    public HunterDTO() {
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsHunting() {
        return isHunting;
    }

    public void setIsHunting(Boolean isHunting) {
        this.isHunting = isHunting;
    }

    public List<Cull> getListOfCull() {
        return listOfCull;
    }

    public void setListOfCull(List<Cull> listOfCull) {
        this.listOfCull = listOfCull;
    }

    public List<Hunt> getListOfHunt() {
        return listOfHunt;
    }

    public void setListOfHunt(List<Hunt> listOfHunt) {
        this.listOfHunt = listOfHunt;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
