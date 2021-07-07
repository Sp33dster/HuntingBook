package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Master_of_the_hunter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Masterofthehunter.findAll", query = "SELECT m FROM Masterofthehunter m"),
    @NamedQuery(name = "Masterofthehunter.findById", query = "SELECT m FROM Masterofthehunter m WHERE m.id = :id"),
    @NamedQuery(name = "Masterofthehunter.findByContactNumber", query = "SELECT m FROM Masterofthehunter m WHERE m.contactNumber = :contactNumber")})
public class Masterofthehunter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "contact_number")
    private String contactNumber;
    @OneToMany(mappedBy = "masterOfTheHunterId")
    private Collection<Account> accountCollection;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account accountId;

    public Masterofthehunter() {
    }

    public Masterofthehunter(Long id) {
        this.id = id;
    }

    public Masterofthehunter(Long id, String contactNumber) {
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

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
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
        if (!(object instanceof Masterofthehunter)) {
            return false;
        }
        Masterofthehunter other = (Masterofthehunter) object;
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
