package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Hunter")
public class Hunter extends Account implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "pesel")
    private String pesel;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "isHunting")
    private Boolean isHunting;

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

    public Collection<Hunt> getHuntCollection() {
        return huntCollection;
    }

    public void setHuntCollection(Collection<Hunt> huntCollection) {
        this.huntCollection = huntCollection;
    }

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
