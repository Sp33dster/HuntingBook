package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

@Entity
@Table(name = "Master_of_the_hunter")
public class MasterOfTheHunter extends Account implements Serializable {

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "contact_number")
    private String contactNumber;

    public MasterOfTheHunter() {
    }

    public MasterOfTheHunter(String contactNumber, Long id, String login, String password, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, password, isActive, name, surname, email, type);
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
