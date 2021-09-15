package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

public class AdministratorDTO extends AccountDTO {

    @Size(min = 6, max = 9, message = "{constraint.string.length.notinrange}")
    private String alarmNumber;

    public AdministratorDTO() {
    }

    public AdministratorDTO(String alarmNumber, Long id, String login, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, isActive, name, surname, email, type);
        this.alarmNumber = alarmNumber;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

}
