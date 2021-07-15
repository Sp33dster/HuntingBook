package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Administrator")
public class Administrator extends Account implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "alarm_number")
    private String alarmNumber;

    public Administrator() {
    }

    public Administrator(Long id) {
        this.id = id;
    }

    public Administrator(Long id, String alarmNumber) {
        this.id = id;
        this.alarmNumber = alarmNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Administrator[ id=" + id + " ]";
    }

}
