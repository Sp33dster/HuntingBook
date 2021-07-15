package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.List;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Address;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Cull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

public class HunterDTO extends AccountDTO {

    private String pesel;

    private String phoneNumber;

    private Boolean isHunting;

    private Address addres;

    private List<Cull> listOfCull;

    private List<Hunt> listOfHunt;

    public HunterDTO(String pesel, String phoneNumber, Address addres, String login, String password, boolean isActive, String name, String surname, String email, AccountType type) {
        super(login, password, isActive, name, surname, email, type);
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.isHunting = false;
        this.addres = addres;
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

    public Address getAddres() {
        return addres;
    }

    public void setAddres(Address addres) {
        this.addres = addres;
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
        return "HunterDTO{" + super.toString() + "pesel=" + pesel + ", phoneNumber=" + phoneNumber + ", isHunting=" + isHunting + ", addres=" + addres + ", listOfCull=" + listOfCull + ", listOfHunt=" + listOfHunt + '}';
    }

}
