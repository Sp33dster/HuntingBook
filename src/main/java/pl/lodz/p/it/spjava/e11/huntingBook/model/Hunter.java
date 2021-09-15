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
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

@Entity
@Table(name = "Hunter")
public class Hunter extends Account implements Serializable {

    
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

    public Hunter(String pesel, String phoneNumber, Long id, String login, String password, boolean isActive, String name, String surname, String email, AccountType type) {
        super(id, login, password, isActive, name, surname, email, type);
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
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

    
}
