package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Hunter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hunter.findAll", query = "SELECT h FROM Hunter h"),
    @NamedQuery(name = "Hunter.findById", query = "SELECT h FROM Hunter h WHERE h.id = :id"),
    @NamedQuery(name = "Hunter.findByPesel", query = "SELECT h FROM Hunter h WHERE h.pesel = :pesel"),
    @NamedQuery(name = "Hunter.findByPhoneNumber", query = "SELECT h FROM Hunter h WHERE h.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Hunter.findByIsHunting", query = "SELECT h FROM Hunter h WHERE h.isHunting = :isHunting")})
public class Hunter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "pesel")
    private String pesel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "isHunting")
    private Boolean isHunting;
    @OneToMany(mappedBy = "hunterId")
    private Collection<Account> accountCollection;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Account accountId;
    @JoinColumn(name = "addres_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Address addresId;
    @JoinColumn(name = "cull_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cull cullId;
    @JoinColumn(name = "hunt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunt huntId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hunterId")
    private Collection<Hunt> huntCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hunterId")
    private Collection<Cull> cullCollection;

    public Hunter() {
    }

    public Hunter(Long id) {
        this.id = id;
    }

    public Hunter(Long id, String pesel, String phoneNumber) {
        this.id = id;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Address getAddresId() {
        return addresId;
    }

    public void setAddresId(Address addresId) {
        this.addresId = addresId;
    }

    public Cull getCullId() {
        return cullId;
    }

    public void setCullId(Cull cullId) {
        this.cullId = cullId;
    }

    public Hunt getHuntId() {
        return huntId;
    }

    public void setHuntId(Hunt huntId) {
        this.huntId = huntId;
    }

    @XmlTransient
    public Collection<Hunt> getHuntCollection() {
        return huntCollection;
    }

    public void setHuntCollection(Collection<Hunt> huntCollection) {
        this.huntCollection = huntCollection;
    }

    @XmlTransient
    public Collection<Cull> getCullCollection() {
        return cullCollection;
    }

    public void setCullCollection(Collection<Cull> cullCollection) {
        this.cullCollection = cullCollection;
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
        if (!(object instanceof Hunter)) {
            return false;
        }
        Hunter other = (Hunter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Hunter[ id=" + id + " ]";
    }

}
