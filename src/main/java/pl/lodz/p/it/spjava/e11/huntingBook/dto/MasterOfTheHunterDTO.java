package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

public class MasterOfTheHunterDTO extends AccountDTO {

    @Size(min = 6, max = 9, message = "{constraint.string.length.notinrange}")
    private String contactNumber;

    public MasterOfTheHunterDTO() {
    }

    public MasterOfTheHunterDTO(String contactNumber, Long id, String login, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, isActive, name, surname, email, type);
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
