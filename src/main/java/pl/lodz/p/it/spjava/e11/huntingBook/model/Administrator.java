package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

@Entity
@Table(name = "Administrator")
public class Administrator extends Account implements Serializable {

    
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "alarm_number")
    private String alarmNumber;

    public Administrator() {
    }

    public Administrator(String alarmNumber, Long id, String login, String password, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, password, isActive, name, surname, email, type);
        this.alarmNumber = alarmNumber;
    }

    
    
    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    
}
