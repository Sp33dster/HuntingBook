package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Master_of_the_hunter")
public class MasterOfTheHunter extends Account implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "contact_number")
    private String contactNumber;

    public MasterOfTheHunter() {
    }

    public MasterOfTheHunter(Long id) {
        this.id = id;
    }

    public MasterOfTheHunter(Long id, String contactNumber) {
        this.id = id;
        this.contactNumber = contactNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
        if (!(object instanceof MasterOfTheHunter)) {
            return false;
        }
        MasterOfTheHunter other = (MasterOfTheHunter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Masterofthehunter[ id=" + id + " ]";
    }

}
